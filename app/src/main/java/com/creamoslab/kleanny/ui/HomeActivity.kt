package com.creamoslab.kleanny.ui

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import java.io.*

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
        loadImageFromStorage()
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
                saveImageToInternalStorage(bitmap)
                loadProfilePicture(bitmap)
            }
        }
    }

    private fun loadProfilePicture(bitmap: Bitmap) {
        navigationView.getHeaderView(0).imageView_profile_photo.setImageBitmap(bitmap)
    }

    private fun saveImageToInternalStorage(bitmapImage: Bitmap) {
        var fos: FileOutputStream? = null

        try {
            fos = FileOutputStream(getProfilePicPath())
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                fos?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun getProfilePicPath(): File {
        val cw = ContextWrapper(applicationContext)
        val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
        return File(directory, "profile.jpg")
    }

    private fun loadImageFromStorage() {
        try {
            val bitmap = BitmapFactory.decodeStream(FileInputStream(getProfilePicPath()))
            if (bitmap != null) {
                loadProfilePicture(bitmap)
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
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
