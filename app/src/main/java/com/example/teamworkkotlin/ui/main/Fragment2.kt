package com.example.teamworkkotlin.ui.main


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.teamworkkotlin.R


class Fragment2 : Fragment() {
    var imageView: ImageView? = null
    var button: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        val rootView: View = inflater.inflate(R.layout.fragment2, parent, false)
        imageView =
            rootView.findViewById<View>(R.id.imageView) as ImageView
        button = rootView.findViewById<View>(R.id.gallary) as Button
        imageView!!.visibility = View.INVISIBLE
        button!!.setOnClickListener { gallary(rootView) }
        return rootView
    }

    fun gallary(view: View?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity!!.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                val permission =
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permission, Permission_code)
            } else {
                pickImageFromGallary()
            }
        } else {
            pickImageFromGallary()
        }
    }

    private fun pickImageFromGallary() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/jpg"
        intent.type = "image/png"
        startActivityForResult(intent, 1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            Permission_code -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallary()
                } else {
                    Toast.makeText(activity, "failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            imageView!!.setImageURI(data!!.data)
            button!!.visibility = View.GONE
            imageView!!.visibility = View.VISIBLE
        } else {
            button!!.visibility = View.VISIBLE
        }
    }

    companion object {
        private const val Permission_code = 1
    }
}

