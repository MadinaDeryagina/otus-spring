package otus.deryagina.spring.question.questionnaire;

import otus.deryagina.spring.question.model.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionService {

    List<Question> getQuestions() throws IOException;
    boolean isCorrectAnswer(String question, String answer);

}
