package com.uns.tiendaisabel.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.uns.tiendaisabel.adapter.CategoriaAdapter
import com.uns.tiendaisabel.adapter.CategoriaProvider
import com.uns.tiendaisabel.databinding.ActivityCategoriaBinding
import com.uns.tiendaisabel.model.Categoria

class CategoriaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriaBinding
    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseFirestore = FirebaseFirestore.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()
        getData()

        binding.ivLogo.setOnClickListener {
            firebaseAuth.signOut()
            finish()
            startActivity(Intent(this,LoginActivity::class.java))
        }

        binding.ivCarrito.setOnClickListener {
            startActivity(Intent(this,CarritoActivity::class.java))
        }
    }

    private fun initRecyclewView(categorias: List<Categoria>) {
        val manager = GridLayoutManager(this, 2)
        binding.rview.layoutManager = manager
        binding.rview.adapter = CategoriaAdapter(
            categorias
        ) { onItemSelected(it) }
    }

    private fun onItemSelected(categoria: Categoria) {
        val intent = Intent(this, ProductoActivity::class.java)
        intent.putExtra("categoria",categoria.nombre)
        startActivity(intent)
    }

    private fun getData() {
        var listaCategorias = mutableListOf<Categoria>()
        firebaseFirestore.collection("categorias")
            .get()
            .addOnSuccessListener { categorias ->
                for (categoria in categorias) {
                    val data = categoria.data
                    val nombre = data["nombre"] as String
                    val urlImg = data["urlImg"] as String
                    val nuevaCategoria = Categoria(nombre, urlImg)
                    listaCategorias.add(nuevaCategoria)
                }
                initRecyclewView(listaCategorias)
            }
            .addOnFailureListener {
                initRecyclewView(CategoriaProvider.categoriaList)
            }
    }
}