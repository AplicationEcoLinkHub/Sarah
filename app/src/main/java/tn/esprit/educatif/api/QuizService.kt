package tn.esprit.educatif.api

import retrofit2.http.*
import retrofit2.Call
import tn.esprit.educatif.model.Quiz

interface QuizService {
    @GET("quiz")
    fun getAllQuizzes(): Call<List<Quiz>>
}
