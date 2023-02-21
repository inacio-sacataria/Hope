package com.decode.hope.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.decode.hope.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ModalFragment : BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modal, container, false)
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}