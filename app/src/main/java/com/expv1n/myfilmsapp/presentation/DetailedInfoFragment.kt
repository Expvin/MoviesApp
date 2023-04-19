package com.expv1n.myfilmsapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.expv1n.myfilmsapp.databinding.FragmentDetailedInfoBinding
import com.expv1n.myfilmsapp.domain.models.Film
import com.expv1n.myfilmsapp.domain.models.FilmDetail
import com.expv1n.myfilmsapp.presentation.viewmodel.DetailInfoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


//TODO Показать ProgressBar загрузки
class DetailedInfoFragment : Fragment() {

    private var _binding: FragmentDetailedInfoBinding? = null
    private val binding: FragmentDetailedInfoBinding
        get() = _binding ?: throw RuntimeException("Unknown binding")

    private val viewModel by lazy {
        ViewModelProvider(this)[DetailInfoViewModel::class.java]
    }
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var movieDetails: FilmDetail? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMovieDetails()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailedInfoBinding.inflate(layoutInflater, container, false)
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

    private fun settingViewValue() {
        movieDetails?.let {
            binding.apply {
                Glide.with(requireActivity()).load(movieDetails!!.posterUrl).into(titleImageView)
                titleMovieTextView.text = it.nameRu
                descriptionMovieTextView.text = it.description
                genresMovieTextView.text = it.genres.toString()
                countriesMovieTextView.text = it.countries.toString()
            }
        }
    }

    private fun getMovieDetails() {
        val filmId = parseFilm()
        coroutineScope.async {
            viewModel.getDetailInfo(filmId)
            viewModel.getDetailFilmLiveData.observe(requireActivity()) {
                movieDetails = it
                settingViewValue();
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