package tn.esprit.educatif

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.educatif.api.CoursService
import tn.esprit.educatif.model.Cours

class CoursRepository(private val coursService: CoursService){
    fun getAllCours(): LiveData<List<Cours>> {
        val data = MutableLiveData<List<Cours>>()

        coursService.getAllCours().enqueue(object : Callback<List<Cours>> {
            override fun onResponse(call: Call<List<Cours>>, response: Response<List<Cours>>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                } else {
                    // Handle error
                    Log.e("CoursRepository", "Error fetching courses: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Cours>>, t: Throwable) {
                // Handle failure
                Log.e("CoursRepository", "Network error: ${t.message}")
            }
        })

        return data
    }

    // Add a new course
    fun addCours(cours: Cours): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()

        coursService.addCours(cours).enqueue(object : Callback<Cours> {
            override fun onResponse(call: Call<Cours>, response: Response<Cours>) {
                result.value = response.isSuccessful
            }

            override fun onFailure(call: Call<Cours>, t: Throwable) {
                // Handle failure
                result.value = false
            }
        })

        return result
    }

    // Update an existing course
    fun updateCours(coursId: String, updatedCours: Cours): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()

        coursService.updateCours(coursId, updatedCours).enqueue(object : Callback<Cours> {
            override fun onResponse(call: Call<Cours>, response: Response<Cours>) {
                result.value = response.isSuccessful
            }

            override fun onFailure(call: Call<Cours>, t: Throwable) {
                // Handle failure
                result.value = false
            }
        })

        return result
    }

    // Delete a course
    fun deleteCours(coursId: String): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()

        coursService.deleteCours(coursId).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                result.value = response.isSuccessful
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Handle failure
                Log.e("CoursRepository", "Network error: ${t.message}")
                result.value = false
            }
        })

        return result
    }}
