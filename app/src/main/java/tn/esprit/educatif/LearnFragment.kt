package tn.esprit.educatif

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class LearnFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_learn)

        val titlecours : TextView = findViewById(R.id.title1)
        val infoheader : TextView = findViewById(R.id.txtAbout)
        val imagecours : ImageView = findViewById(R.id.image)

        val bundel : Bundle? = intent.extras
        val title= bundel!!.getString("title")
        val imageId=bundel.getInt("imageId")
        val header = bundel.getString("cours")

        titlecours.text=title
        infoheader.text=header
        imagecours.setImageResource(imageId)
    }

}