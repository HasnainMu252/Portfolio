package com.example.portfolio_mhm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class repo_adapter(val context: Context, private val repo_list: List<Repo_Data>): RecyclerView.Adapter<repo_adapter.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        var id:TextView = itemView.findViewById(R.id.TheID)
        var CreateDate:TextView = itemView.findViewById(R.id.CreateDate)
        var Title:TextView = itemView.findViewById(R.id.RepoTitle)
        var Dec:TextView = itemView.findViewById(R.id.description)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_view,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return repo_list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentData = repo_list[position]

        holder.id.text = currentData.id.toString()        // Set text on TextView
        holder.CreateDate.text = currentData.created_at    // Set text on TextView
        holder.Title.text = currentData.name              // Set text on TextView
        holder.Dec.text = currentData.description
    }



}