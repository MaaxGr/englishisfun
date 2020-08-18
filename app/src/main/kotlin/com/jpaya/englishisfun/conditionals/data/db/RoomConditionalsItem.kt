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

package com.jpaya.englishisfun.conditionals.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conditionals")
data class RoomConditionalsItem(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val condition: String,
    val result: String,
    val uses: MutableList<String>,
    val examples: MutableList<String>
)
