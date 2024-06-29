package com.example.movielistapp.core.ui.fragments.details

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.movielistapp.R
import com.example.movielistapp.core.ui.fragments.BindingFragment
import com.example.movielistapp.core.ui.fragments.SharedViewModel
import com.example.movielistapp.core.ui.uiState.DetailsMovieState
import com.example.movielistapp.core.ui.util.Constants.IMAGE_URL
import com.example.movielistapp.databinding.FragmentMovieDetailsBinding
import com.example.movielistapp.domain.models.MovieImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsFragment :
    BindingFragment<FragmentMovieDetailsBinding>(FragmentMovieDetailsBinding::inflate) {


    private val viewModel: MovieDetailsViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var startPosition = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        useBinding {
            it.apply {
                lifecycleScope.launch {
                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                        viewModel._mutableDetailsMovieState.collect { detailsMovieState ->
                            when (detailsMovieState) {
                                is DetailsMovieState.Loading -> {
                                    if (detailsMovieState.isLoading) {
                                        Log.d("dapgoooooooo", "VISIBLE")

                                        loadingBar.visibility = View.VISIBLE
                                    } else {
                                        Log.d("dapgoooooooo", "GONE")

                                        loadingBar.visibility = View.GONE

                                    }
                                }

                                is DetailsMovieState.Details -> {
                                    val movie = detailsMovieState.movie
                                    Log.d("dapgoooooooo", "Details: ${movie}")

                                    movie?.apply {
                                        tvMovieOverview.text = overview
                                        tvMovieTitle.text = title
                                        tvMovieVote.text = voteCount.toString()
                                        val moviesPosters = detailsMovieState.posters
                                        Log.d("dapgoooooooo", "moviesPosters: ${moviesPosters}")
                                        if (moviesPosters == null) {
                                            ivMoviesImages.setImageResource(R.drawable.ic_not_found)
                                        } else {
                                            Log.d("dapgoooooooo", "moviesPosters: ${movie}")

                                            startAnimationSequence(ivMoviesImages, moviesPosters)


                                        }


                                    }
                                }

                                is DetailsMovieState.Error -> {
                                    makeToastFromStr(detailsMovieState.errMsg)
                                }

                                is DetailsMovieState.ResError -> {
                                    makeToastFromRes(detailsMovieState.errMsg)
                                }

                            }


                        }
                    }
                }

            }
        }
    }

    private fun startAnimationSequence(imageView: ImageView, posters: List<MovieImage>) {
        lifecycleScope.launch {
            while (true) {
                val fadeOut = AlphaAnimation(1.0f, 0.0f).apply {
                    duration = 1000
                    setAnimationListener(object : Animation.AnimationListener {
                        override fun onAnimationStart(animation: Animation) {}
                        override fun onAnimationEnd(animation: Animation) {
                            if (posters.isNotEmpty()) {
                                val posterPath = posters[startPosition].imagePath
                                Glide.with(requireContext())
                                    .load("$IMAGE_URL$posterPath")
                                    .placeholder(R.drawable.ic_not_found)
                                    .into(imageView)

                                val fadeIn = AlphaAnimation(0.0f, 1.0f).apply {
                                    duration = 1000
                                }
                                imageView.startAnimation(fadeIn)

                                startPosition = (startPosition + 1) % posters.size
                            }
                        }

                        override fun onAnimationRepeat(animation: Animation) {}
                    })
                }
                imageView.startAnimation(fadeOut)
                delay(2000)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("dapgoooooooo", "onStart: ${sharedViewModel.getMovie()}")
        viewModel.bindDetails(sharedViewModel.getMovie())
    }


}