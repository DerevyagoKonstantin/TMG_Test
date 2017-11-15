package com.meetme.test.base

/**
 * Created by Konstantin on 16.11.2017.
 */
interface UseCase<in T, out U> {
    fun execute(input: T): U
}