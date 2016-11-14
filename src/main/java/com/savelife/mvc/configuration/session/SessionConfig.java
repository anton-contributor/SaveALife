package com.savelife.mvc.configuration.session;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.ExpiringSession;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import java.io.IOException;

/**
 * Created by gleb on 22.10.16.
 */
@Configuration
@EnableRedisHttpSession
public class SessionConfig {

    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }

    @Bean
    public RedisConnectionFactory connectionFactory() throws IOException {
        RedisConnectionFactory factory = new JedisConnectionFactory();
        return factory;
    }

    @Autowired
    public SessionRepository sessionRepository(RedisConnectionFactory connectionFactory) {
        RedisOperationsSessionRepository redisOperationsSessionRepository =
                new RedisOperationsSessionRepository(connectionFactory);
        redisOperationsSessionRepository.setDefaultMaxInactiveInterval(2592000);
        redisOperationsSessionRepository.setRedisKeyNamespace("savelife");
        return redisOperationsSessionRepository;
    }

}
