package com.example.flupperapp

import android.content.Intent
import android.os.Bundle
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val videoView = findViewById<VideoView>(R.id.videoView)
        // Укажи путь к видео
        val videoPath = "android.resource://${packageName}/${R.raw.splash_screen}"
        videoView.setVideoPath(videoPath)

        // Запусти воспроизведение
        videoView.setOnPreparedListener {
            android.util.Log.d("SplashActivity", "Video prepared, starting playback")
            // Убираем белый фон, чтобы видео стало видно
            videoView.setBackgroundColor(android.graphics.Color.TRANSPARENT)
            videoView.start()
        }

        // Перейди в MainActivity после завершения видео
        videoView.setOnCompletionListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}