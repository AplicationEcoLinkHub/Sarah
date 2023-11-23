package tn.esprit.educatif.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.LinearLayoutManager
import tn.esprit.educatif.R
import tn.esprit.educatif.api.RetrofitClient
import tn.esprit.educatif.databinding.FragmentCoursBinding
import tn.esprit.educatif.model.Cours
import tn.esprit.educatif.ui.adapters.CoursAdapter

class CoursFragment : Fragment(), CoursAdapter.OnFavoriteClickListener, CoursAdapter.OnShowMoreClickListener {
    private lateinit var binding: FragmentCoursBinding
    private lateinit var coursAdapter: CoursAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCoursBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the adapter with an empty list
        coursAdapter = CoursAdapter(mutableListOf(), this, this)
        binding.rvStore.adapter = coursAdapter
        binding.rvStore.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        Log.e("Fragment COURS", coursAdapter.coursList.toString())
        fetchDataFromApi()

        // Set OnClickListener for FloatingActionButton
        binding.btnAddCours.setOnClickListener {
            val addFragment = AddFragment()
            // Replace the current fragment with AddFragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.context_view, addFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun fetchDataFromApi() {
        val api = RetrofitClient.apiService
        val call = api.getAllCours()
        call.enqueue(object : Callback<List<Cours>> {
            override fun onResponse(call: Call<List<Cours>>, response: Response<List<Cours>>) {
                if (response.isSuccessful) {
                    val cours: List<Cours>? = response.body()?.toMutableList()
                    Log.e("FRAGMENT COURS API", response.body().toString())
                    coursAdapter.updateList(cours ?: emptyList())
                } else {
                    Log.d("aaaaaaa", (response ?: "Empty response").toString())
                }
            }

            override fun onFailure(call: Call<List<Cours>>, t: Throwable) {
                Log.e("onFailure", t.toString())
            }
        })
    }

    override fun onFavoriteClick(cours: Cours) {
        val favoriteFragment = FavoriteFragment.newInstance(cours)

        // Replace the current fragment with FavoriteFragment
        childFragmentManager.beginTransaction().replace(R.id.context_view, favoriteFragment)
            .addToBackStack(null).commit()
    }

    override fun onShowMoreClick(cours: Cours) {
        val intent = Intent(requireContext(), LearnFragment::class.java)
        intent.putExtra(LearnFragment.ARG_COURS, cours)
        startActivity(intent)
    }
}