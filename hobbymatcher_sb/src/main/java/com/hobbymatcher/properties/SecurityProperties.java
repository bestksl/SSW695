package com.hobbymatcher.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author HaoxuanLi
 * @version 1.0
 * @date 2020/3/3 17:50
 */
@ConfigurationProperties(prefix = "hobbymatcher.security")
public class SecurityProperties {
    private OAuth2Properties oauth2 = new OAuth2Properties();

    public OAuth2Properties getOauth2() {
        return oauth2;
    }

    public void setOauth2(OAuth2Properties oauth2) {
        this.oauth2 = oauth2;
    }
}

