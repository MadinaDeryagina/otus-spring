package otus.deryagina.spring.question.interact;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import otus.deryagina.spring.question.iostreams.IOStreamsProvider;
import otus.deryagina.spring.question.model.Question;
import otus.deryagina.spring.question.questionnaire.QuestionService;

import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class AskQuestionsServiceImpl implements AskQuestionsService {

    private final QuestionService questionService;
    private final IOStreamsProvider ioStreamsProvider;
    private final MessageSource messageSource;
    private final Locale locale;

    @Override
    public void startAskQuestion() {

        List<Question> questions = questionService.getQuestions();
        ioStreamsProvider.printInfo(messageSource.getMessage("number.questions.to.ask", new String[]{String.valueOf(questions.size())}, locale));
        int counter = 0;
        for (Question question : questions) {
            ioStreamsProvider.printInfo(question.getQuestion());
            String answer = ioStreamsProvider.readData();
            boolean isCorrectAnswer = questionService.isCorrectAnswer(question.getQuestion(), answer);
            if (isCorrectAnswer) {
                ioStreamsProvider.printInfo(messageSource.getMessage("correct.answer", null, locale));
                counter++;
            } else {
                ioStreamsProvider.printInfo(messageSource.getMessage("wrong.answer", null, locale));
            }
        }
        ioStreamsProvider.printInfo(messageSource.getMessage("user.result",
                new String[]{String.valueOf(counter), String.valueOf(questions.size())}, locale));

    }
}
