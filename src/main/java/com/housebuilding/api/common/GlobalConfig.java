package com.housebuilding.api.common;

import com.housebuilding.api.unit.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GlobalConfig {
    private final UnitService unitService;

    @Bean
    CommandLineRunner init() {
        return args -> {
            unitService.init();
        };
    }
}
