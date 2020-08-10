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

package com.jpaya.englishisfun.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jpaya.englishisfun.abbreviations.data.db.AbbreviationsDao
import com.jpaya.englishisfun.abbreviations.data.db.RoomAbbreviationsItem
import com.jpaya.englishisfun.irregulars.data.db.IrregularsDao
import com.jpaya.englishisfun.irregulars.data.db.RoomIrregularsItem

@Database(
    entities = [RoomIrregularsItem::class, RoomAbbreviationsItem::class],
    version = 1,
    exportSchema = true
)
abstract class EnglishIsFunDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "englishisfun.db"

        fun create(context: Context) =
            Room.databaseBuilder(context, EnglishIsFunDatabase::class.java, DATABASE_NAME).build()
    }

    abstract fun irregulars(): IrregularsDao

    abstract fun abbreviations(): AbbreviationsDao
}
