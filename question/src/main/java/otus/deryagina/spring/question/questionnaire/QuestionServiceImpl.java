package otus.deryagina.spring.question.questionnaire;


import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import otus.deryagina.spring.question.exceptions.QuestionsLoadingException;
import otus.deryagina.spring.question.loader.QuestionLoader;
import otus.deryagina.spring.question.model.Question;

import java.util.*;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionLoader questionLoader;
    private final Map<String,Question> mapOfQuestions;
    private final MessageSource messageSource;
    private final Locale locale;

    public QuestionServiceImpl(QuestionLoader questionLoader, MessageSource messageSource, Locale locale) throws QuestionsLoadingException {
        this.questionLoader = questionLoader;
        this.messageSource = messageSource;
        this.locale = locale;
        List<Question> questionList = this.questionLoader.loadQuestionsAnswers();
        mapOfQuestions= new HashMap<>();
        for (Question currentQuestion :questionList ) {
            mapOfQuestions.put(currentQuestion.getQuestion(),currentQuestion);
        }
    }

    @Override
    public List<Question> getQuestions() {
        if(mapOfQuestions.isEmpty()){
            return new ArrayList<>();
        }
        return Collections.unmodifiableList(new ArrayList<>(mapOfQuestions.values()));
    }

    @Override
    public boolean isCorrectAnswer(String question, String answer){
        if( !mapOfQuestions.containsKey(question)){
            throw new IllegalArgumentException(messageSource.getMessage("no.such.question",
                    new String[]{question}, locale));
        }
        return answer.equalsIgnoreCase(mapOfQuestions.get(question).getCorrectAnswer());
    }
}
