package com.example.teamworkkotlin.ui.main


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.example.teamworkkotlin.MainActivity
import com.example.teamworkkotlin.R
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout


class home : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val tabLayout: TabLayout? = null
    private val view_pager: ViewPager? = null
    private var hView: View? = null
    private var email: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        try {
            val intent = intent
            email = intent.getStringExtra("Email")
            Log.d(TAG, "onCreate: user name$email")
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
        val sectionsPagerAdapter =
            SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager = findViewById<ViewPager>(R.id.Home_Page_View)
        viewPager.adapter = sectionsPagerAdapter
        val tabs = findViewById<TabLayout>(R.id.Home_tabs)
        tabs.setupWithViewPager(viewPager)
        val toolbar =
            findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val drawer =
            findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close
        )
        drawer.setDrawerListener(toggle)
        toggle.syncState()
        val navigationView =
            findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
        hView = navigationView.getHeaderView(0)
        val textEmail =
            hView?.findViewById<View>(R.id.emailTextView) as TextView
        textEmail.text = email
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            Log.d(TAG, "onNavigationItemSelected: logout clicked")
            val intent = Intent(this@home, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        return false
    }

    companion object {
        private const val TAG = "home"
    }
}