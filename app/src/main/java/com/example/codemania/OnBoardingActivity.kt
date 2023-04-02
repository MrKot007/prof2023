package com.example.codemania

import android.content.Intent
import android.icu.lang.UCharacter.VerticalOrientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.codemania.databinding.ActivityOnBoardingBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var bbinding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bbinding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(bbinding.root)
        val fragments: List<Pair<String, Int>> = listOf(
            Pair("Без теории, только практика\n" +
                    "Вы не платите за лекции и теоретический материал, ведь все\n"+" это можно найти в интернете бесплатно", R.drawable.post1),
            Pair("Без теории, только практика\n" +
                    "Вы не платите за лекции и теоретический материал, ведь все\n"+"это можно найти в интернете бесплатно", R.drawable.post2),
            Pair("Обучение онлайн из любой точки мира\n" +
                    "Наше обучение изначально создавалось как дистанционное", R.drawable.post3)
        )
        bbinding.boardingPager.adapter = OnBoardingAdapter(this@OnBoardingActivity, fragments)
        bbinding.boardingPager.isUserInputEnabled = false
        bbinding.boardingPager.orientation = ViewPager2.ORIENTATION_VERTICAL
        TabLayoutMediator(bbinding.tabs, bbinding.boardingPager) {
            tab, pos ->
            run {
                tab.view.isClickable = false
            }
        }.attach()
        bbinding.enter.setOnClickListener {
            if (bbinding.boardingPager.currentItem == 1) {
                bbinding.nextScreenText.text = "Начать"
            }
            if (bbinding.boardingPager.currentItem == 2) {
                startActivity(Intent(this@OnBoardingActivity, LogInActivity::class.java))
                finish()
            }
            bbinding.boardingPager.setCurrentItem(bbinding.boardingPager.currentItem + 1)
        }

    }
}