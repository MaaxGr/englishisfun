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

package com.jpaya.core.di.modules

import android.content.Context
import androidx.room.Room
import com.jpaya.core.BuildConfig
import com.jpaya.core.database.MarvelDatabase
import com.jpaya.core.database.characterfavorite.CharacterFavoriteDao
import com.jpaya.core.database.characterfavorite.CharacterFavoriteRepository
import com.jpaya.core.database.migrations.MIGRATION_1_2
import com.jpaya.core.di.CoreComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Class that contributes to the object graph [CoreComponent].
 *
 * @see Module
 */
@Module
class DatabaseModule {

    /**
     * Create a provider method binding for [MarvelDatabase].
     *
     * @return Instance of marvel database.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideMarvelDatabase(context: Context) =
        Room.databaseBuilder(
            context,
            MarvelDatabase::class.java,
            BuildConfig.MARVEL_DATABASE_NAME
        ).addMigrations(MIGRATION_1_2)
            .build()

    /**
     * Create a provider method binding for [CharacterFavoriteDao].
     *
     * @return Instance of character favorite data access object.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideCharacterFavoriteDao(marvelDatabase: MarvelDatabase) =
        marvelDatabase.characterFavoriteDao()

    /**
     * Create a provider method binding for [CharacterFavoriteRepository].
     *
     * @return Instance of character favorite repository.
     * @see Provides
     */
    @Singleton
    @Provides
    fun provideCharacterFavoriteRepository(
        characterFavoriteDao: CharacterFavoriteDao
    ) = CharacterFavoriteRepository(
        characterFavoriteDao
    )
}
