package com.example.movielistapp.core.ui.fragments.search

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movielistapp.R
import com.example.movielistapp.core.ui.adapters.pagingAdapter.MoviesPagingAdapter
import com.example.movielistapp.core.ui.fragments.BindingFragment
import com.example.movielistapp.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BindingFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var moviesPagingAdapter: MoviesPagingAdapter
    private lateinit var arrayAdapter: ArrayAdapter<Int>
    private var yearsList: List<Int> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesPagingAdapter = MoviesPagingAdapter()
        yearsList = (1900..2024).toList()
        arrayAdapter =
            ArrayAdapter(requireContext(), R.layout.dropdown_menu, yearsList)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        useBinding {
            it.apply {

                autoCompleteTextView.setAdapter(arrayAdapter)
                autoCompleteTextView.setOnItemClickListener { adapterView, view, i, l ->
                    viewModel.searchMovies(yearsList[i], checkBoxAdult.isChecked)

                }
                checkBoxAdult.setOnCheckedChangeListener { compoundButton, b ->
                    if (b) {
                        viewModel.searchMovies(autoCompleteTextView.text.toString().toInt(), true)
                    } else {
                        viewModel.searchMovies(autoCompleteTextView.text.toString().toInt(), false)

                    }

                }

                lifecycleScope.launch {

                    viewLifecycleOwner.lifecycleScope.launch {
                        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                            viewModel.movies.observe(viewLifecycleOwner) { pagingData ->
                                rvSearch.layoutManager = GridLayoutManager(context, 2)
                                rvSearch.adapter = moviesPagingAdapter

                                moviesPagingAdapter.submitData(
                                    viewLifecycleOwner.lifecycle,
                                    pagingData
                                )
                            }

                            viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
                                if (isLoading) {
                                    loadingBar.visibility = View.VISIBLE
                                } else {
                                    loadingBar.visibility = View.GONE
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
        viewModel.searchMovies(2024, false)
    }

}