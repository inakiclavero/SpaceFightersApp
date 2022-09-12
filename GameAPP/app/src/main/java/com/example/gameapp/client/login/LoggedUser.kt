package com.example.gameapp.client.login

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "loggedUser_table")
class LoggedUser(@PrimaryKey @ColumnInfo(name = "tocken") val tocken: String,
                 @ColumnInfo(name = "user") val user: String,
)
