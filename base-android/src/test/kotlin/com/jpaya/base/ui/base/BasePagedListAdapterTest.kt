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

package com.jpaya.base.ui.base

import android.view.ViewGroup
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.jpaya.libraries.testutils.pagelist.pagedListOf
import com.jpaya.libraries.testutils.robolectric.TestRobolectric
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.anyInt
import org.mockito.MockitoAnnotations
import org.mockito.Spy

class BasePagedListAdapterTest : TestRobolectric() {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    interface Comparator : (String, String) -> Boolean {
        override fun invoke(p1: String, p2: String): Boolean = false
    }

    @Mock
    lateinit var viewHolder: RecyclerView.ViewHolder
    @Mock
    lateinit var itemsSame: Comparator
    @Mock
    lateinit var contentsSame: Comparator
    @Mock
    lateinit var recyclerView: RecyclerView
    @Spy
    lateinit var adapter: TestBasePagedListAdapter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun initializeAdapter_WithoutRecycleView() {
        assertTrue(adapter.hasStableIds())
        assertNull(adapter.recyclerView)
    }

    @Test
    fun initializeAdapter_WithRecycleView() {
        adapter.recyclerView = recyclerView

        assertTrue(adapter.hasStableIds())
        assertEquals(recyclerView, adapter.recyclerView)
    }

    @Test
    fun createViewHolder_ShouldInvokeAbstractMethod() {
        val parent = mock<ViewGroup>()
        val viewType = 1

        doReturn(context).whenever(parent).context
        adapter.onCreateViewHolder(parent, viewType)

        verify(adapter).onCreateViewHolder(same(parent), same(viewType))
    }

//    @Test
//    fun listedRecycleView_ShouldInvokeItemsComparator() {
//        adapter.submitList(pagedListOf("1", "2"))
//        adapter.submitList(pagedListOf("3", "4"))
//
//        verify(itemsSame, after(300).atLeastOnce()).invoke(anyString(), anyString())
//    }

    @Test
    fun listedRecycleView_ShouldInvokeContentComparator() {
        doReturn(true).whenever(itemsSame).invoke(anyString(), anyString())

        adapter.submitList(pagedListOf("1", "2"))
        adapter.submitList(pagedListOf("6", "4", "2"))

        verify(contentsSame, after(300).atLeastOnce()).invoke(anyString(), anyString())
    }

    @Test
    fun emptyRecycleView_ShouldNotInvokeAnyComparator() {
        verify(itemsSame, after(300).never()).invoke(anyString(), anyString())
        verify(contentsSame, after(300).never()).invoke(anyString(), anyString())
    }

    @Test
    fun attachedRecycleView_ShouldStoreInstance() {
        val attachedRecyclerView: RecyclerView = mock()

        adapter.recyclerView = recyclerView
        adapter.onAttachedToRecyclerView(attachedRecyclerView)

        assertEquals(attachedRecyclerView, adapter.recyclerView)
    }

    @Test
    fun detachedRecycleView_ShouldDestroyInstance() {
        val detachedRecyclerView: RecyclerView = mock()

        adapter.recyclerView = recyclerView
        adapter.onDetachedFromRecyclerView(detachedRecyclerView)

        assertNull(adapter.recyclerView)
    }

    @Test
    fun submitEmptyList_ShouldNotScrollUp() {
        val pagedList = mock<PagedList<String>>()
        doReturn(false).whenever(pagedList).isNullOrEmpty()

        adapter.recyclerView = recyclerView
        adapter.submitList(pagedList)

        verify(recyclerView, never()).scrollToPosition(anyInt())
    }

    @Test
    fun submitList_ShouldScrollUp() {
        val pagedList = mock<PagedList<String>>()
        doReturn(true).whenever(pagedList).isNullOrEmpty()

        adapter.recyclerView = recyclerView
        adapter.submitList(pagedList)

        verify(recyclerView).scrollToPosition(same(0))
    }

    @Test
    fun submitNull_ShouldScrollUp() {
        adapter.recyclerView = recyclerView
        adapter.submitList(null)

        verify(recyclerView).scrollToPosition(same(0))
    }

    @Test
    fun submitNull_ShouldNotScrollUp() {
        adapter.submitList(null)

        verify(recyclerView, never()).scrollToPosition(anyInt())
    }

    inner class TestBasePagedListAdapter : BasePagedListAdapter<String>(
        itemsSame = itemsSame, contentsSame = contentsSame
    ) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            viewHolder

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {}
    }
}
