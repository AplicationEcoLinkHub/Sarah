package tn.esprit.educatif.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.squareup.picasso.Picasso
import tn.esprit.educatif.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.educatif.QuizActivity
import tn.esprit.educatif.api.RetrofitClient
import tn.esprit.educatif.databinding.FragmentLearnBinding
import tn.esprit.educatif.model.Cours
import tn.esprit.educatif.ui.adapters.CoursAdapter

class LearnFragment : AppCompatActivity() {

    private lateinit var binding: FragmentLearnBinding
    private lateinit var coursAdapter: CoursAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLearnBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cours: Cours? = intent.extras?.getParcelable(ARG_COURS)

        cours?.let {
            with(binding) {
                title1.text = it.title
                txtAbout.text = it.header
                Picasso.get().load(it.titleImage).into(image)

                deleteButton.setOnClickListener {
                    val courseId = cours._id.toString()

                    if (courseId.isNotEmpty()) {
                        val deleteCall = RetrofitClient.apiService.deleteCours(courseId)
                        deleteCall.enqueue(object : Callback<Void> {
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                if (response.isSuccessful) {
                                    // Mettez à jour l'adaptateur après la suppression
                                    coursAdapter.removeCours(cours)
                                    // Terminez l'activité ou effectuez d'autres actions nécessaires après la suppression
                                    finish()
                                } else {
                                    // Gérer les erreurs de suppression
                                    Log.e("LearnFragment", "Erreur lors de la suppression du cours")
                                }
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                // Gérer les erreurs de réseau
                                Log.e("LearnFragment", "Erreur réseau lors de la suppression du cours", t)
                            }
                        })
                    }
                }
                quizButton.setOnClickListener {
                    val intent = Intent(this@LearnFragment, QuizActivity::class.java)
                    startActivity(intent)
                   // val quizFragment = QuizFragment()
                   // val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                    //transaction.replace(R.id.fragmentContainer, quizFragment)
                    //transaction.addToBackStack(null)
                    //transaction.commit()
                }
            }
        }
    }

    companion object {
        const val ARG_COURS = "arg_cours"
    }
}
