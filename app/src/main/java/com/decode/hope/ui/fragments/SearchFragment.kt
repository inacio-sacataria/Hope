package com.decode.hope.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.decode.hope.R
import com.decode.hope.databinding.FragmentSearchBinding
import com.decode.hope.util.changeIconColor


class SearchFragment : Fragment() {


   lateinit var binding : FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_search, container, false)
        binding = FragmentSearchBinding.bind(view)



        return binding.root
    }

}