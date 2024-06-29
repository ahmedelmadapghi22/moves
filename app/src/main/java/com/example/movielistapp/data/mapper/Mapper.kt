package com.example.movielistapp.data.mapper

abstract class Mapper<Input, T> {
    open abstract fun mapInToOut(input: Input): T

    open fun mapListInToOut(input: List<Input>): List<T> = input.map { mapInToOut(it) }


}