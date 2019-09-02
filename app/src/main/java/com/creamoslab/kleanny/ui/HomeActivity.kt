package com.creamoslab.kleanny.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.creamoslab.kleanny.R
import com.creamoslab.kleanny.ui.myaccount.*
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.nav_header_home.view.*
import android.content.Intent
import android.view.Gravity
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity(), OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemSelectedListener {

    companion object {
//        día de nacimiento (19), mes (7), número personalizado (01)
        private const val PICK_IMAGE: Int = 19701
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        bottom_navigation.setOnNavigationItemSelectedListener(this)

        navigationView.setNavigationItemSelectedListener(this)
        navigationView.getHeaderView(0).imageView_profile_photo.setOnClickListener {
            pickImageFromGallery()
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        var title: String? = null
        when (item.itemId) {
            R.id.nav_visits -> {
                fragment = MyVisitsFragment()
                title = item.title.toString()
            }
            R.id.nav_account -> {
                fragment = MyAccountInfoFragment()
                title = item.title.toString()
            }
            R.id.nav_privacy -> {
                fragment = PrivacyFragment()
                title = item.title.toString()
            }
            R.id.nav_help -> {
                fragment = FaqFragment()
                title = item.title.toString()
            }
            R.id.nav_settings -> {
                fragment = SettingsFragment()
                title = item.title.toString()
            }
            R.id.bottom_account -> {
                drawer_layout.openDrawer(Gravity.LEFT)
            }
        }
        toolbarTitle.text = title
        if (fragment != null) {
            val fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main2, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }
}
