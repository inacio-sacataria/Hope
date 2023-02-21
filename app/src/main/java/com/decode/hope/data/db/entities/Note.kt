package com.decode.hope.data.db.entities

import android.icu.text.CaseMap.Title
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id : Int?=null,
    @ColumnInfo(name = "title")
    var title: String?=null,
    @ColumnInfo(name="content")
    var content: String?=null,
    @ColumnInfo(name="created_at")
    var createdAt : Long?=null,
    @ColumnInfo(name="modified_at")
    var modifiedAt : Long?=null
)
