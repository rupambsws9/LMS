package com.example.jis_lms

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.jis_lms.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var fragmentmanager:FragmentManager

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(this , binding.drawerLayout , binding.toolbar , R.string.nav_open , R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)

        binding.bottomNavigation.background= null
        binding.bottomNavigation.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.bottom_home->openfragment(homefragment())
                R.id.bottom_profile -> openfragment(profilefragment())
                R.id.bottom_cart -> openfragment(cartfragment())
                R.id.bottom_menu -> openfragment(menufragment())
            }
            true
        }

        fragmentmanager = supportFragmentManager
        openfragment(homefragment())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.nav_prime -> openfragment(profilefragment())
            R.id.nav_fashion -> openfragment(fashionfragment())
            R.id.nav_electronics -> openfragment(electronicsfragment())
            R.id.nav_fresh -> openfragment(freshfragment())
            R.id.nav_beauty -> Toast.makeText(this, "i am beauty", Toast.LENGTH_SHORT).show()
            R.id.nav_furniture -> Toast.makeText(this, "i am furniture", Toast.LENGTH_SHORT).show()
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressedDispatcher.onBackPressed()
        }
    }
    private fun openfragment(fragment: Fragment){
        val fragmentTransaction:FragmentTransaction = fragmentmanager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container , fragment)
        fragmentTransaction.commit()
    }

}