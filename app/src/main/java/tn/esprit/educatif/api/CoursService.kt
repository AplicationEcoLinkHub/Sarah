package tn.esprit.educatif.api

import retrofit2.Call
import retrofit2.http.*
import tn.esprit.educatif.model.Cours

interface CoursService {
    @GET("cours")
    fun getAllCours(): Call<List<Cours>>

    @POST("cours")
    fun addCours(@Body cours: Cours): Call<Cours>

    @DELETE("cours/{id}")
    fun deleteCours(@Path("id") courseId: String): Call<Void>
}
