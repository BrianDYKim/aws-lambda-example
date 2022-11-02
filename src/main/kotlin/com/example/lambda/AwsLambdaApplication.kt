package com.example.lambda

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class AwsLambdaApplication

fun main(args: Array<String>) {
    SpringApplication.run(AwsLambdaApplication::class.java, *args)
}