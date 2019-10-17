package otus.deryagina.spring.question.interact;

import lombok.AllArgsConstructor;
import otus.deryagina.spring.question.iostreams.IOStreamsProvider;
import otus.deryagina.spring.question.model.Question;
import otus.deryagina.spring.question.questionnaire.QuestionService;

import java.util.List;

@AllArgsConstructor
public class AskQuestionsServiceImpl implements AskQuestionsService {

    private final QuestionService questionService;
    private final IOStreamsProvider ioStreamsProvider;

    public void startAskQuestion() {

        List<Question> questions = questionService.getQuestions();
        ioStreamsProvider.printInfo("There are " + questions.size() + " questions. Let's go");
        int counter = 0;
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            ioStreamsProvider.printInfo(question.getQuestion());
            String answer = ioStreamsProvider.readData();
            boolean isCorrectAnswer = questionService.isCorrectAnswer(question.getQuestion(), answer);
            if (isCorrectAnswer) {
                ioStreamsProvider.printInfo("correct answer");
                counter++;
            } else {
                ioStreamsProvider.printInfo("wrong answer");
            }
        }
        ioStreamsProvider.printInfo("Your result " + counter + " correct answers from " + questions.size() + " questions");

    }
}
