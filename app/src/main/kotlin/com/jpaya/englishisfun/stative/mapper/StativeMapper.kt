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

package com.jpaya.englishisfun.stative.mapper

import com.jpaya.englishisfun.stative.data.db.RoomStativeItem
import com.jpaya.englishisfun.stative.data.network.model.StativeContent
import com.jpaya.englishisfun.stative.domain.Stative
import com.jpaya.englishisfun.stative.ui.StativePresenter.StativeItem

fun Stative.toRoomItem() = RoomStativeItem(
    id = id,
    base = base,
    simple = simple,
    participle = participle,
    definitions = definitions
)

fun RoomStativeItem.toDomain() = Stative(
    id = id,
    base = base,
    simple = simple,
    participle = participle,
    definitions = definitions
)

fun StativeContent.toDomain() = Stative(
    id = id,
    base = base,
    simple = simple,
    participle = participle,
    definitions = definitions
)

fun Stative.toPresentation() = StativeItem(
    id = id,
    base = base,
    simple = simple,
    participle = participle,
    definitions = definitions
)
