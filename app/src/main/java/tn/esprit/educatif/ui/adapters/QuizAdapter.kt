package tn.esprit.educatif.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.educatif.databinding.QuizItemBinding
import tn.esprit.educatif.model.Quiz

class QuizAdapter(val quizList: MutableList<Quiz>) : RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val binding = QuizItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuizViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        with(holder) {
            with(quizList[position]) {
                binding.QuizNo.text = "Quiz No : ${position + 1}"
                binding.scoretext.text = "$topScore%" // Assuming you want to append "%"
                binding.quizProgressBar.progress = topScore.toIntOrNull() ?: 0
            }
        }
    }

    override fun getItemCount(): Int = quizList.size

    fun updateList(newList: List<Quiz>) {
        quizList.clear()
        quizList.addAll(newList)
        notifyDataSetChanged()
    }

    inner class QuizViewHolder(val binding: QuizItemBinding) : RecyclerView.ViewHolder(binding.root)
}