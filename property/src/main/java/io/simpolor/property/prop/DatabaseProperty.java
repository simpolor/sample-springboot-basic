package io.simpolor.property.prop;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/***
 * @ConfigurationProperties를 사용하기 위해서는,Maven에서 spring-boot-configuration-processor 추가가 필요
 */
@Getter
@Component
@PropertySource("classpath:database.yml")
@ConfigurationProperties(prefix = "database")
@Validated
public class DatabaseProperty {

    @Value("${host}")
    private String host;

    @Value("${port}")
    private String port;

}
