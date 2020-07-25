package com.example.teamworkkotlin


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.room.Room
import com.example.data.UserDao
import com.example.data.UserDataBase
import com.example.modules.User
import com.example.teamworkkotlin.ui.main.home
import com.google.android.material.snackbar.Snackbar


class signInActivity : AppCompatActivity() {
    var editTextEmail: EditText? = null
    var editTextPassword: EditText? = null
    var buttonLogin: Button? = null
    var db: UserDao? = null
    var dataBase: UserDataBase? = null
    var constraintLayout: ConstraintLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        constraintLayout = findViewById<View>(R.id.snackbarId) as ConstraintLayout
        dataBase =
            Room.databaseBuilder<UserDataBase>(this, UserDataBase::class.java, "mi-database.db")
                .allowMainThreadQueries()
                .build()
        db = dataBase?.userDao
        buttonLogin?.setOnClickListener(View.OnClickListener {
            val email = editTextEmail?.getText().toString().trim { it <= ' ' }
            val password =
                editTextPassword?.getText().toString().trim { it <= ' ' }
            val user: User? = db?.getUser(email, password)
            if (user != null) {
                val i = Intent(this@signInActivity, home::class.java)
                i.putExtra("Email", user.email)
                startActivity(i)
                finish()
                val snackbar = Snackbar
                    .make(constraintLayout!!, "SignIn Successfull", Snackbar.LENGTH_LONG)
                snackbar.show()
            } else {
                val snackbar = Snackbar
                    .make(
                        constraintLayout!!, """
     ‘Login Failed. Please
     check your credentials
     """.trimIndent(), Snackbar.LENGTH_LONG
                    )
                snackbar.show()
            }
        })
    }
}

