package com.uns.tiendaisabel.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.uns.tiendaisabel.R
import com.uns.tiendaisabel.databinding.ActivityRegisterBinding
import com.uns.tiendaisabel.isAnEmailValid

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseFirestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.LoginTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = binding.edtName
        val email = binding.edtEmail
        val password = binding.edtPassword

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseFirestore = FirebaseFirestore.getInstance()

        binding.btnSave.setOnClickListener {
            if (name.text.toString().isNotEmpty() && email.text.toString().isNotEmpty()
                && password.text.toString().isNotEmpty()
            ) {
                if (email.text.toString().isAnEmailValid()) {
                    saveEmail(name.text.toString(), email.text.toString(), password.text.toString())
                } else {
                    Toast.makeText(this, "Ingrese un email válido", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No deje espacios en blanco", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveEmail(name: String, email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val id = firebaseAuth.currentUser?.uid
                val map = hashMapOf("id" to id, "nombre" to name, "email" to email, "password" to password)
                if (id != null) {
                    firebaseFirestore.collection("usuarios")
                        .document(id)
                        .set(map)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                            finish()
                            startActivity(Intent(this, LoginActivity::class.java))
                        }
                }
            } else {
                Toast.makeText(this, "Error al guardar datos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}