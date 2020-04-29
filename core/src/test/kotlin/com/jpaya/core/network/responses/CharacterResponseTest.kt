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

package com.jpaya.core.network.responses

import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CharacterResponseTest {

    @Test
    fun createCharacterResponse_ShouldAddCorrectAttributes() {
        val id = 131231L
        val name = "A.I.M"
        val description = "AIM is a terrorist organization bent on destroying the world."
        val thumbnail: CharacterThumbnailResponse = mock()

        val characterResponse =
            CharacterResponse(
                id = id,
                name = name,
                description = description,
                thumbnail = thumbnail
            )

        assertEquals(id, characterResponse.id)
        assertEquals(name, characterResponse.name)
        assertEquals(description, characterResponse.description)
        assertEquals(thumbnail, characterResponse.thumbnail)
    }
}
