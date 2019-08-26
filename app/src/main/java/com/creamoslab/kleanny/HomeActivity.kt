package com.creamoslab.kleanny

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.creamoslab.kleanny.myaccount.*
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*

class HomeActivity : AppCompatActivity(), OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        var title: String? = null
        when(item.itemId) {
            R.id.nav_views -> {
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
            R.id.nav_settings -> {
                fragment = SettingsFragment()
                title = item.title.toString()
            }
            R.id.nav_rewards -> {
                fragment = FaqFragment()
                title = item.title.toString()
            }
        }
        toolbarTitle.text = title
        if (fragment != null) {
            val fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment).commit()
        }
        drawer_layout.closeDrawer(GravityCompat.START)
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
