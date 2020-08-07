package com.sumin.aactest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_rx.view.*

class RxAdapter: RecyclerView.Adapter<RxAdapter.TestHolder>() {
    var list = List(20) { i:Int -> i}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_rx, parent, false)

        return TestHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TestHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class TestHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(data:Int){
            itemView.mTestTV.text = data.toString()
        }
    }
}

