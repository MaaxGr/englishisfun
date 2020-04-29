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

package com.jpaya.dynamicfeatures.caractersfavorites.ui.favorite.di

import androidx.lifecycle.ViewModel
import com.jpaya.commons.ui.extensions.viewModel
import com.jpaya.core.database.characterfavorite.CharacterFavoriteRepository
import com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite.CharactersFavoriteFragment
import com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite.CharactersFavoriteViewModel
import com.jpaya.dynamicfeatures.charactersfavorites.ui.favorite.di.CharactersFavoriteModule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CharactersFavoriteModuleTest {

    @MockK
    lateinit var fragment: CharactersFavoriteFragment
    private lateinit var module: CharactersFavoriteModule

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun initializeCharactersFavoriteModule_ShouldSetUpCorrectly() {
        module = CharactersFavoriteModule(fragment)

        assertEquals(fragment, module.fragment)
    }

    @Test
    fun verifyProvidedCharactersFavoriteViewModel() {
        mockkStatic("com.jpaya.commons.ui.extensions.FragmentExtensionsKt")

        every {
            fragment.viewModel(any(), any<() -> ViewModel>())
        } returns mockk<CharactersFavoriteViewModel>()

        val factoryCaptor = slot<() -> CharactersFavoriteViewModel>()
        val favoriteRepository = mockk<CharacterFavoriteRepository>(relaxed = true)
        module = CharactersFavoriteModule(fragment)
        module.providesCharactersFavoriteViewModel(favoriteRepository)

        verify {
            fragment.viewModel(factory = capture(factoryCaptor))
        }

        assertEquals(favoriteRepository, factoryCaptor.captured().characterFavoriteRepository)
    }

    @Test
    fun verifyProvidedCharactersFavoriteAdapter() {
        module = CharactersFavoriteModule(fragment)

        assertNotNull(module.providesCharactersFavoriteAdapter())
    }
}
