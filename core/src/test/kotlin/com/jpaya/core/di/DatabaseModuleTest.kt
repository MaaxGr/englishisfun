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

package com.jpaya.core.di

import android.content.Context
import com.jpaya.core.database.MarvelDatabase
import com.jpaya.core.database.characterfavorite.CharacterFavoriteDao
import com.jpaya.core.di.modules.DatabaseModule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DatabaseModuleTest {

    private lateinit var databaseModule: DatabaseModule

    @BeforeEach
    fun setUp() {
        databaseModule = DatabaseModule()
    }

    @Test
    fun verifyProvidedMarvelDatabase() {
        val context: Context = mock()
        val marvelDatabase = databaseModule.provideMarvelDatabase(context)

        assertNotNull(marvelDatabase.characterFavoriteDao())
    }

    @Test
    fun verifyProvidedCharacterFavoriteDao() {
        val marvelDatabase: MarvelDatabase = mock()
        val characterFavoriteDao: CharacterFavoriteDao = mock()

        doReturn(characterFavoriteDao).whenever(marvelDatabase).characterFavoriteDao()

        assertEquals(
            characterFavoriteDao,
            databaseModule.provideCharacterFavoriteDao(marvelDatabase)
        )
        verify(marvelDatabase).characterFavoriteDao()
    }

    @Test
    fun verifyProvidedCharacterFavoriteRepository() {
        val characterFavoriteDao: CharacterFavoriteDao = mock()
        val repository = databaseModule.provideCharacterFavoriteRepository(characterFavoriteDao)

        assertEquals(characterFavoriteDao, repository.characterFavoriteDao)
    }
}
