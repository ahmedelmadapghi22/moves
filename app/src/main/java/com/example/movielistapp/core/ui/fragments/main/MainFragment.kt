package com.example.movielistapp.core.ui.fragments.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.movielistapp.R
import com.example.movielistapp.core.ui.adapters.viewpageradapter.ViewPagerAdapter
import com.example.movielistapp.core.ui.fragments.popular.PopularFragment
import com.example.movielistapp.core.ui.fragments.toprated.TopRatedFragment
import com.example.movielistapp.databinding.FragmentMainBinding
import com.example.movielistapp.core.ui.fragments.BindingFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BindingFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {


    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        useBinding {
            it.apply {
                val adapter = ViewPagerAdapter(
                    listOf(PopularFragment(), TopRatedFragment()),
                    childFragmentManager,
                    lifecycle
                )
                viewPager.adapter = adapter
                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    when (position) {
                        0 -> {
                            tab.text = getString(R.string.popular)
                        }

                        1 -> {
                            tab.text = getString(R.string.top_rated)
                        }
                    }
                }.attach()


            }
        }

    }

}