package com.meetme.test.base

interface UseCase<in T, out U> {
    fun execute(input: T): U
}