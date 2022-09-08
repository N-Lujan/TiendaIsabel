package com.uns.tiendaisabel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.uns.tiendaisabel.R
import com.uns.tiendaisabel.databinding.ItemCategoryBinding
import com.uns.tiendaisabel.model.Categoria

class CategoriaAdapter(
    private var categoriaList: List<Categoria>,
    private val onClickListener: (Categoria) -> Unit
) : RecyclerView.Adapter<CategoriaAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCategoryBinding.bind(view)

        fun render(categoria: Categoria, onClickListener: (Categoria) -> Unit) {
            binding.titulo.text = categoria.nombre
            Picasso.get().load(categoria.urlImg).into(binding.ivCategoria)
            itemView.setOnClickListener { onClickListener(categoria) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_category, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = categoriaList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int {
        return categoriaList.size
    }
}