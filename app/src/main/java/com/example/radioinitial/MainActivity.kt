package com.example.radioinitial

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.radioinitial.databinding.ActivityMainBinding
import android.media.MediaPlayer
import android.view.View

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        mediaPlayer = MediaPlayer()

        try {
            // Set the data source to the URL of the MP3 file
            mediaPlayer?.setDataSource("https://bguradio.co/listen/bguradio/radio.mp3")

            // Prepare the MediaPlayer asynchronously
            mediaPlayer?.prepareAsync()

            // Set up a listener to start playback when prepared
            mediaPlayer?.setOnPreparedListener {
                // Start playing the MP3 file
                it.start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    override fun onDestroy() {
        super.onDestroy()
        // Release the MediaPlayer instance when the app is destroyed
        mediaPlayer?.release()
    }
}