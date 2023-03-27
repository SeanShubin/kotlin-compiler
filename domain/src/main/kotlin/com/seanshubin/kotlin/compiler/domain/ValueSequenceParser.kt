package com.seanshubin.kotlin.compiler.domain

import com.seanshubin.kotlin.compiler.cursor.Cursor

class ValueSequenceParser<T>(override val name: String, private val values: List<T>) : Parser<T> {
    override fun parse(start: Cursor<T>): Result<T> {
        var index = 0
        var current = start
        val list = mutableListOf<Tree<T>>()
        while (index < values.size) {
            val expected = values[index]
            val actual = current.value ?: return Result.Failure(listOf(name), start, "expected $expected, got end")
            if (expected != actual) {
                return Result.Failure(listOf(name), start, "expected $expected, got $actual")
            }
            list.add(Tree.Leaf("$name-part", actual))
            index++
            current = current.next
        }
        return Result.Success(listOf(name), start, current, Tree.Branch(name, list))
    }
}
