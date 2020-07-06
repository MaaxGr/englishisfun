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

package com.jpaya.englishisfun.abbreviations

import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Test

class AbbreviationsListViewStateTest {

    lateinit var state: AbbreviationsListViewState

    @Test
    fun setStateAsRefreshing_ShouldBeSettled() {
        state = AbbreviationsListViewState.Refreshing

        assertTrue(state.isRefreshing())
        assertFalse(state.isLoaded())
        assertFalse(state.isLoading())
        assertFalse(state.isAddLoading())
        assertFalse(state.isEmpty())
        assertFalse(state.isError())
        assertFalse(state.isAddError())
        assertFalse(state.isNoMoreElements())
    }

    @Test
    fun setStateAsLoaded_ShouldBeSettled() {
        state = AbbreviationsListViewState.Loaded

        assertTrue(state.isLoaded())
        assertFalse(state.isRefreshing())
        assertFalse(state.isLoading())
        assertFalse(state.isAddLoading())
        assertFalse(state.isEmpty())
        assertFalse(state.isError())
        assertFalse(state.isAddError())
        assertFalse(state.isNoMoreElements())
    }

    @Test
    fun setStateAsLoading_ShouldBeSettled() {
        state = AbbreviationsListViewState.Loading

        assertTrue(state.isLoading())
        assertFalse(state.isRefreshing())
        assertFalse(state.isLoaded())
        assertFalse(state.isAddLoading())
        assertFalse(state.isEmpty())
        assertFalse(state.isError())
        assertFalse(state.isAddError())
        assertFalse(state.isNoMoreElements())
    }

    @Test
    fun setStateAsAddLoading_ShouldBeSettled() {
        state = AbbreviationsListViewState.AddLoading

        assertTrue(state.isAddLoading())
        assertFalse(state.isRefreshing())
        assertFalse(state.isLoaded())
        assertFalse(state.isLoading())
        assertFalse(state.isEmpty())
        assertFalse(state.isError())
        assertFalse(state.isAddError())
        assertFalse(state.isNoMoreElements())
    }

    @Test
    fun setStateAsEmpty_ShouldBeSettled() {
        state = AbbreviationsListViewState.Empty

        assertTrue(state.isEmpty())
        assertFalse(state.isRefreshing())
        assertFalse(state.isLoaded())
        assertFalse(state.isLoading())
        assertFalse(state.isAddLoading())
        assertFalse(state.isError())
        assertFalse(state.isAddError())
        assertFalse(state.isNoMoreElements())
    }

    @Test
    fun setStateAsError_ShouldBeSettled() {
        state = AbbreviationsListViewState.Error

        assertTrue(state.isError())
        assertFalse(state.isRefreshing())
        assertFalse(state.isLoaded())
        assertFalse(state.isLoading())
        assertFalse(state.isAddLoading())
        assertFalse(state.isEmpty())
        assertFalse(state.isAddError())
        assertFalse(state.isNoMoreElements())
    }

    @Test
    fun setStateAsAddError_ShouldBeSettled() {
        state = AbbreviationsListViewState.AddError

        assertTrue(state.isAddError())
        assertFalse(state.isRefreshing())
        assertFalse(state.isLoaded())
        assertFalse(state.isLoading())
        assertFalse(state.isAddLoading())
        assertFalse(state.isEmpty())
        assertFalse(state.isError())
        assertFalse(state.isNoMoreElements())
    }

    @Test
    fun setStateAsNoMoreElements_ShouldBeSettled() {
        state = AbbreviationsListViewState.NoMoreElements

        assertTrue(state.isNoMoreElements())
        assertFalse(state.isRefreshing())
        assertFalse(state.isLoaded())
        assertFalse(state.isLoading())
        assertFalse(state.isAddLoading())
        assertFalse(state.isEmpty())
        assertFalse(state.isError())
        assertFalse(state.isAddError())
    }
}
