package com.uns.tiendaisabel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.uns.tiendaisabel.R
import com.uns.tiendaisabel.databinding.ItemProductoBinding
import com.uns.tiendaisabel.model.Producto

class ProductoAdapter(
    private var productoList: List<Producto>,
    private val onClickListener: (Producto) -> Unit
) : RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemProductoBinding.bind(view)

        fun render(producto: Producto, onClickListener: (Producto) -> Unit) {
            binding.titulo.text = producto.nombre
            Picasso.get().load(producto.urlImg).into(binding.ivProducto)
            itemView.setOnClickListener { onClickListener(producto) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_producto, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = productoList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int {
        return productoList.size
    }
}