package otus.deryagina.spring.question.questionnaire;


import otus.deryagina.spring.question.exceptions.QuestionsLoadingException;
import otus.deryagina.spring.question.loader.QuestionLoader;
import otus.deryagina.spring.question.model.Question;

import java.util.*;


public class QuestionServiceImpl implements QuestionService {

    private final QuestionLoader questionLoader;
    private final Map<String,Question> mapOfQuestions;

    public QuestionServiceImpl(QuestionLoader questionLoader) throws QuestionsLoadingException {
        this.questionLoader = questionLoader;
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
            throw new IllegalArgumentException("There is no such question " + question);
        }
        return answer.equalsIgnoreCase(mapOfQuestions.get(question).getCorrectAnswer());
    }
}
