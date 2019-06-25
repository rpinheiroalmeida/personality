package com.sparke.networks.personality.query.template;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.sparke.networks.personality.PersonalityApplication;
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

import static org.assertj.core.api.Assertions.assertThat;


@ContextConfiguration(classes = PersonalityApplication.class)
@DataMongoTest
@ExtendWith(SpringExtension.class)
public class TemplateRepositotyTests {

    private static final String LOCALHOST = "localhost";
    private static final int PORT = 27017;

    private MongodExecutable mongodExecutable;
    private MongoTemplate mongoTemplate;

    @Autowired
    TemplateRepository templateRepository;

    @BeforeEach
    void setUp() throws IOException {
		IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION)
				.net(new Net(LOCALHOST, PORT, Network.localhostIsIPv6()))
				.build();

		MongodStarter starter = MongodStarter.getDefaultInstance();
		mongodExecutable = starter.prepare(mongodConfig);
		mongodExecutable.start();
		mongoTemplate = new MongoTemplate(new MongoClient(LOCALHOST, PORT), "test");
	}

    @AfterEach
    void clean() {
        mongodExecutable.stop();
    }

    @DisplayName("given object to save"
            + " when save object using MongoDB template"
            + " then object is saved")
    @Test
    public void test() throws Exception {
        // given
        DBObject objectToSave = BasicDBObjectBuilder.start()
                .add("key", "value")
                .get();

        // when
        mongoTemplate.save(objectToSave, "collection");

        // then
        assertThat(mongoTemplate.findAll(DBObject.class, "collection")).extracting("key")
                .containsOnly("value");
    }

    @DisplayName("given I have a template"
            + " when save template "
            + " then template is saved")
    @Test
    public void saveTemplate() {
        //given
        Template template = new Template("template");

        //when
        templateRepository.save(template);

        //then
        Template templateSaved = templateRepository.findOne(
                Example.of(new Template("template"))
        ).get();

        assertThat(template).isEqualTo(new Template("template"));
    }
}
