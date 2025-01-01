package com.example.animationexample

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Reference to ImageView and Button
        val imageView: ImageView = findViewById(R.id.imageView)
        val button: Button = findViewById(R.id.button)

        // Set onClickListener for the button
        button.setOnClickListener {
            // Animate the ImageView with SCALE_X, SCALE_Y, TRANSLATION_X, and TRANSLATION_Y
            imageView.animate()
                .scaleX(2f) // Scale X to 2 times
                .scaleY(2f) // Scale Y to 2 times
                .translationXBy(200f) // Move X by 200 pixels
                .translationYBy(200f) // Move Y by 200 pixels
                .setDuration(1000) // Duration of animation (1 second)
                .start()
        }
    }
}
