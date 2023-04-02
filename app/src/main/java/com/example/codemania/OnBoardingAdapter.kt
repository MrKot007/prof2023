package com.example.codemania

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnBoardingAdapter(fa: FragmentActivity, val list: List<Pair<String, Int>>) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return OnBoardingFragment(list[position].first, list[position].second)
    }
}