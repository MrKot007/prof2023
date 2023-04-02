package com.example.codemania

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.codemania.databinding.FragmentBoardingBinding

class OnBoardingFragment(val text: String, val img: Int) : Fragment() {
    private lateinit var bindingFragment: FragmentBoardingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFragment = FragmentBoardingBinding.inflate(layoutInflater)
        return bindingFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindingFragment.boardingText.text = this.text
        bindingFragment.img.setImageResource(this.img)
    }
}