package br.com.phs.utils

// General Purpose

// Exit Constants Section
const val EXIT_OK = 0
const val INVALID_ARGS_ERROR_EXIT = 1
const val CANCEL_EXIT = 2

// Helpers Constants Section
val welcomeContent = """
    |Welcome to elen * Environment LikE Node * version 0.0.1-ALPHA-V1
""".trimMargin()

val errorContent = """
    |$welcomeContent
    |Type help or -h to view command list
""".trimMargin()

val argumentsErrorContent = """
    |$welcomeContent
    |Argument error. Please, to help type help or -h
""".trimMargin()

val helpContent = """
    |Type: 
    |   [version or -v] to show version
    |   [init or -i] to initialize a new Kotlin with Gradle project
    |   [help or -h] to show help
    |   [add] to add dependency example add:com.example:example:1.0
    |   [build or -b] to build project
    |   [clean or -c] to clear project
    |   [run or -r] to start application
    |   [tests or -t] to execute all tests
    |   [test or -t] to execute a specific test example test:MainTest or -t:MainTest
""".trimMargin()