package com.example.tragosapp.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
//ESTO BINDEA A CADA ITEM DE LA LISTA
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T,position:Int)
}