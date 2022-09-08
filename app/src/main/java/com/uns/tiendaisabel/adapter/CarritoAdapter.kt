package com.uns.tiendaisabel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.uns.tiendaisabel.R
import com.uns.tiendaisabel.databinding.ItemCarritoBinding
import com.uns.tiendaisabel.model.Pedido
import com.uns.tiendaisabel.model.Producto

class CarritoAdapter(
    private var pedidoList: List<Pedido>,
    private val onAddOne: (Int) -> Unit,
    private val onRemoveOne: (Int) -> Unit,
    private val onClickDelete: (Int) -> Unit
) : RecyclerView.Adapter<CarritoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCarritoBinding.bind(view)

        fun render(pedido: Pedido, onAddOne: (Int) -> Unit, onRemoveOne: (Int) -> Unit, onClickDelete: (Int) -> Unit) {
            binding.nombreProducto.text = pedido.producto
            binding.precio.text = "S/.${pedido.cantidad.toInt() * pedido.precio.toDouble()}"
            binding.cantidad.text = "${pedido.cantidad}"
            binding.btnSumar.setOnClickListener { onAddOne(adapterPosition) }
            binding.btnRestar.setOnClickListener { onRemoveOne(adapterPosition) }
            binding.btnEliminar.setOnClickListener { onClickDelete(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_carrito, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = pedidoList[position]
        holder.render(item, onAddOne,onRemoveOne , onClickDelete)
    }

    override fun getItemCount(): Int {
        return pedidoList.size
    }
}