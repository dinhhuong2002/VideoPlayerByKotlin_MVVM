package com.example.videoplayerbykotlin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.videoplayerbykotlin.databinding.ActivityMainBinding
import com.example.videoplayerbykotlin.ui.home.HomeFragment
import com.example.videoplayerbykotlin.ui.list.ListFragment
import com.example.videoplayerbykotlin.ui.setting.SettingFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG = "VideoPlayer"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnClickListener {item ->
            when (item.id) {
                R.id.navigation_home -> {
                    replaceFragment(HomeFragment())
                    Toast.makeText(applicationContext, "Home", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigation_list -> {
                    replaceFragment(ListFragment())
                    Toast.makeText(applicationContext, "Playlist", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "PlaylistFragment")
                    true
                }
                R.id.navigation_setting -> {
                    replaceFragment(SettingFragment())
                    Toast.makeText(applicationContext, "Notifications", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }


    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_main, fragment)
        fragmentTransaction.commit()
    }
}