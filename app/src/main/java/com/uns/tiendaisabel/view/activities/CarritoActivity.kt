package com.uns.tiendaisabel.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.uns.tiendaisabel.adapter.CarritoAdapter
import com.uns.tiendaisabel.databinding.ActivityCarritoBinding
import com.uns.tiendaisabel.model.Carrito
import com.uns.tiendaisabel.model.Pedido

class CarritoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCarritoBinding
    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var adapter: CarritoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarritoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseFirestore = FirebaseFirestore.getInstance()
        initRecyclewView(Carrito.contenido)

        binding.btnSave.setOnClickListener {
            guardarPedido()
        }
    }

    private fun initRecyclewView(pedidos: List<Pedido>) {
        adapter = CarritoAdapter(
            pedidoList = pedidos,
            onAddOne = { pos -> onAddOne(pos) },
            onRemoveOne = { pos -> onRemoveOne(pos) },
            onClickDelete = { pos -> onItemDeleted(pos) }
        )
        val manager = LinearLayoutManager(this)
        binding.rview.layoutManager = manager
        binding.rview.adapter = adapter
    }

    private fun onAddOne(pos: Int) {
        Carrito.contenido.get(pos).cantidad = "${Carrito.contenido.get(pos).cantidad.toInt() + 1}"
        adapter.notifyItemChanged(pos)
    }

    private fun onRemoveOne(pos: Int) {
        if (Carrito.contenido.get(pos).cantidad.toInt() != 1) {
            Carrito.contenido.get(pos).cantidad =
                "${Carrito.contenido.get(pos).cantidad.toInt() - 1}"
            adapter.notifyItemChanged(pos)
        }
    }

    private fun onItemDeleted(pos: Int) {
        Carrito.contenido.removeAt(pos)
        adapter.notifyItemRemoved(pos)
    }

    private fun guardarPedido() {
        if (Carrito.contenido.isNotEmpty()) {
            for (i in 0 until Carrito.contenido.size) {
                val pedido = Carrito.contenido.get(i)
                firebaseFirestore.collection("pedidos").document().set(pedido)
            }
            Carrito.contenido.clear()
            finish()
            startActivity(Intent(this, CategoriaActivity::class.java))
            Toast.makeText(this, "Pedido Guardado", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show()
        }
    }
}