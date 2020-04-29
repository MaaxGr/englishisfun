/*
 * Copyright 2020 Jose Maria Payá Castillo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jpaya.core.extensions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

/**
 * Unit test for ByteArrayExtensions functions
 */
class ByteArrayExtensionsTest {

    @Test
    fun encodeToHexText() {
        val originalText = "Expected string"
        val expectedHexadecimal = "457870656374656420737472696e67"
        assertEquals(expectedHexadecimal, originalText.toByteArray().toHex())
    }

    @Test
    fun encodeToHexSpecialCharactersText() {
        val originalText = "^/4(0!§±§.,=+-'~`"
        val expectedHexadecimal = "5e2f34283021c2a7c2b1c2a72e2c3d2b2d277e60"
        assertEquals(expectedHexadecimal, originalText.toByteArray().toHex())
    }

    @Test
    fun encodeToHexEmptyText() {
        val originalText = ""
        val expectedHexadecimal = ""
        assertEquals(expectedHexadecimal, originalText.toByteArray().toHex())
    }

    @Test
    fun encodeToHexUnexpectedText() {
        val originalText = "Not expected string"
        val expectedHexadecimal = "457870656374656420737472696e67"
        assertNotEquals(expectedHexadecimal, originalText.toByteArray().toHex())
    }
}
