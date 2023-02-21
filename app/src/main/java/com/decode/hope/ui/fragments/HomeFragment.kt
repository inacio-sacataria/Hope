package com.decode.hope.ui.fragments

import ClikedNotes
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.decode.hope.R
import com.decode.hope.adapters.NotesListAdapter
import com.decode.hope.data.db.entities.Note
import com.decode.hope.databinding.FragmentHomeBinding
import com.decode.hope.util.changeIconColor
import com.decode.hope.ui.viewmodels.SharedViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment() {


    private lateinit var  viewModel : SharedViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter : NotesListAdapter


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.bind(view)

        changeIconColor(binding.fabInfo,resources.getColor(R.color.white))
        changeIconColor(binding.fabSearch,resources.getColor(R.color.white))
        changeIconColor(binding.fab,resources.getColor(R.color.white))
        viewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        viewModel.initialize(requireActivity().applicationContext)
        fetchData()
        listenCLickNotes()
        swipeToDelete()



        val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                // Do something for new state.
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // Do something for slide offset.
            }
        }


        binding.fab.setOnClickListener {
           findNavController().navigate(R.id.action_homeFragment_to_createNoteFragment)
        }

        binding.fabSearch.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        binding.rvcNotes.setOnScrollChangeListener { view, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY > oldScrollY + 1 && binding.fab.isExtended()) {
                binding.fab.shrink();
            }

            // the delay of the extension of the FAB is set for 12 items
            if (scrollY < oldScrollY - 1 && !binding.fab.isExtended()) {
                binding.fab.extend();
            }

            // if the nestedScrollView is at the first item of the list then the
            // extended floating action should be in extended state
//            if (scrollY == 0) {
//                binding.fab.extend();
//            }
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getData()
        fetchData()
        Log.d("cycle","resume")
    }



    fun fetchData(){

        val colors = listOf<Int>(
            resources.getColor(R.color.pink),
            resources.getColor(R.color.orange),
            resources.getColor(R.color.green),
            resources.getColor(R.color.yellow),
            resources.getColor(R.color.blue)
        )

        viewModel._notes.observeForever {
            if(it.size>0) {
                adapter = NotesListAdapter(it,colors, requireActivity())
                binding.rvcNotes.adapter =adapter
                binding.txtInfo.visibility = View.GONE
                binding.imgIllustrationA.visibility= View.GONE
                Log.d("notepad", it.get(0).title.toString())
                adapter.notifyDataSetChanged()
            }else{
                binding.txtInfo.visibility = View.VISIBLE
                binding.imgIllustrationA.visibility = View.VISIBLE
            }
        }
    }
     fun listenCLickNotes(){
         ClikedNotes.note.observeForever {
             if (it!=null) {
                 findNavController().navigate(R.id.createNoteFragment)
             }
         }
     }

    fun swipeToDelete(){
        ItemTouchHelper( object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                var note : Note? = viewModel._notes.value?.get(viewHolder.adapterPosition)
                var position = viewHolder.position
                viewModel._notes.value?.remove(note)
                adapter.notifyItemRemoved(position)

                Snackbar.make(binding.main, "${note?.title}", Snackbar.LENGTH_LONG)
                    .setAction(R.string.undo_text) {
                        viewModel._notes.value?.add(note!!)
                    }
                    .show()

                viewModel.deleteData(note!!)
            }

        }).attachToRecyclerView(binding.rvcNotes)
    }

}