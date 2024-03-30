package com.example.onboardingpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.viewpager2.widget.ViewPager2
import com.example.onboardingpage.databinding.ActivityOnboardingBinding
import kotlin.math.abs
import kotlin.math.max

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    private val image = intArrayOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val SliderAdapter = SliderAdapter(image, this@OnboardingActivity)

        binding.apply {
            viewPager.apply {
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
                adapter = SliderAdapter
                offscreenPageLimit = 3
                val pageMargin = resources.getDimensionPixelOffset(R.dimen.pageMargin).toFloat()
                val pageOffset = resources.getDimensionPixelOffset(R.dimen.offset).toFloat()
                wormDotsIndicator.attachTo(viewPager)
                currentItem = 1

                setPageTransformer{page, position ->
                    val myOffset = position * - (2 * pageOffset + pageMargin)
                    if (position < - 1)
                    {
                        page.translationX = - myOffset
                    }
                    else if (position <= 1)
                    {
                        val scaleFactor = max(0.7f , 1 - abs(position - 0.14285715f))
                        page.translationX = myOffset
                        page.scaleY = scaleFactor
                        page.alpha = scaleFactor
                    }
                    else
                    {
                        page.alpha = 0f
                        page.translationX = myOffset
                    }
                }

            }
        }

        binding.btnHome.setOnClickListener {
            startActivity(Intent(this@OnboardingActivity, MainActivity::class.java))
            finish()
        }

    }
}