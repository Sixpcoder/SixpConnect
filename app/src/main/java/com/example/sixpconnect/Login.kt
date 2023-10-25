package com.example.sixpconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sixpconnect.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.register.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))

        }
        binding.Login.setOnClickListener {
            if (binding.email.text.toString().isEmpty() or
                binding.Password.text.toString().isEmpty()){

                Toast.makeText(this,
                    "please fill all the details",Toast.LENGTH_SHORT)
                    .show()

            }
            else{
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.email.text.toString(),
                    binding.Password.text.toString()
                ).addOnCompleteListener {
                    if(it.isSuccessful){
                        startActivity(Intent(this,Home::class.java))
                        finish()

                    }else{

                        Toast.makeText(this,
                            it.exception?.localizedMessage,
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            }
        }
    }
}