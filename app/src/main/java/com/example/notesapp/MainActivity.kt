package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onClickTakeNotes(v: View){
        val intent = Intent(applicationContext, DetailsActivity::class.java)
        Log.d("Activity Main", "Before starting activity")
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()

        val dbHelper = DatabaseHelper(applicationContext)
        val db = dbHelper.writableDatabase

        binding.recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        binding.recyclerView.adapter = CardViewAdapter(applicationContext, db)
    }
}