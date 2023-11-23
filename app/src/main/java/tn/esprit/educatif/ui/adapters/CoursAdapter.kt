package tn.esprit.educatif.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.educatif.databinding.ItemCoursBinding
import tn.esprit.educatif.model.Cours
import com.squareup.picasso.Picasso

class CoursAdapter(val coursList: MutableList<Cours>,
                   private val onFavoriteClickListener: OnFavoriteClickListener,
                   private val onShowMoreClickListener: OnShowMoreClickListener

) : RecyclerView.Adapter<CoursAdapter.CoursViewHolder>() {

    interface OnFavoriteClickListener {
        fun onFavoriteClick(cours: Cours)
    }

    interface OnShowMoreClickListener {
        fun onShowMoreClick(cours: Cours)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursViewHolder {
        val binding = ItemCoursBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoursViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoursViewHolder, position: Int) {
        with(holder) {
            with(coursList[position]) {
                Picasso.get().load(titleImage).into(binding.itemImage)
                binding.itemTitle.text = title
                binding.btnAddToFavorite.setOnClickListener {
                    onFavoriteClickListener.onFavoriteClick(this)
                }
                binding.btnShowMore.setOnClickListener {
                    onShowMoreClickListener.onShowMoreClick(this)
                }
            }
        }
    }

    override fun getItemCount(): Int = coursList.size
    fun updateList(newList: List<Cours>) {
        coursList.clear()
        coursList.addAll(newList)
        notifyDataSetChanged()
    }

    fun removeCours(cours: Cours) {
        val position = coursList.indexOf(cours)
        if (position != -1) {
            coursList.removeAt(position)
            notifyItemRemoved(position)
        }

    }

    inner class CoursViewHolder(val binding: ItemCoursBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}