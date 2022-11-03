package com.example.lambda

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author Brian
 * @since 2022/11/03
 */
@Configuration
class RandomFunction {

    @Bean
    fun getName(): (User) -> (String) {
        return { it.name }
    }
}