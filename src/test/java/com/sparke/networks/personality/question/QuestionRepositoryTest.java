package com.sparke.networks.personality.question;

import com.mongodb.MongoClient;
import com.sparke.networks.personality.PersonalityApplication;
import com.sparke.networks.personality.category.CategoryRepository;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = PersonalityApplication.class)
@DataMongoTest
@ExtendWith(SpringExtension.class)
public class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @AfterEach
    void clean() {
        questionRepository.deleteAll();
    }

    @DisplayName("given I have a question" +
            "when I save the question" +
            "then the question is saved")
    @Test
    void saveQuestion() {
        Question question = Question.builder()
                                .question("What is your gender?")
                                .category("hard_fact")
                                .questionType(
                                        new QuestionType(
                                                "single_choice",
                                                Arrays.asList("male", "female", "other"))
                                ).build();

        questionRepository.save(question);

        Question questionSaved = questionRepository.findAll().get(0);

        assertThat(questionSaved).isEqualTo(question);
    }

    @DisplayName("given I have a question with condition" +
            "when I save the question" +
            "then the question is saved")
    @Test
    void saveQuestion_withCondition() {
        Question question = Question.builder()
                .question("What is your gender?")
                .category("hard_fact")
                .questionType(
                        new QuestionType(
                                "single_choice",
                                Arrays.asList("male", "female", "other"))
                )
                .condition(
                        Condition.builder()
                            .predicate(Predicate.builder()
                                    .name("exactEquals")
                                    .answeres(Arrays.asList("${selection}", "very important"))
                                    .build())
                            .ifPositive(IfPositive.builder()
                                            .question(Question.builder()
                                                .question("What age should your potential partner be?")
                                                .category("hard_fact")
                                                .questionType(
                                                    new QuestionType("number_range",
                                                            Arrays.asList("18", "40"))).build())
                                    .build())
                        .build())
                .build();

        questionRepository.save(question);

        Question questionSaved = questionRepository.findAll().get(0);

        assertThat(questionSaved).isEqualTo(question);
    }

}
