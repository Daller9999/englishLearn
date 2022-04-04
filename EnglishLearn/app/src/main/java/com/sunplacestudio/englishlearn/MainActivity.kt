package com.sunplacestudio.englishlearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.sunplacestudio.englishlearn.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val navController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController
    }

    private val bottomNavigationView by lazy {
        findViewById<BottomNavigationView>(R.id.bottomNavigation)
    }

    private val itemSelectedListener = NavigationBarView.OnItemSelectedListener { item: MenuItem ->
        if (navController.currentDestination?.id != getDestinationById(item.itemId)) {
            when (item.itemId) {
                R.id.main_page -> navController.navigate(R.id.fragmentWords)
                R.id.learn -> navController.navigate(R.id.fragmentLearn)
                R.id.add_word -> navController.navigate(R.id.fragmentAddWord)
                R.id.search_word -> navController.navigate(R.id.fragmentSearchWord)
                R.id.etc -> navController.navigate(R.id.fragmentEtc)
            }
        }
        true
    }

    private fun getDestinationById(id: Int): Int? {
        return when (id) {
            R.id.main_page -> R.id.fragmentWords
            R.id.learn -> R.id.fragmentLearn
            R.id.add_word -> R.id.fragmentAddWord
            R.id.search_word -> R.id.fragmentSearchWord
            R.id.etc -> R.id.fragmentEtc
            else -> null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        bottomNavigationView.setOnItemSelectedListener(itemSelectedListener)
    }
}