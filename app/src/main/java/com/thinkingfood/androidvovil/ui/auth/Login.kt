package com.thinkingfood.androidvovil.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.thinkingfood.androidvovil.MainActivity
import com.thinkingfood.androidvovil.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth=Firebase.auth
        firestore=FirebaseFirestore.getInstance()

        binding.login.setOnClickListener {
            val mEmail= binding.email.editText?.text.toString()
            val password =binding.password.editText?.text.toString()
            LoginFirebase(mEmail,password)
        }
        binding.regsiter.setOnClickListener {
            val intent= Intent(this,Register::class.java)
            this.startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser=auth.currentUser
        if(currentUser!=null){
            reload()
//            if (currentUser.isEmailVerified){
//
//            }
        }
    }

    fun LoginFirebase(email:String,password:String){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){ task->
            if (task.isSuccessful){
                val user =auth.currentUser
                val userId = user!!.uid
                val documentReference:DocumentReference
                documentReference=firestore.collection("Usuarios").document(userId)
                documentReference.update(mapOf(
                    "email" to email,
                    "password" to password
                ))
                reload()
//                if (user.isEmailVerified){
//
//                }else{
//                    Toast.makeText(this,"no se pudo iniciar sesión LPTM",Toast.LENGTH_LONG).show()
//                }
            }else{
                Toast.makeText(this,"no se pudo iniciar sesión LPTM",Toast.LENGTH_LONG).show()
            }
        } }

    fun reload(){
        val intent= Intent(this,MainActivity::class.java)
        this.startActivity(intent)
    }
}