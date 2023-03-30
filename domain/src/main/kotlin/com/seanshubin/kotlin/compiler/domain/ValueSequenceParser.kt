package com.seanshubin.kotlin.compiler.domain

import com.seanshubin.kotlin.compiler.cursor.Cursor

class ValueSequenceParser<T>(override val name: String, private val values: List<T>) : Parser<T> {
    override fun parse(lookup:(String)->Parser<T>, start: Cursor<T>): ParseResult<T> {
        var index = 0
        var current = start
        val list = mutableListOf<Tree<T>>()
        val history = mutableListOf<ParseResult<T>>()
        while (index < values.size) {
            val expected = values[index]
            val actual = current.value ?: return ParseResult.Failure(history, listOf(name, "part"), start, "expected $expected, got end")
            if (expected != actual) {
                return ParseResult.Failure(history, listOf(name, "part"), start, "expected $expected, got $actual")
            }
            val toAdd: Tree.Leaf<T> = Tree.Leaf("$name-part", actual)
            list.add(toAdd)
            index++
            current = current.next
            history.add(ParseResult.Success(emptyList(), listOf(name, "part"), start, current, toAdd))
        }
        return ParseResult.Success(history, listOf(name), start, current, Tree.Branch(name, list))
    }
}
