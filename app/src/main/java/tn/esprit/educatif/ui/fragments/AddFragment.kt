package tn.esprit.educatif.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import tn.esprit.educatif.R
import tn.esprit.educatif.api.CoursService
import tn.esprit.educatif.api.RetrofitClient
import tn.esprit.educatif.model.Cours
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.educatif.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var coursService: CoursService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize your CoursService
        coursService = RetrofitClient.apiService

        // Initialize your views here
        with(binding) {
            imageView.setOnClickListener {
                // Handle image selection using Intent
                // Example: startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
            }

            add1.setOnClickListener {
                addCours()
            }
        }
    }

    private fun addCours() {
        val title = binding.editTextTitle.text.toString().trim()
        val header = binding.editTextHeader.text.toString().trim()
        val titleImage = binding.editTextHeader.text.toString().trim()

        if (title.isNotEmpty() && header.isNotEmpty()) {
            val cours = Cours("your_id", titleImage, title, header, false)
            showProgressBar(true)

            coursService.addCours(cours).enqueue(object : Callback<Cours> {
                override fun onResponse(call: Call<Cours>, response: Response<Cours>) {
                    showProgressBar(false)

                    if (response.isSuccessful) {
                        binding.editTextTitle.text.clear()
                        binding.editTextHeader.text.clear()
                        parentFragmentManager.popBackStack()
                    } else {
                        showError("Error: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<Cours>, t: Throwable) {
                    showProgressBar(false)
                    showError("Error: ${t.message}")
                }
            })
        } else {
            showError("Fields cannot be empty")
        }
    }

    private fun showProgressBar(show: Boolean) {
        // Show or hide the progress bar based on the 'show' parameter
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showError(message: String) {
        // You can implement your error handling logic here, such as showing a Toast or Snackbar
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
