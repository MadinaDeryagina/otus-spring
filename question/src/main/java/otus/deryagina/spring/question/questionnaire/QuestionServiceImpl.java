package otus.deryagina.spring.question.questionnaire;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import otus.deryagina.spring.question.loader.QuestionLoader;
import otus.deryagina.spring.question.model.Question;

import java.io.IOException;
import java.util.*;

@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    @NonNull private QuestionLoader questionLoader;
    private Map<String,Question> mapOfQuestions;

    public void build(){
        try {
            List<Question> questionList = questionLoader.loadQuestionsAnswers();
            if(questionList == null || questionList.isEmpty()){
                System.out.println("There is no questions");
                return;
            }
            mapOfQuestions= new HashMap<String, Question>();
            for (Question currentQuestion :questionList ) {
                mapOfQuestions.put(currentQuestion.getQuestion(),currentQuestion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Question> getQuestions() {
        if(mapOfQuestions.isEmpty()){
            return  new ArrayList<Question>();
        }
        return Collections.unmodifiableList(new ArrayList<Question>(mapOfQuestions.values()));
    }

    public boolean isCorrectAnswer(String question, String answer){
        if( !mapOfQuestions.containsKey(question)){
            System.out.println("There is no such question");
            return false;
        }
        if(answer.equalsIgnoreCase(mapOfQuestions.get(question).getCorrectAnswer())){
            System.out.println("right answer");
            return true;
        }else{
            System.out.println("wrong answer ");
            return false;
        }
    }
}
