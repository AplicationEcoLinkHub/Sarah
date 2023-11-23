package tn.esprit.educatif.model

class Quiz1(var question: String, var answer1: String, var answer2: String, var answer3: String, var correctAnswerNumber: Int) {
    fun isCorrect(answerNumber: Int): Boolean{
        if (answerNumber == correctAnswerNumber)
            return true
        return false
    }
}