package com.example.session2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.session2.Connection.api
import com.example.session2.databinding.ActivityCatalogBinding
import java.io.File
import java.io.FileOutputStream

class CatalogActivity : AppCompatActivity() {
    private lateinit var cbinding: ActivityCatalogBinding
    lateinit var tags: List<ModelTag>
    lateinit var courses: List<ModelCourse>
    var isAll = true
    val checked = mutableListOf<Boolean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cbinding = ActivityCatalogBinding.inflate(layoutInflater)
        setContentView(cbinding.root)
        checkPermission()

        cbinding.search.addTextChangedListener {
            val str = cbinding.search.text.toString().split(' ')
            for (i in tags.indices) {
                checked.add(false)
            }
            isAll = true

        }

        api.getTags().push(object: OnGetData<List<ModelTag>>{
            override fun onGet(data: List<ModelTag>) {
                tags = data
                for (i in tags.indices) {
                    checked.add(true)
                }
                var searchRequest = ""
                cbinding.tagRecycler.adapter = TagAdapter(tags, object: OnClickTag{
                    override fun onClick(tag: ModelTag) {
                        searchRequest += "${tag.name} "
                        cbinding.search.setText(searchRequest)
                    }
                })
                cbinding.tagRecycler.layoutManager = LinearLayoutManager(this@CatalogActivity, LinearLayoutManager.HORIZONTAL, false)
            }

            override fun onError(error: String) {
                Toast.makeText(this@CatalogActivity, error, Toast.LENGTH_LONG).show()
            }
        }, this)


        api.getCourses().push(object: OnGetData<List<ModelCourse>>{
            override fun onGet(data: List<ModelCourse>) {
                courses = data
                cbinding.catalogRecycler.adapter = CourseRecyclerAdapter(courses, tags)
                cbinding.catalogRecycler.layoutManager = LinearLayoutManager(this@CatalogActivity)
            }

            override fun onError(error: String) {
                Toast.makeText(this@CatalogActivity, error, Toast.LENGTH_LONG).show()
            }
        }, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123){
            checkPermission()
        }
    }
    fun checkPermission(){
        try {
            val path = Environment.getExternalStorageDirectory()
            val outputStream = FileOutputStream(File(path, "placeholder.png"))
        }catch (e: java.lang.Exception) {
            AlertDialog.Builder(this)
                .setTitle("Требуется разрешение!")
                .setMessage("Для дальнейшего использования приложения необходимо Ваше разрешение на использование хранилища устройства.")
                .setPositiveButton("OK"){
                        dialog, id -> dialog.cancel()
                }.create().show()
            val permissionIntent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION,
                Uri.parse("package:" + com.example.session2.BuildConfig.APPLICATION_ID))
            startActivityForResult(permissionIntent, 123)
        }
    }
}