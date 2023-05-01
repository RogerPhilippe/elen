/*
 * Copyright [2023] [Roger Philippe Pereira] - rogerphilippe@outlook.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
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