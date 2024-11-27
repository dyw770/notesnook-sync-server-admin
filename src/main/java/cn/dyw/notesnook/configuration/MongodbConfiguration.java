package cn.dyw.notesnook.configuration;

import com.mongodb.client.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author dyw770
 * @date 2024-11-27
 */
@Configuration
public class MongodbConfiguration {

    @Bean
    public MongoTemplate identityMongoTemplate(MongoClient client) {
        return new MongoTemplate(client, "identity");
    }

    @Bean
    @Primary
    public MongoTemplate notesnookMongoTemplate(MongoClient client) {
        return new MongoTemplate(client, "notesnook");
    }

    @Configuration
    @EnableMongoRepositories(value = "cn.dyw.notesnook.repository.identity", mongoTemplateRef = "identityMongoTemplate")
    static class IdentityMongodbConfiguration {

    }

    @Configuration
    @EnableMongoRepositories(value = "cn.dyw.notesnook.repository.notesnook", mongoTemplateRef = "notesnookMongoTemplate")
    static class NotesnookMongodbConfiguration {

    }
}
