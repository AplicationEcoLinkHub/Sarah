package tn.esprit.educatif

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import tn.esprit.educatif.databinding.ActivityMainBinding
import tn.esprit.educatif.ui.fragments.CoursFragment



class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item -> when (item.itemId) {
                //R.id.home-> replaceFragment(HomeFragment())
                R.id.cours -> replaceFragment(CoursFragment())
                //R.id.ic_zaineb -> replaceFragment(NotificationsFragment())
                //R.id.ic_achref -> replaceFragment(NotificationsFragment())
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.context_view, fragment).commit()
    }
}

