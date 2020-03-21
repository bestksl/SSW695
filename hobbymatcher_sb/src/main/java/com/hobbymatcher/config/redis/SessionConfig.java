//package com.hobbymatcher.config.redis;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;
//
////这个类用配置redis服务器的连接
////maxInactiveIntervalInSeconds为SpringSession的过期时间（单位：秒）
//@Configuration
//public class SessionConfig extends RedisHttpSessionConfiguration {
//
//	@Bean
//	public LettuceConnectionFactory connectionFactory() {
//		return new LettuceConnectionFactory();
//	}
//
//	@PostConstruct
//	public void initConfig() throws Exception {
//		this.setMaxInactiveIntervalInSeconds(3600);
//		this.setRedisNamespace("session-redis");
//	}
//}