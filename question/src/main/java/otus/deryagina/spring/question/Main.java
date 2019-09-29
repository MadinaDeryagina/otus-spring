package otus.deryagina.spring.question;


import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import otus.deryagina.spring.question.loader.QuestionLoader;
import otus.deryagina.spring.question.model.Question;
import otus.deryagina.spring.question.questionnaire.QuestionService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static int NUMBER_OF_ATTEMPTS_FOR_FULL_NAME = 3;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your full name: ");
        String fullName = validFullName(scanner);
        if (fullName == null) {
            System.exit(0);
        }
        System.out.println("Hello " + fullName);
        QuestionService questionService = context.getBean(QuestionService.class);
        try {
            List<Question> questions= questionService.getQuestions();
            System.out.println("There are "+ questions.size() + " questions. Let's go");
            int counter = 0;
            for (int i = 0; i < questions.size(); i++) {
                Question question = questions.get(i);
                System.out.println(question.getQuestion());
                String answer = scanner.nextLine();
                if(questionService.isCorrectAnswer(question.getQuestion(),answer)){
                    counter++;
                }
            }
            System.out.println("Your result " + counter +" correct answers from "+ questions.size()+ " questions");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String validFullName(Scanner scanner) {
        for (int i = 0; i < NUMBER_OF_ATTEMPTS_FOR_FULL_NAME; i++) {
            String fullName = scanner.nextLine();
            if ((StringUtils.isEmpty(fullName) || StringUtils.isBlank(fullName))) {
                if (i != NUMBER_OF_ATTEMPTS_FOR_FULL_NAME - 1) {
                    System.out.println("Your input is empty, please try again. Your have " +
                            String.valueOf(NUMBER_OF_ATTEMPTS_FOR_FULL_NAME - i - 1) + " attempts. Enter your full name again");
                } else {
                    return null;
                }
            } else {
                return fullName;
            }
        }
        return null;
    }

}
