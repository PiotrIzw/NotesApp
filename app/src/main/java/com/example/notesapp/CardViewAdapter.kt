package com.example.notesapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CardViewAdapter(val context: Context,val db: SQLiteDatabase): RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cardViewNote = layoutInflater.inflate(R.layout.card_view, parent, false)
        return MyViewHolder((cardViewNote))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cardViewNote: CardView = holder.view.findViewById(R.id.note_cardView)
        val title: TextView = holder.view.findViewById(R.id.title_cardView)
        val message: TextView = holder.view.findViewById(R.id.message_cardView)

        val cursor = db.query(TableInfo.TABLE_NAME, null, BaseColumns._ID + "=?",
            arrayOf(holder.adapterPosition.plus(1).toString()), null, null, null)

        if (cursor.moveToFirst()){
                title.setText(cursor.getString(1))
                message.setText(cursor.getString(2))
            }
    }

    override fun getItemCount(): Int {
        val cursor = db.query(TableInfo.TABLE_NAME, null, null,
            null, null, null, null)

        val rowsCount = cursor.count
        cursor.close()

        return rowsCount

    }

}

class  MyViewHolder(val view: View): RecyclerView.ViewHolder(view)