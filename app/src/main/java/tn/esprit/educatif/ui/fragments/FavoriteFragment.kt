package tn.esprit.educatif.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import tn.esprit.educatif.R
import tn.esprit.educatif.databinding.FragmentFavoriteBinding
import tn.esprit.educatif.model.Cours

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding

    companion object {
        const val ARG_COURS = "COURS"

        fun newInstance(cours: Cours): FavoriteFragment {
            val fragment = FavoriteFragment()
            val bundle = Bundle().apply {
                putParcelable(ARG_COURS, cours)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val cours: Cours? = requireArguments().getParcelable(ARG_COURS)

        cours?.let {
            Log.d("FavoriteFragment", "Cours details: $it")
        }

        return binding.root
    }
}

