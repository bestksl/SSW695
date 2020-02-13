package com.hobbymatcher.config.Dao;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class SessionFactoryConfiguration {

    private static String mapperLocations;
    private static String configLocation;

    @Autowired
    private DataSource dataSource;

    @Value("${typeAliasesPackage}")
    private String typeAliasesPackage;

    @Value("${configLocation}")
    public void setConfigLocation(String configLocation) {
        SessionFactoryConfiguration.configLocation = configLocation;
    }

    @Value("${mapperLocations}")
    public void setMapperLocations(String mapperLocations) {
        SessionFactoryConfiguration.mapperLocations = mapperLocations;
    }

    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
        sfb.setConfigLocation(new ClassPathResource(configLocation));
        sfb.setTypeAliasesPackage(typeAliasesPackage);
        PathMatchingResourcePatternResolver p = new PathMatchingResourcePatternResolver();
        String packageSearchPath = p.CLASSPATH_ALL_URL_PREFIX + mapperLocations;
        sfb.setMapperLocations(p.getResources(packageSearchPath));
        sfb.setDataSource(dataSource);
        sfb.setTypeAliasesPackage(typeAliasesPackage);
        return sfb;
    }
}
