package com.example.question2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var clickCount = 0 // Counter to track the number of clicks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Reference to ImageView and Button
        val imageView: ImageView = findViewById(R.id.imageView)
        val animateButton: Button = findViewById(R.id.button)

        // Set onClickListener for the button
        animateButton.setOnClickListener(View.OnClickListener {
            clickCount++ // Increment click count

            when (clickCount) {
                1 -> {
                    // Scale X
                    imageView.animate()
                        .scaleX(2f)
                        .setDuration(1000)
                        .start()
                }
                2 -> {
                    // Scale Y
                    imageView.animate()
                        .scaleY(2f)
                        .setDuration(1000)
                        .start()
                }
                3 -> {
                    // Translate X
                    imageView.animate()
                        .translationX(200f)
                        .setDuration(1000)
                        .start()
                }
                4 -> {
                    // Translate Y
                    imageView.animate()
                        .translationY(200f)
                        .setDuration(1000)
                        .start()
                }
                5 -> {
                    // Reset to original state
                    imageView.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .translationX(0f)
                        .translationY(0f)
                        .setDuration(1000)
                        .start()
                    clickCount = 0 // Reset the click count
                }
            }
        })
    }
}
