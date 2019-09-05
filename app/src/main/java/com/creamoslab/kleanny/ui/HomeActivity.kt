package com.creamoslab.kleanny.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.creamoslab.kleanny.R
import com.creamoslab.kleanny.ui.myaccount.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.nav_header_home.view.*

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
        loadImageFromStorageAndShow()
    }

    private fun pickImageFromGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            val uri = data.data

            var bitmap: Bitmap? = null

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && uri != null) {
                val source = ImageDecoder.createSource(this.contentResolver, uri)
                bitmap = ImageDecoder.decodeBitmap(source)
            }

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q && uri != null) {
                bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
            }

            if (bitmap != null) {
                val rotatedBitmap =
                    uri?.let { BitmapManager.fixBitmap(it, bitmap, applicationContext) } ?: return
                BitmapManager.saveImageToPreferences(rotatedBitmap)
                loadProfilePicture(Bitmap.createScaledBitmap(rotatedBitmap, 120, 120, false))
            }
        }
    }

    private fun loadProfilePicture(bitmap: Bitmap) {
        navigationView.getHeaderView(0).imageView_profile_photo.setImageBitmap(bitmap)
    }

    private fun loadImageFromStorageAndShow() {
        val bitmap = BitmapManager.loadImageFromPreferences()
        bitmap?.let { loadProfilePicture(it) }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        var title: String? = null
        val fragmentManager = supportFragmentManager

        when (item.itemId) {
            R.id.nav_visits -> {
                fragment = MyVisitsFragment()
                title = item.title.toString()
                drawer_layout.closeDrawer(Gravity.LEFT)
            }
            R.id.nav_account -> {
                fragment = MyAccountInfoFragment()
                title = item.title.toString()
                drawer_layout.closeDrawer(Gravity.LEFT)
            }
            R.id.nav_privacy -> {
                fragment = PrivacyFragment()
                title = item.title.toString()
                drawer_layout.closeDrawer(Gravity.LEFT)
            }
            R.id.nav_help -> {
                fragment = FaqFragment()
                title = item.title.toString()
                drawer_layout.closeDrawer(Gravity.LEFT)
            }
            R.id.nav_settings -> {
                fragment = SettingsFragment()
                title = item.title.toString()
                drawer_layout.closeDrawer(Gravity.LEFT)
            }
            R.id.bottom_account -> {
                drawer_layout.openDrawer(Gravity.LEFT)
            }
            R.id.bottom_finder -> {
                for (i in 0..fragmentManager.backStackEntryCount){
                    fragmentManager.popBackStackImmediate()
                }
            }
            R.id.bottom_wallet -> {

            }
        }
        toolbarTitle.text = title
        if (fragment != null) {
            val simpleName = fragment::class.simpleName

//            if (fragmentManager.findFragmentByTag(simpleName) == null) {
//                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment, simpleName).addToBackStack(null).commit()
                fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment, simpleName).commit()
//            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main2, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size > 0) {
            supportFragmentManager.beginTransaction().remove(supportFragmentManager.fragments[0]).commit()
        } else {
            super.onBackPressed()
        }
    }
}
