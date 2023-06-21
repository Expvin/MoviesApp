package com.expv1n.myfilmsapp.presentation.DetailMovie

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.expv1n.myfilmsapp.R
import com.expv1n.myfilmsapp.databinding.FragmentDetailedInfoBinding
import com.expv1n.myfilmsapp.domain.models.Film
import com.expv1n.myfilmsapp.domain.models.MovieEntity
import com.expv1n.myfilmsapp.presentation.PopularMovies.PopularMoviesFragment
import com.expv1n.myfilmsapp.presentation.state.DetailError
import com.expv1n.myfilmsapp.presentation.state.DetailProgress
import com.expv1n.myfilmsapp.presentation.state.DetailResult

// TODO room database and add favorite and delete
class DetailedInfoFragment : Fragment() {

    private var _binding: FragmentDetailedInfoBinding? = null
    private val binding: FragmentDetailedInfoBinding
        get() = _binding ?: throw RuntimeException("Unknown binding")

    private val viewModel by lazy {
        ViewModelProvider(this)[DetailInfoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailedInfoBinding.inflate(layoutInflater, container, false)
        observeViewModel()
        buttonInit()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun parseFilm(): Long {
        requireArguments().getParcelable<Film>(PARSE_KEY)?.let {
            return it.filmId.toLong()
        }
        return -1
    }

    private fun settingViewValue(movieDetails: MovieEntity) {
        movieDetails.let {
            binding.apply {
                Glide.with(requireActivity()).load(movieDetails.posterUrl).into(titleImageView)
                titleMovieTextView.text = it.name
                descriptionMovieTextView.text = it.description
                genresMovieTextView.text = it.genre
                countriesMovieTextView.text = it.country
            }
        }
    }

    private fun observeViewModel() {
        viewModel.getDetailInfo(parseFilm())
        viewModel.stateDetail.observe(requireActivity()) {
            when (it) {
                is DetailError -> {
                    //TODO error button
                    binding.progressBar.visibility = View.GONE
                    binding.errorImageView.visibility = View.VISIBLE
                    binding.errorTextView.visibility = View.VISIBLE
                    binding.errorRepeatButton.visibility = View.VISIBLE
                }
                is DetailProgress -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is DetailResult -> {
                    binding.progressBar.visibility = View.GONE
                    settingViewValue(it.result)
                    isFavorite(it.result)
                }
            }
        }
    }

    private fun launchFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.mainFragmentContainerView, PopularMoviesFragment.getInstance())
            .commit()
    }

    // TODO add database and delete database
    private fun isFavorite(movie: MovieEntity) {
        if (movie.isFavorite) {
            createFavoriteButton(R.drawable.trash, R.string.delete_is_favorite)
            binding.addFavoriteButton.setOnClickListener {
                viewModel.deleteFromFavorite(movie)
            }
        } else {
            createFavoriteButton(R.drawable.likes, R.string.add_to_favorite)
            binding.addFavoriteButton.setOnClickListener {
                viewModel.addToFavorite(movie)
            }
        }
    }

    private fun createFavoriteButton(drawable: Int, text: Int) {
        val icon = requireActivity().resources.getDrawable(drawable)
//        binding.addFavoriteButton.text = getString(text)
//        binding.addFavoriteButton.setCompoundDrawables(icon,
//            null, null, null)
//        binding.addFavoriteButton.visibility = View.VISIBLE
    }

    private fun buttonInit() {
        binding.backImageButton.setOnClickListener {
            launchFragment()
        }
    }
    companion object {
        const val PARSE_KEY = "DetailedInfoFragment"
        fun getInstance(film: Film): DetailedInfoFragment {
            return DetailedInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PARSE_KEY, film)
                }
            }
        }
    }

}