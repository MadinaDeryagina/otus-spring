package otus.deryagina.spring.question.loader;

import otus.deryagina.spring.question.model.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionLoader {

    List<Question> loadQuestionsAnswers() throws IOException;

}
