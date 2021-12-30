package com.codingtest.ul.network.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "university")
data class University(
    val country: String,
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}