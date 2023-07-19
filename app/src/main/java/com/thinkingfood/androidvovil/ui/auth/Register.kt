package com.thinkingfood.androidvovil.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.thinkingfood.androidvovil.MainActivity
import com.thinkingfood.androidvovil.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding=ActivityRegisterBinding.inflate(layoutInflater)
        auth= Firebase.auth
        setContentView(binding.root)
        binding.register.setOnClickListener {
//            if con toast para validar los datos aqui
            val email= binding.email.editText?.text.toString()
            val password = binding.password.editText?.text.toString()
            val nombre = binding.nombre.editText?.text.toString()
            val appellido = binding.apellido.editText?.text.toString()
            register(email,password,nombre,appellido)
        }

    }
    override fun onStart() {
        super.onStart()
        val currentUser=auth.currentUser
        if(currentUser!=null){
            if (currentUser.isEmailVerified){
                reload()
            }
        }
    }

    fun register( email:String,password:String,name:String,apellido:String){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task->
            if (task.isSuccessful){
                val user= hashMapOf(
                    "email" to email,
                    "nombre" to name,
                    "apellifo" to apellido
                )
                Firebase.firestore.collection("Usuarios")
                    .document(task.result.user!!.uid).set(user)
                reload()
            }

        }
    }


    fun reload(){
        val intent= Intent(this, MainActivity::class.java)
        this.startActivity(intent)
    }
}