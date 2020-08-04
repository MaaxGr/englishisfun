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

package com.jpaya.englishisfun.irregulars.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.transition.TransitionManager
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.extensions.exhaustive
import com.jpaya.englishisfun.R
import com.jpaya.englishisfun.extensions.hide
import com.jpaya.englishisfun.extensions.show
import com.jpaya.englishisfun.irregulars.ui.adapter.IrregularsAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.android.synthetic.main.irregulars_fragment_list.*

@AndroidEntryPoint
class IrregularsListFragment :
    RainbowCakeFragment<ListViewState, IrregularsListViewModel>(), IrregularsAdapter.Listener {

    @Inject
    lateinit var irregularsAdapter: IrregularsAdapter

    private val customViewModel: IrregularsListViewModel by viewModels()

    override fun provideViewModel() = customViewModel
    override fun getViewResource() = R.layout.irregulars_fragment_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        irregularsAdapter.listener = this
        irregularsList.adapter = irregularsAdapter
        irregularsList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        retryButton.setOnClickListener {
            viewModel.reload()
        }
        viewSavedButton.setOnClickListener {
//            navigator?.add(SavedFragment())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_view_saved -> true // navigator?.add(SavedFragment())
            else -> false
        }

    override fun render(viewState: ListViewState) {
        TransitionManager.beginDelayedTransition(listFragmentRoot)
        when (viewState) {
            Loading -> {
                shimmerLayout.show()
                irregularsList.isVisible = false
                errorGroup.isVisible = false
            }
            is ListReady -> {
                irregularsAdapter.submitList(viewState.irregulars)
                shimmerLayout.hide()
                irregularsList.isVisible = true
                errorGroup.isVisible = false
            }
            NetworkError -> {
                irregularsAdapter.submitList(null)
                shimmerLayout.hide()
                irregularsList.isVisible = false
                errorGroup.isVisible = true
            }
        }.exhaustive
    }

    override fun onItemSelected(id: Long) {
//        navigator?.add(DetailFragment.newInstance(id))
    }
}
