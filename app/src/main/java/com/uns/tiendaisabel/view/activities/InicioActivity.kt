package com.uns.tiendaisabel.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.uns.tiendaisabel.R
import com.uns.tiendaisabel.databinding.ActivityInicioBinding

class InicioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInicioBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        firebaseAuth = FirebaseAuth.getInstance()


        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.comprarCompatButton.setOnClickListener {
            val intent = Intent(this, CategoriaActivity::class.java)
            this.startActivity(intent)
        }

        binding.infoCompatButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            this.startActivity(intent)
        }

        binding.logout.setOnClickListener {
            firebaseAuth.signOut()
            finish()
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
}