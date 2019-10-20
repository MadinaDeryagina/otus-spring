package otus.deryagina.spring.question.loader;

import lombok.AllArgsConstructor;
import otus.deryagina.spring.question.exceptions.QuestionsLoadingException;
import otus.deryagina.spring.question.model.Question;
import otus.deryagina.spring.question.utils.CsvUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@AllArgsConstructor
public class QuestionLoaderCSVImpl implements QuestionLoader{

    private final String fileName;

    @Override
    public List<Question> loadQuestionsAnswers() throws QuestionsLoadingException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(fileName);
        List<Question> questionList;
        try {
            questionList = CsvUtils.read(Question.class,is);
        }catch (IOException ex){
            throw new QuestionsLoadingException(ex.getMessage());
        }
        return questionList;
    }

}
