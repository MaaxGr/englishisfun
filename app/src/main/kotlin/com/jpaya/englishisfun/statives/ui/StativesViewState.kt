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

package com.jpaya.englishisfun.statives.ui

import com.jpaya.englishisfun.statives.ui.model.StativeItem

sealed class StativesViewState {
    abstract fun showLoading(): Boolean
    abstract fun showError(): Boolean
    abstract fun showList(): Boolean
    abstract fun list(): List<StativeItem>

    object Loading : StativesViewState() {
        override fun showLoading(): Boolean = true
        override fun showError(): Boolean = false
        override fun showList(): Boolean = false
        override fun list(): List<StativeItem> = listOf()
    }

    data class ListReady(val statives: List<StativeItem>) : StativesViewState() {
        override fun showLoading(): Boolean = false
        override fun showError(): Boolean = false
        override fun showList(): Boolean = true
        override fun list(): List<StativeItem> = statives
    }

    object NetworkError : StativesViewState() {
        override fun showLoading(): Boolean = false
        override fun showError(): Boolean = true
        override fun showList(): Boolean = false
        override fun list(): List<StativeItem> = listOf()
    }
}
