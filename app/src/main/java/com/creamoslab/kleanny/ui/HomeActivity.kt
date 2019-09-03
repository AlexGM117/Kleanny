package com.creamoslab.kleanny.ui

import android.app.Activity
import android.content.ContentValues
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
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.Gravity
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

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

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        val name = "profile_pic"
//        val imagesFolderName = "DCIM/profile"
//        var fos : OutputStream? = null
//        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
//            val bitmap = data.getParcelableExtra<Bitmap>("BitmapImage")
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                val resolver = contentResolver
//                val contentValues = ContentValues()
//                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name)
//                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
//
//                contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, imagesFolderName)
//                val imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
//                fos = resolver.openOutputStream(imageUri!!)
//
////                TODO: Revisa esto
////                ContentValues values = new ContentValues();
////                String filename = System.currentTimeMillis() + ".jpg";
////
////                values.put(MediaStore.Images.Media.TITLE, filename);
////                values.put(MediaStore.Images.Media.DISPLAY_NAME, filename);
////                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
////                values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000);
////                values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
////                values.put(MediaStore.Images.Media.RELATIVE_PATH, "PATH/TO/ALBUM");
////
////                getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
//            }
//
//            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
//                val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + File.separator + imagesFolderName
//                val file = File(imagesDir)
//
//                if (!file.exists()) {
//                    file.mkdir()
//                }
//
//                val image = File(imagesDir, "$name.png")
//                fos = FileOutputStream(image)
//            }
//
//            bitmap?.compress(Bitmap.CompressFormat.PNG, 100, fos)
//            fos?.flush()
//            fos?.close()
//        }
//    }

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
