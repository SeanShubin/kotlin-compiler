package com.seanshubin.kotlin.compiler.cursor

import com.seanshubin.kotlin.compiler.cursor.CharCursor.cursor
import kotlin.test.Test
import kotlin.test.assertEquals

class CharCursorTest {
    @Test
    fun nullValuesAfterEnd() {
        val s = "abc"
        val cursorA = s.cursor()
        val cursorB = cursorA.next
        val cursorC = cursorB.next
        val cursorD = cursorC.next
        val cursorE = cursorD.next

        assertEquals('a', cursorA.value)
        assertEquals('b', cursorB.value)
        assertEquals('c', cursorC.value)
        assertEquals(null, cursorD.value)
        assertEquals(null, cursorE.value)
    }

    @Test
    fun splitCursorRemainsConsistent() {
        val s = "abc"
        val cursorA = s.cursor()
        val cursorB1 = cursorA.next
        val cursorB2 = cursorA.next
        val cursorC1 = cursorB1.next
        val cursorC2 = cursorB2.next

        assertEquals('a', cursorA.value)
        assertEquals('b', cursorB1.value)
        assertEquals('b', cursorB2.value)
        assertEquals('c', cursorC1.value)
        assertEquals('c', cursorC2.value)
    }

    @Test
    fun filter(){
        val s = "abcaba"
        val cursor = s.cursor().filter { it != 'a' }
        val actual = cursor.reifyAll().joinToString("")
        val expected = "bcb"

        assertEquals(expected, actual)
    }

    @Test
    fun between(){
        val s = "abcaba"
        val cursor = s.cursor()
        val oneIn = cursor.next
        val fourIn = cursor.next.next.next.next
        val actual = oneIn.between(fourIn).joinToString("")
        val expected = "bca"

        assertEquals(expected, actual)
    }
}