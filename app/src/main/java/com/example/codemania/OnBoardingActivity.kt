package com.example.codemania

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.codemania.databinding.ActivityOnBoardingBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragments: List<Pair<String, Int>> = listOf(
            Pair("Без теории, только практика\n" +
                    "Вы не платите за лекции и теоретический материал, ведь все\n"+" это можно найти в интернете бесплатно", R.drawable.post1),
            Pair("Без теории, только практика\n" +
                    "Вы не платите за лекции и теоретический материал, ведь все\n"+"это можно найти в интернете бесплатно", R.drawable.post2),
            Pair("Обучение онлайн из любой точки мира\n" +
                    "Наше обучение изначально создавалось как дистанционное", R.drawable.post3)
        )
        binding.boardingPager.adapter = OnBoardingAdapter(this@OnBoardingActivity, fragments)
        binding.boardingPager.isUserInputEnabled = false
        TabLayoutMediator(binding.tabs, binding.boardingPager) {
            tab, pos ->
            run {
                tab.view.isClickable = false
            }
        }.attach()
        binding.nextScreen.setOnClickListener {
            if (binding.boardingPager.currentItem == 1) {
                binding.nextScreenText.text = "Начать"
            }
            if (binding.boardingPager.currentItem == 2) {
                //startActivity(Intent(this@OnBoardingActivity, LogInActivity::class.java))
            }
            binding.boardingPager.setCurrentItem(binding.boardingPager.currentItem + 1)
        }

    }
}