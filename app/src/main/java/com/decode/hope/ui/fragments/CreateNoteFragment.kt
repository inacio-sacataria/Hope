package com.decode.hope.ui.fragments

import ClikedNotes
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.decode.hope.R
import com.decode.hope.data.db.entities.Note
import com.decode.hope.databinding.FragmentCreateNoteBinding
import com.decode.hope.ui.viewmodels.SharedViewModel
import com.decode.hope.util.changeIconColor


class CreateNoteFragment : Fragment() {
    private lateinit var binding: FragmentCreateNoteBinding
    private lateinit var viewModel: SharedViewModel
    var isViewModeEnable = false
     var note: Note?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_create_note, container, false)
        binding = FragmentCreateNoteBinding.bind(view)

        getLastData()

        changeIconColor(binding.fabBack,resources.getColor(R.color.white))
        changeIconColor(binding.fabSave,resources.getColor(R.color.white))
        changeIconColor(binding.fabViewMode,resources.getColor(R.color.white))


        viewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        viewModel.initialize(requireActivity())

        binding.fabSave.setOnClickListener {
            if (!binding.editTitle.text.isNullOrEmpty() && ! binding.editContent.text.isNullOrEmpty()){

                var newNote = Note(
                    title =binding.editTitle.text.toString(),
                    content = binding.editContent.text.toString())

                if (note==null){

                    viewModel.saveData(newNote)
                }else{
                    note?.title =newNote.title
                    note?.content =newNote.content
                    viewModel.updateData(note!!)
                }

                findNavController().navigate(R.id.action_createNoteFragment_to_homeFragment)
            }else{
                Toast.makeText(requireActivity(),"Preencha todos campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.fabBack.setOnClickListener {
            findNavController().navigate(R.id.action_createNoteFragment_to_homeFragment)
        }

        binding.fabViewMode.setOnClickListener {
            isViewModeEnable= !isViewModeEnable
            if (isViewModeEnable){
                binding.editTitle.isEnabled= FIELDS_DISABLED
                binding.editContent.isEnabled= FIELDS_DISABLED
                binding.fabSave.visibility = HIDE_VIEW
                binding.fabViewMode.setImageResource(R.drawable.ic_edit)
            }else{
                binding.editTitle.isEnabled= FIELDS_ENABLED
                binding.editContent.isEnabled= FIELDS_ENABLED
                binding.fabSave.visibility = SHOW_VIEW
                binding.fabViewMode.setImageResource(R.drawable.ic_eye)
            }
        }


        return binding.root
    }

    fun changeBackGroundColor(position: Int){
        val colors = listOf<Int>(
            resources.getColor(R.color.pink),
            resources.getColor(R.color.orange),
            resources.getColor(R.color.green),
            resources.getColor(R.color.yellow),
            resources.getColor(R.color.blue)
        )
        when(position){
            0->   {
                binding.nestedScrollView.setBackgroundColor(colors[0])
                binding.editContent.setBackgroundColor(colors[0])
                binding.editTitle.setBackgroundColor(colors[0])
            }
            1->  {
                binding.nestedScrollView.setBackgroundColor(colors[1])
                binding.editContent.setBackgroundColor(colors[1])
                binding.editTitle.setBackgroundColor(colors[1])
            }
            2->   {
                binding.nestedScrollView.setBackgroundColor(colors[2])
                binding.editContent.setBackgroundColor(colors[2])
                binding.editTitle.setBackgroundColor(colors[2])
            }
            3->  {
                binding.nestedScrollView.setBackgroundColor(colors[3])
                binding.editContent.setBackgroundColor(colors[3])
                binding.editTitle.setBackgroundColor(colors[3])
            }
            4->  {
                binding.nestedScrollView.setBackgroundColor(colors[4])
                binding.editContent.setBackgroundColor(colors[4])
                binding.editTitle.setBackgroundColor(colors[4])
            }
        }
    }

    fun getLastData(){
        note = ClikedNotes.note.value
        if(note!=null){
            isViewModeEnable= !isViewModeEnable
            binding.editTitle.isEnabled= FIELDS_DISABLED
            binding.editContent.isEnabled= FIELDS_DISABLED
            binding.fabSave.visibility = HIDE_VIEW
            binding.fabViewMode.setImageResource(R.drawable.ic_edit)

            binding.editTitle.setText(note?.title)
            binding.editContent.setText(note?.content)
            ClikedNotes.note.value= null
        }
    }

    companion object{
        val FIELDS_DISABLED = false
        val FIELDS_ENABLED = true
        val HIDE_VIEW = View.GONE
        val SHOW_VIEW = View.VISIBLE
    }
}