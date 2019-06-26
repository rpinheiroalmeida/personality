package com.sparke.networks.personality.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Question {


    private final String question;
    private final String category;
    private final QuestionType questionType;
    private final Condition condition;

}
