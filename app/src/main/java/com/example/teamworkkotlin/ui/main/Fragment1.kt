package com.example.teamworkkotlin.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.teamworkkotlin.R


class Fragment1 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        val rootView: View = inflater.inflate(R.layout.fragment1, parent, false)
        val phoneNumber =
            rootView.findViewById<View>(R.id.phoneNumber) as TextView
        val emailText =
            rootView.findViewById<View>(R.id.emailText) as TextView
        phoneNumber.setOnClickListener {
            val phone = "+919876543210"
            val intent =
                Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            startActivity(intent)
        }
        emailText.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/html"
            intent.putExtra(Intent.EXTRA_EMAIL, "abc@xyz.com")
            startActivity(Intent.createChooser(intent, "Send Email"))
        }
        return rootView
    }
}
