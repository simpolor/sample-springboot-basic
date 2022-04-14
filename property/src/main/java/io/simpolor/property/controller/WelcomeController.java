package io.simpolor.property.controller;

import io.simpolor.property.config.PropertyConfig;
import io.simpolor.property.prop.ApplicationProperty;
import io.simpolor.property.prop.DatabaseProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WelcomeController {

    private final ApplicationProperty applicationProperty;
    private final DatabaseProperty databaseProperty;
    private final PropertyConfig propertyConfig;

    @ResponseBody
    @RequestMapping({"/", "/index", "/welcome"})
    public String welcome() {
        return "Springboot Sample Property";
    }

    @ResponseBody
    @RequestMapping({"/property"})
    public String property() {

        log.info("applicationProperty.name :{}", applicationProperty.getName());
        log.info("applicationProperty.email : {}", applicationProperty.getEmail());

        log.info("----");

        log.info("propertyConfig.name : {}", propertyConfig.application().getName());
        log.info("propertyConfig.email : {}", propertyConfig.application().getEmail());

        log.info("----");

        log.info("databaseProperty.host : {}", databaseProperty.getHost());
        log.info("databaseProperty.port : {}", databaseProperty.getPort());

        return "OK";
    }
}
