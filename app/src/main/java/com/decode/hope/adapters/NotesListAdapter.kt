package com.decode.hope.adapters

import ClikedNotes
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.decode.hope.R
import com.decode.hope.data.db.entities.Note
import com.decode.hope.util.toTimeDateString

class NotesListAdapter(var noteList: MutableList<Note>,var colors: List<Int>,var context: Context) : Adapter<NotesListAdapter.MyViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       var view = LayoutInflater.from(parent.context).inflate(R.layout.item_notes,parent,false)
        return MyViewHolder(item = view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var randomColor = (0..4).random()
        holder.initiliaze(noteList.get(position),randomColor)
    }

    override fun getItemCount(): Int {
      return  noteList.size!!
    }

    inner class MyViewHolder(item : View) : ViewHolder(item){
        var title = item.findViewById<TextView>(R.id.txtTitle)
        var txtDate = item.findViewById<TextView>(R.id.txtDate)
        var background = item.findViewById<View>(R.id.background)

       fun initiliaze(note: Note, position: Int){
           title.text= note.title
           txtDate.text = note.createdAt?.toTimeDateString()
           changeCardColor(position)
           background.setOnClickListener {
               ClikedNotes.note.postValue(note)
           }
       }

        fun changeCardColor(position: Int){
            when(position){
                0->  background.setBackgroundColor(colors[0])
                1->  background.setBackgroundColor(colors[1])
                2->  background.setBackgroundColor(colors[2])
                3->  background.setBackgroundColor(colors[3])
                4->  background.setBackgroundColor(colors[4])
            }
        }

    }
}