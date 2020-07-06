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

package com.jpaya.dynamicfeatures.abbreviations.ui.adapter

import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AbbreviationsListAdapterTest {

    private lateinit var adapter: AbbreviationsListAdapter

    @Before
    fun setUp() {
        adapter = AbbreviationsListAdapter(mock())
    }

    @Test
    fun initShouldInitialiseProperly() {
        assertNotNull(adapter.state)
    }

    @Test
    fun submitStateWithSameStateShouldNotNotifyChanges() {
        val newState: AbbreviationsListAdapterState = AbbreviationsListAdapterState.Added
        adapter.submitState(newState)
        assertEquals(newState, adapter.state)
    }
}