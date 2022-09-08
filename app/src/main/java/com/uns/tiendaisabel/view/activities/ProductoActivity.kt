package com.uns.tiendaisabel.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.uns.tiendaisabel.adapter.ProductoAdapter
import com.uns.tiendaisabel.databinding.ActivityProductoBinding
import com.uns.tiendaisabel.model.Carrito
import com.uns.tiendaisabel.model.Pedido
import com.uns.tiendaisabel.model.Producto
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class ProductoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductoBinding
    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseFirestore = FirebaseFirestore.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()

        val extras = intent.extras
        val nombreCategoria = extras?.getString("categoria") ?: ""
        getData(nombreCategoria)
    }

    private fun initRecyclewView(productos: List<Producto>) {
        val manager = GridLayoutManager(this, 2)
        binding.rview.layoutManager = manager
        binding.rview.adapter = ProductoAdapter(
            productos
        ) { onItemSelected(it) }
    }

    private fun onItemSelected(producto: Producto) {
        val id = firebaseAuth.currentUser?.uid
        if (id != null) {
            val fecha = ZonedDateTime.now(ZoneId.of("America/Chicago"))
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

            val hora = ZonedDateTime.now(ZoneId.of("America/Chicago"))
                .format(DateTimeFormatter.ofPattern("hh:mm:ss a"))

            val pedido = Pedido(producto.nombre, "${1}", "${producto.precio}", fecha, hora, id)
            Carrito.contenido.add(pedido)
            Toast.makeText(this, "Agregado al carrito", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getData(nombreCategoria: String) {
        var listaProductos = mutableListOf<Producto>()
        firebaseFirestore.collection("productos")
            .whereEqualTo("categoria", nombreCategoria)
            .get()
            .addOnSuccessListener { productos ->
                for (producto in productos) {
                    val data = producto.data
                    val nombre = data["nombre"] as String
                    val imagen = data["imagen"] as String
                    val precio = data["precio"] as String
                    val nuevoProducto = Producto(nombre, precio.toDouble(), imagen)
                    listaProductos.add(nuevoProducto)
                }
                initRecyclewView(listaProductos)
            }
    }
}