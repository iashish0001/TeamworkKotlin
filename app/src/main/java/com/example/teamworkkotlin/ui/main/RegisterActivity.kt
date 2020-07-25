package com.example.teamworkkotlin.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.data.UserDao
import com.example.data.UserDataBase
import com.example.modules.User
import com.example.teamworkkotlin.MainActivity
import com.example.teamworkkotlin.R


class RegisterActivity : AppCompatActivity() {
    var editTextUsername: EditText? = null
    var editTextEmail: EditText? = null
    var editTextPassword: EditText? = null
    var editTextCnfPassword: EditText? = null
    var buttonRegister: Button? = null
    var textViewLogin: TextView? = null
    private var userDao: UserDao? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        editTextUsername = findViewById(R.id.editTextUsername)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextCnfPassword = findViewById(R.id.editTextCnfPassword)
        buttonRegister = findViewById(R.id.buttonRegister)
        userDao =
            Room.databaseBuilder<UserDataBase>(this, UserDataBase::class.java, "mi-database.db")
                .allowMainThreadQueries()
                .build().userDao
        buttonRegister?.setOnClickListener(View.OnClickListener {
            val userName =
                editTextUsername?.getText().toString().trim { it <= ' ' }
            val email = editTextEmail?.getText().toString().trim { it <= ' ' }
            val password =
                editTextPassword?.getText().toString().trim { it <= ' ' }
            val passwordConf =
                editTextCnfPassword?.getText().toString().trim { it <= ' ' }
            if (password == passwordConf) {
                val user = User(userName, password, email)
                userDao!!.insert(user)
                val moveToLogin =
                    Intent(this@RegisterActivity, MainActivity::class.java)
                startActivity(moveToLogin)
            } else {
                Toast.makeText(
                    this@RegisterActivity,
                    "Password is not matching",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
