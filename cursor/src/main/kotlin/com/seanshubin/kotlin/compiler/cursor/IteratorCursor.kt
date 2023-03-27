package com.seanshubin.kotlin.compiler.cursor

class IteratorCursor<T>(private val iterator: Iterator<T>) : Cursor<T> {
    override val value: T? = if (iterator.hasNext()) iterator.next() else null
    private var nextCursor: IteratorCursor<T>? = null

    private fun reifyAndGet(): IteratorCursor<T> {
        val localNextCursor = nextCursor
        return if (localNextCursor == null) {
            nextCursor = IteratorCursor(iterator)
            reifyAndGet()
        } else {
            localNextCursor
        }
    }

    override val next: Cursor<T> get() = reifyAndGet()

    companion object {
        fun <T> Iterator<T>.cursor(): Cursor<T> = IteratorCursor(this)
    }
}
