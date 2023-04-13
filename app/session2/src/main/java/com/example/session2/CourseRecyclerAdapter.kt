package com.example.session2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.session2.Connection.api
import com.example.session2.databinding.CourseItemBinding
import com.example.session2.databinding.TagBinding
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream

class CourseViewHolder(var binding: CourseItemBinding) : RecyclerView.ViewHolder(binding.root)
class CourseRecyclerAdapter(val list: List<ModelCourse>, val tags: List<ModelTag>) : RecyclerView.Adapter<CourseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        return CourseViewHolder(CourseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.binding.courseHeader.text = list[position].title
        holder.binding.price.text = "₽${list[position].price}"
        holder.binding.subRecycler.adapter = SubRecyclerAdapter(tags, list[position].tags)

        val path = Environment.getExternalStorageDirectory()
        val file = File(path, list[position].cover)
        if (file.exists()) {
            holder.binding.courseCover.setImageBitmap(getImage(list[holder.adapterPosition].cover))
        }
        api.getImage(list[position].cover).push(object: OnGetData<ResponseBody>{
            override fun onGet(data: ResponseBody) {
                saveImage(data, list[holder.adapterPosition].cover)
                holder.binding.courseCover.setImageBitmap(getImage(list[holder.adapterPosition].cover))
            }

            override fun onError(error: String) {
                Toast.makeText(holder.itemView.context, error, Toast.LENGTH_LONG).show()
            }
        }, holder.itemView.context)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun saveImage(body: ResponseBody, name: String) {
        val path = Environment.getExternalStorageDirectory()
        val fileRender = ByteArray(4096)
        val inputStream = body.byteStream()
        val outputStream = FileOutputStream(File(path, name))
        while (true) {
            val sz = inputStream.read()
            if (sz == -1) {
                break
            }
            outputStream.write(fileRender, 0, sz)
        }
        outputStream.flush()
        inputStream.close()
        outputStream.close()
    }

    fun getImage(name: String) : Bitmap {
        val path = Environment.getExternalStorageDirectory()
        val bitmap = BitmapFactory.decodeFile("$path/$name")
        return bitmap
    }
}