package com.seanshubin.kotlin.compiler.cursor

class FilteringCursor<T>(
    private val backingCursor: Cursor<T>,
    private val predicate: (T) -> Boolean
) : Cursor<T> {
    private val current = nextAllowedByFilter()
    override val value: T? get() = current.value
    override val next: Cursor<T> get() = FilteringCursor(current.next, predicate)

    private fun nextAllowedByFilter(): Cursor<T> {
        val value = backingCursor.value
        return if (value == null) {
            backingCursor
        } else if (predicate(value)) {
            backingCursor
        } else {
            backingCursor.next
        }
    }
}
