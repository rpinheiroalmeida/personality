package com.sparke.networks.personality.category;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.sparke.networks.personality.PersonalityApplication;
import com.sparke.networks.personality.query.template.Template;
import com.sparke.networks.personality.query.template.TemplateRepository;
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
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = PersonalityApplication.class)
@DataMongoTest
@ExtendWith(SpringExtension.class)
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @DisplayName("given I have categories"
            + " when save categories "
            + " then categories are saved")
    @Test
    public void saveTemplate() {
        //given
        Categories categories = Categories.builder().categories(
                Arrays.asList("hard_fact", "lifestyle", "introversion", "passion")
        ).build();

        //when
        categoryRepository.save(categories);

        Categories categoriesSaved = categoryRepository.findAll().get(0);

        //then
        assertThat(categoriesSaved).isEqualTo(categories);
    }
}
