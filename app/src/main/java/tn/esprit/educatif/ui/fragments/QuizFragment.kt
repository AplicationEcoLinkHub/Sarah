package tn.esprit.educatif.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.educatif.ui.adapters.QuizAdapter
import tn.esprit.educatif.api.RetrofitClient
import tn.esprit.educatif.databinding.QuizActivityBinding
import tn.esprit.educatif.model.Quiz


class QuizFragment : Fragment() {
    private lateinit var binding: QuizActivityBinding
    private lateinit var quizAdapter: QuizAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = QuizActivityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialiser l'adaptateur avec une liste vide
        quizAdapter = QuizAdapter(mutableListOf())
        binding.quiz.adapter = quizAdapter
        binding.quiz.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        Log.e("Fragment QUIZ", quizAdapter.quizList.toString())
        fetchDataFromApi()
    }

    private fun fetchDataFromApi() {
        val api = RetrofitClient.quizService
        val call = api.getAllQuizzes()
        call.enqueue(object : Callback<List<Quiz>> {
            override fun onResponse(call: Call<List<Quiz>>, response: Response<List<Quiz>>) {
                if (response.isSuccessful) {
                    val quiz: List<Quiz>? = response.body()?.toMutableList()
                    Log.e("FRAGMENT QUIZ", response.body().toString())
                    quizAdapter.updateList(quiz ?: emptyList())
                } else {
                    Log.d("aaaaaaa", (response ?: "Empty response").toString())
                }
            }

            override fun onFailure(call: Call<List<Quiz>>, t: Throwable) {
                Log.e("onFailure", t.toString())
            }
        })
    }
}