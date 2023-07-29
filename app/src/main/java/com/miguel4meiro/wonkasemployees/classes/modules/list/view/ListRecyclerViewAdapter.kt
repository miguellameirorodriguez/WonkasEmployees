package com.miguel4meiro.wonkasemployees.classes.modules.list.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.miguel4meiro.wonkasemployees.R
import com.miguel4meiro.wonkasemployees.classes.models.app.Loompa
import com.miguel4meiro.wonkasemployees.classes.models.app.getValue
import com.miguel4meiro.wonkasemployees.databinding.LoompaItemBinding

class ListRecyclerViewAdapter(
    private val context: Context,
    private val listener: ListItemClickInterface
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val loompasList = mutableListOf<Loompa>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.loompa_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder !is ViewHolder) return
        val loompa = getLoompa(position) ?: return

        with(holder.binding) {
            Glide.with(context)
                .load(loompa.image)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(loompaIv)

            nameTv.text = loompa.fullName
            emailTv.text = loompa.email

            professionData.titleTv.text = context.getString(R.string.list_profession)
            professionData.valueTv.text = loompa.profession.getValue()

            root.setOnClickListener { listener.onLoompaClick(loompa.id) }
        }
    }

    override fun getItemCount(): Int = loompasList.size

    fun addLoompas(loompas: List<Loompa>) {
        val count = loompasList.size
        loompasList.addAll(loompas)
        notifyItemRangeInserted(count, loompas.size)
    }

    fun clearItems() {
        loompasList.clear()
        notifyDataSetChanged()
    }

    private fun getLoompa(position: Int): Loompa? {
        if (!loompasList.indices.contains(position)) return null
        return loompasList[position]
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        internal val binding = LoompaItemBinding.bind(view)
    }

    interface ListItemClickInterface {
        fun onLoompaClick(id: Int)
    }
}