package otus.deryagina.spring.question.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
//@AllArgsConstructor(onConstructor = @__(@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)))
@ToString
public class Question {
    private final String question;
    private final String correctAnswer;
    @JsonCreator
    public Question(@JsonProperty("question") String question,@JsonProperty("correct_answer") String correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

}
