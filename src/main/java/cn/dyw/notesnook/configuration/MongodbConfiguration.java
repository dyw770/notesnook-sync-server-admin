package cn.dyw.notesnook.configuration;

import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
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

    @Bean
    MappingMongoConverter mappingMongoConverter(ObjectProvider<MongoDatabaseFactory> factory,
                                                MongoMappingContext context, MongoCustomConversions conversions) {
        MongoDatabaseFactory mongoDatabaseFactory = factory.getIfAvailable();
        DbRefResolver dbRefResolver = (mongoDatabaseFactory != null) ? new DefaultDbRefResolver(mongoDatabaseFactory)
                : NoOpDbRefResolver.INSTANCE;
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
        mappingConverter.setCustomConversions(conversions);
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return mappingConverter;
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
