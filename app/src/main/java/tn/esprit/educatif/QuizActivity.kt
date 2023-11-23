package tn.esprit.educatif

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import tn.esprit.educatif.model.Quiz1
import tn.esprit.educatif.databinding.ActivityQuiz1Binding

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuiz1Binding

    var quizs = ArrayList<Quiz1>()
    var numberOfGoodAnswers: Int = 0
    var currentQuizIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityQuiz1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        quizs.add(Quiz1("Quels sont les nutriments essentiels que l'on trouve généralement dans les fruits et légumes?",
                "a) Protéines et lipides","b) Fibres, potassium, vitamine C et folates","c) Glucides simples et graisses saturées",2))
        quizs.add(Quiz1("Pourquoi les fruits et légumes sont-ils considérés comme bénéfiques pour la santé?",
            "a) Ils contiennent des milliers de composés chimiques artificiels.","b) Ils sont riches en graisses saturées.","c) c) Ils sont sources de fibres, de vitamine C, de potassium et de composés phytochimiques protecteurs.",3))
        quizs.add(Quiz1("Quel rôle jouent les composés phytochimiques présents dans les fruits et légumes?",
            "a) Ils sont responsables du goût sucré des fruits.","b) Ils protègent contre les maladies.","c) Ils n'ont aucun effet sur la santé.",2))
        showQuestion(quizs.get(currentQuizIndex))
    }

    fun showQuestion(quiz: Quiz1){
        binding.txtQuestion.text = quiz.question
        binding.answer1.text = quiz.answer1
        binding.answer2.text = quiz.answer2
        binding.answer3.text = quiz.answer3
    }

    fun handelAnswer(answerID: Int){
        val quiz = quizs.get(currentQuizIndex)
        if(quiz.isCorrect(answerID)){
            numberOfGoodAnswers ++
            Toast.makeText(this,"+1", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"0",Toast.LENGTH_SHORT).show()
        }
        currentQuizIndex++
        if(currentQuizIndex>= quizs.size){
            var alert = AlertDialog.Builder(this)
            alert.setTitle("quiz terminé")
            alert.setMessage("tu as eu:"+numberOfGoodAnswers+" bonne(s) reponse(s)")
            alert.setPositiveButton("ok") { dialogInterface: DialogInterface?, i:Int->
                finish()
            }
            alert.show()
        }else{
            showQuestion(quizs.get(currentQuizIndex))
        }
    }
    fun onClickAnswerOne(view: View){
        handelAnswer(1)
    }
    fun onClickAnswerTwo(view: View){
        handelAnswer(2)
    }
    fun onClickAnswerThree(view: View){
        handelAnswer(3)
    }
}