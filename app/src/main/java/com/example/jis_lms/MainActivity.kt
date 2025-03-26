package com.example.jis_lms

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.jis_lms.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        replaceFragment(home_fragment())
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(home_fragment())
                R.id.payment -> replaceFragment(payment_fragment())
                R.id.profile -> replaceFragment(profile_fragment())
                R.id.lms -> replaceFragment(lms_fragment())
                R.id.menu -> replaceFragment(menu_fragment())

                else ->{

                }
            }
            true

        }


    }

    private fun replaceFragment(fragment:Fragment){
        val fragmentmanager = supportFragmentManager
        val fragmentTransction = fragmentmanager.beginTransaction()
        fragmentTransction.replace(R.id.frameLayout , fragment)
        fragmentTransction.commit()

    }


}