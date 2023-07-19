package com.thinkingfood.androidvovil.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.thinkingfood.androidvovil.MainActivity
import com.thinkingfood.androidvovil.databinding.ActivityCheckEmailBinding

class CheckEmail : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private var _binding: ActivityCheckEmailBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityCheckEmailBinding.inflate(layoutInflater)
        auth= Firebase.auth
        setContentView(binding.root)
        val user=auth.currentUser
        binding.btnVerify.setOnClickListener {
            val profileUpdates = userProfileChangeRequest{}
            user!!.updateProfile(profileUpdates).addOnCompleteListener { task->
                if (task.isSuccessful){
                    if (user.isEmailVerified){
                        reload()
                    }
                }
            }
        }
//        logOut
//        binding.btnVerify.setOnClickListener {
//            auth.signOut()
//            val intent= Intent(this, Login::class.java)
//            this.startActivity(intent)
//        }
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


    fun reload(){
        val intent= Intent(this, MainActivity::class.java)
        this.startActivity(intent)
    }
}