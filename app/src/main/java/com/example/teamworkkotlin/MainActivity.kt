package com.example.teamworkkotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.teamworkkotlin.ui.main.RegisterActivity


class MainActivity : AppCompatActivity() {
    var signinButton: Button? = null
    var signupButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        signinButton = findViewById(R.id.signinButton)
        signupButton = findViewById(R.id.signupButton)
        signinButton?.setOnClickListener(View.OnClickListener {
            val signIn = Intent(this@MainActivity, signInActivity::class.java)
            this@MainActivity.startActivity(signIn)
        })
        signupButton?.setOnClickListener(View.OnClickListener {
            val signUp = Intent(this@MainActivity, RegisterActivity::class.java)
            //Intent test = new Intent(MainActivity.this, home.class);
            this@MainActivity.startActivity(signUp)
        })
    }
}