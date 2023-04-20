package com.expv1n.myfilmsapp.presentation

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.expv1n.myfilmsapp.databinding.FragmentDetailedInfoBinding
import com.expv1n.myfilmsapp.domain.models.Country
import com.expv1n.myfilmsapp.domain.models.Film
import com.expv1n.myfilmsapp.domain.models.FilmDetail
import com.expv1n.myfilmsapp.domain.models.Genre
import com.expv1n.myfilmsapp.presentation.state.DetailError
import com.expv1n.myfilmsapp.presentation.state.DetailProgress
import com.expv1n.myfilmsapp.presentation.state.DetailResult
import com.expv1n.myfilmsapp.presentation.viewmodel.DetailInfoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.lang.StringBuilder



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

    private fun settingViewValue(movieDetails: FilmDetail) {
        movieDetails.let {
            binding.apply {
                Glide.with(requireActivity()).load(movieDetails.posterUrl).into(titleImageView)
                titleMovieTextView.text = it.nameRu
                descriptionMovieTextView.text = it.description
                genresMovieTextView.text = getGenres(it.genres)
                countriesMovieTextView.text = getCountries(it.countries)
            }
        }
    }

    private fun getGenres(genres: List<Genre>): String {
        val result = StringBuilder()
        result.append("Жанры: ")
        for ((i, tmp) in genres.withIndex()) {
            result.append(tmp.genre)
            if (i + 1 != genres.size) {
                result.append(", ")
            } else {
                result.append(".")
            }
        }
        return result.toString()
    }

    private fun getCountries(countries: List<Country>): String {
        val result = StringBuilder()
        result.append("Страны: ")
        for ((i, tmp) in countries.withIndex()) {
            result.append(tmp.country)
            if (i + 1 != countries.size) {
                result.append(", ")
            } else {
                result.append(".")
            }
        }
        return result.toString()
    }

    private fun observeViewModel() {
        viewModel.getDetailInfo(parseFilm())
        viewModel.stateDetail.observe(requireActivity()) {
            when (it) {
                is DetailError -> {
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
                }
            }
        }
    }

    companion object {
        const val PARSE_KEY = "DetailedInfoFragment"
        const val FRAGMENT_NAME = "DetailedInfoFragment"
        fun getInstance(movie: Film): DetailedInfoFragment {
            return DetailedInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PARSE_KEY, movie)
                }
            }
        }
    }

}