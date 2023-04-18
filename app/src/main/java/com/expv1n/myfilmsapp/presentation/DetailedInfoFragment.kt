package com.expv1n.myfilmsapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.expv1n.myfilmsapp.databinding.FragmentDetailedInfoBinding
import com.expv1n.myfilmsapp.domain.models.Film

class DetailedInfoFragment : Fragment() {

    private var _binding: FragmentDetailedInfoBinding? = null
    private val binding: FragmentDetailedInfoBinding
        get() = _binding ?: throw RuntimeException("Unknown binding")

    private lateinit var film: Film

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseFilm()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailedInfoBinding.inflate(layoutInflater, container, false)
        settingViewValue();
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun parseFilm() {
        requireArguments().getParcelable<Film>(PARSE_KEY)?.let {
            film = it
        }
    }

    private fun settingViewValue() {
        film.let {
            binding.apply {
                Glide.with(requireActivity()).load(film.posterUrl).into(titleImageView)
                titleMovieTextView.text = it.nameRu
                descriptionMovieTextView.text = it.nameEn
                genresMovieTextView.text = it.genres.toString()
                countriesMovieTextView.text = it.countries.toString()
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