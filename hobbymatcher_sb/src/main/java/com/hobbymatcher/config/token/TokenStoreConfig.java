//package com.hobbymatcher.config.token;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
//
//import com.hobbymatcher.properties.SecurityProperties;
//
///**
// * @author HaoxuanLi
// * @version 1.0
// * @date 2020/3/3 19:42
// */
//@Configuration
//public class TokenStoreConfig {
//
//	@Qualifier("connectionFactory")
//	@Autowired
//	private LettuceConnectionFactory lettuceConnectionFactory;
//
//	@Bean
//	@ConditionalOnProperty(prefix = "hobbymatcher.security.oauth2", name = "storeType", havingValue = "redis")
//	public TokenStore redisTokenStore() {
//		return new RedisTokenStore(lettuceConnectionFactory);
//	}
//
//	@Configuration
//	@ConditionalOnProperty(prefix = "hobbymatcher.security.oauth2", name = "storeType", havingValue = "jwt", matchIfMissing = true)
//	public static class JwtTokenConfig {
//
//		@Autowired
//		private SecurityProperties securityProperties;
//
//		@Bean
//		public TokenStore jwtTokenStore() {
//			return new JwtTokenStore(jwtAccessTokenConverter());
//		}
//
//		@Bean
//		public JwtAccessTokenConverter jwtAccessTokenConverter() {
//			JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
//			jwtAccessTokenConverter.setSigningKey(securityProperties.getOauth2().getJwtSigningKey());
//			return jwtAccessTokenConverter;
//		}
//	}
//}
