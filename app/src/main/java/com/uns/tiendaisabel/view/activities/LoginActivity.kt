package com.uns.tiendaisabel.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.uns.tiendaisabel.R
import com.uns.tiendaisabel.databinding.ActivityLoginBinding
import com.uns.tiendaisabel.isAnEmailValid

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseFirestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.LoginTheme)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = binding.edtEmail
        val password = binding.edtPassword

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseFirestore = FirebaseFirestore.getInstance()

        binding.signUpTextView.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            if (email.text.toString().isNotEmpty() && password.text.toString().isNotEmpty()) {
                if (email.text.toString().isAnEmailValid()) {
                    loginUser(email.text.toString(), password.text.toString())
                } else {
                    Toast.makeText(this, "Ingrese un email válido", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No deje espacios en blanco", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                finish()
                startActivity(Intent(this,InicioActivity::class.java))

            } else {
                Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        var user = firebaseAuth.currentUser
        if (user!=null){
            startActivity(Intent(this, InicioActivity::class.java))
        }
    }
}