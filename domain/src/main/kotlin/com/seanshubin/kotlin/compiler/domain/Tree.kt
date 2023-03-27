package com.seanshubin.kotlin.compiler.domain

sealed interface Tree<T> {
    val name: String
    fun toLines(): List<String>
    fun flatten(): List<T>

    data class Leaf<T>(override val name: String, val value: T) : Tree<T> {
        override fun toLines(): List<String> = listOf("$name: $value")
        override fun flatten(): List<T> = listOf(value)
    }

    data class Branch<T>(override val name: String, val list: List<Tree<T>>) : Tree<T> {
        override fun toLines(): List<String> = listOf(name) + list.flatMap { it.toLines() }.map { "  $it" }
        override fun flatten(): List<T> = list.flatMap { it.flatten() }
    }
}
