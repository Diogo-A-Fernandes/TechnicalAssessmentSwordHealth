package com.example.catapp.utils

import com.example.catapp.ui.utils.lifeSpanFormatter
import org.junit.Assert.assertEquals
import org.junit.Test

class LifeSpanFormatterTest {

    @Test
    fun `returns Unknown when input is null`() {
        assertEquals("Unknown", lifeSpanFormatter(null))
    }

    @Test
    fun `returns Unknown when input is empty`() {
        assertEquals("Unknown", lifeSpanFormatter(""))
    }

    @Test
    fun `returns Unknown when input is blank`() {
        assertEquals("Unknown", lifeSpanFormatter("   "))
    }

    @Test
    fun `returns min value for range formatted string`() {
        assertEquals("10", lifeSpanFormatter("10-15"))
    }

    @Test
    fun `trims spaces and returns min value`() {
        assertEquals("12", lifeSpanFormatter(" 12 - 20 "))
    }

    @Test
    fun `returns value if only one number is provided`() {
        assertEquals("8", lifeSpanFormatter("8"))
    }

    @Test
    fun `returns Unknown if no number before dash`() {
        assertEquals("Unknown", lifeSpanFormatter("-15"))
    }
}
