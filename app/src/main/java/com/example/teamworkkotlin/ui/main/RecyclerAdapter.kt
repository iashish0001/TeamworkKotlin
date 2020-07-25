package com.example.teamworkkotlin.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.teamworkkotlin.R
import com.example.teamworkkotlin.ui.main.Fragment3.*
import com.squareup.picasso.Picasso
import java.util.*
import com.example.teamworkkotlin.ui.main.Fragment3.Instancefield as Fragment3Instancefield


class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    //    private String[] data;
    // RecyclerView recyclerView;
    var datas: ArrayList<Fragment3Instancefield> = ArrayList<Fragment3Instancefield>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View = layoutInflater.inflate(R.layout.list_view, parent, false)
        return ViewHolder(listItem)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.title.setText(datas[position].title)
        Picasso.get()
            .load(datas[position].url)
            .into(holder.imgShow)
        holder.id.text = Integer.toString(datas[position].id)
    }

    fun setDatas(datas: ArrayList<Fragment3Instancefield>) {
        this.datas = datas
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgShow: ImageView
        var title: TextView
        var id: TextView

        init {
            imgShow =
                itemView.findViewById<View>(R.id.imgShow) as ImageView
            title = itemView.findViewById<View>(R.id.title) as TextView
            id = itemView.findViewById<View>(R.id.idText) as TextView
        }
    }
}


