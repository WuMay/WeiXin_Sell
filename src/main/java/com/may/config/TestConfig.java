package com.may.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties()
@PropertySource("classpath:/config.properties")
@Data
public class TestConfig {
  public String password;


}
