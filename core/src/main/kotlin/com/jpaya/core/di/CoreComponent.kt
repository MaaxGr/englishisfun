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
import com.google.firebase.firestore.FirebaseFirestore
import com.jpaya.core.database.characterfavorite.CharacterFavoriteDao
import com.jpaya.core.di.modules.ContextModule
import com.jpaya.core.di.modules.NetworkModule
import com.jpaya.core.di.modules.DatabaseModule
import com.jpaya.core.di.modules.UtilsModule
import com.jpaya.core.di.modules.FirebaseModule
import com.jpaya.core.network.repositiories.MarvelRepository
import com.jpaya.core.network.services.MarvelService
import com.jpaya.core.utils.ThemeUtils
import dagger.Component
import javax.inject.Singleton

/**
 * Core component that all module's components depend on.
 *
 * @see Component
 */
@Singleton
@Component(modules = [
    ContextModule::class,
    NetworkModule::class,
    DatabaseModule::class,
    UtilsModule::class,
    FirebaseModule::class
])
interface CoreComponent {

    /**
     * Provide dependency graph Context
     *
     * @return Context
     */
    fun context(): Context

    /**
     * Provide dependency graph MarvelService
     *
     * @return MarvelService
     */
    fun marvelService(): MarvelService

    /**
     * Provide dependency graph MarvelRepository
     *
     * @return MarvelRepository
     */
    fun marvelRepository(): MarvelRepository

    /**
     * Provide dependency graph CharacterFavoriteDao
     *
     * @return CharacterFavoriteDao
     */
    fun characterFavoriteDao(): CharacterFavoriteDao

    /**
     * Provide dependency graph ThemeUtils
     *
     * @return ThemeUtils
     */
    fun themeUtils(): ThemeUtils

    /**
     * Provide dependency graph FirebaseFirestore
     *
     * @return FirebaseFirestore
     */
    fun firebaseFirestore(): FirebaseFirestore
}
