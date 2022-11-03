package com.example.lambda

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

/**
 * @author Brian
 * @since 2022/11/03
 */
@Component
class ExampleFunction {
    @Bean
    fun getId(): (User) -> (Long) {
        return { it.id }
    }
}