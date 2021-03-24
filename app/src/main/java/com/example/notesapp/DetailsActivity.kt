package com.example.notesapp

import android.content.ContentValues
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notesapp.databinding.ActivityDetailsBinding


class DetailsActivity: AppCompatActivity(){
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = DatabaseHelper(applicationContext)
        val db = dbHelper.writableDatabase
        val saveInfoToast = Toast.makeText(applicationContext, "Note saved", Toast.LENGTH_SHORT)

        binding.saveBTDetails.setOnClickListener{
            val title = binding.titleDetails.text.toString()
            val message = binding.messageDetails.text.toString()

            val value = ContentValues()
            value.put("title", title)
            value.put("message", message)


            if(!title.isNullOrEmpty() || !message.isNullOrEmpty()){
                val value = ContentValues()

                value.put("title", title)
                value.put("message", message)

                db.insertOrThrow(TableInfo.TABLE_NAME, null, value)
                saveInfoToast.show()
            }
            else{
                Toast.makeText(applicationContext, "Nothing to save", Toast.LENGTH_SHORT).show()
            }



        }
    }


}