package com.example.movielistapp.core.ui.fragments.welcome

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.movielistapp.R
import com.example.movielistapp.core.ui.fragments.BindingFragment
import com.example.movielistapp.core.ui.uiState.WelcomeScreenState
import com.example.movielistapp.databinding.FragmentWelcomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class WelcomeFragment : BindingFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {


    private val viewModel: WelcomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        useBinding {
            it.apply {
                mainNavLayout.apply {
                    cardMain.setOnClickListener {
                        findNavController().navigate(R.id.action_welcomeFragment_to_mainFragment)
                    }
                    cardSearch.setOnClickListener {
                        findNavController().navigate(R.id.action_welcomeFragment_to_mainFragment)
                    }
                }
                lifecycleScope.launch {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                        viewModel._mutableWelcomeScreenState.collect { welcomeScreenState ->
                            when (welcomeScreenState) {
                                is WelcomeScreenState.Loading -> {
                                    tvState.text = "Popular"

                                    if (welcomeScreenState.isLoading) {
                                        Log.d("dapgoooo", "Laoding")

                                        loadingBar.visibility = View.VISIBLE
                                    } else {
                                        Log.d("dapgoooo", "finish Laoding")

                                        loadingBar.visibility = View.GONE

                                    }
                                }

                                is WelcomeScreenState.Popular -> {
                                    Log.d("dapgoooo", "Popular")

                                    tvState.text = "Top Rated"

                                    viewModel.getTopRatedMovie()
                                }

                                is WelcomeScreenState.TopRated -> {
                                    Log.d("dapgoooo", "TopRated")

                                    tvState.text = "saveCacheTime"
                                    viewModel.saveCacheTime()

                                }


                                is WelcomeScreenState.CacheTime -> {
                                    if (welcomeScreenState.isSaved) {
                                        ivLogo.visibility = View.GONE
                                        loadingBar.visibility = View.GONE
                                        tvState.visibility = View.GONE
                                        mainNavLayout.root.visibility = View.VISIBLE
                                    }

                                }

                                is WelcomeScreenState.Error -> {
                                    makeToastFromStr(welcomeScreenState.errMsg)
                                }

                                is WelcomeScreenState.ResError -> {
                                    makeToastFromRes(welcomeScreenState.errMsg)
                                }
                            }
                        }


                    }

                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        viewModel.inValidateData()

    }

    override fun onStop() {
        super.onStop()
        viewModel.inValidateData()

    }

}