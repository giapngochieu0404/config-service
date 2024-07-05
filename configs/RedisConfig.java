package com.hieuubuntu.configservice.configs;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.lettuce.core.ReadFrom;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.TimeZone;

@Configuration
public class RedisConfig {
    @Bean
    public RedisSentinelConfiguration redisSentinelConfiguration(RedisProperties redisProperties) {
        var configuration = new RedisSentinelConfiguration();
        configuration.setMaster(redisProperties.getSentinel().getMaster());
        configuration.setSentinels(redisProperties.getSentinel().getNodes().stream().map(RedisNode::fromString).toList());
        configuration.setPassword(RedisPassword.of(redisProperties.getSentinel().getPassword()));
        return configuration;
    }

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(RedisSentinelConfiguration redisSentinelConfiguration) {
        var clientConfiguration = LettuceClientConfiguration.builder()
                .readFrom(ReadFrom.REPLICA_PREFERRED)
                .build();

        return new LettuceConnectionFactory(redisSentinelConfiguration, clientConfiguration);
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplateObjectKV(LettuceConnectionFactory lettuceConnectionFactory) {
        var mapper = objectMapper();
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new Jackson2JsonRedisSerializer<>(mapper, Object.class));
        redisTemplate.setHashKeySerializer(new Jackson2JsonRedisSerializer<>(mapper, Object.class));
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(mapper, Object.class));
        redisTemplate.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(mapper, Object.class));
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = JsonMapper.builder()
                .enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)
                .enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY)
                .enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
                .enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .defaultTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"))
                .build();

        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(LettuceConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
}
