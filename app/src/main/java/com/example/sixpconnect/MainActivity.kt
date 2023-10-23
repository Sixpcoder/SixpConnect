package com.example.sixpconnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.sixpconnect.databinding.ActivityMainBinding
import com.example.sixpconnect.model.Usermodel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.collection.LLRBNode.Color
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth:FirebaseAuth
    private lateinit var data :FirebaseDatabase
    private lateinit var user:Usermodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data = FirebaseDatabase.getInstance()
        auth =FirebaseAuth.getInstance()

        user= Usermodel()
        binding.button.setOnClickListener {

            if (binding.name.text.toString().isNotEmpty() &&
                binding.email.text.toString().isNotEmpty() &&
                binding.Password.text.toString().isNotEmpty()){

                Toast.makeText(this,
                    "Please fill all the Details",
                    Toast.LENGTH_SHORT)
                    .show()

            }
            else{

                auth.createUserWithEmailAndPassword(
                    binding.email.text.toString(),
                    binding.Password.text.toString())
                    .addOnCompleteListener {

                        result->
                        if (result.isSuccessful){

                            user.name=binding.name.text.toString()
                            user.email=binding.email.text.toString()
                            user.password= binding.Password.text.toString()

                            Firebase.firestore.collection("User")
                                .document(auth.currentUser!!.uid )
                                .set(user)
                                .addOnSuccessListener {

                                }

                            Toast.makeText(this,
                                "You are Registered successfully",
                                Toast.LENGTH_SHORT)
                                .show()

                        }
                        else{
                            Log.e("Firebase",
                                "registration failed",
                                result.exception)

                            Toast.makeText(this,
                                "${result.exception}",
                                Toast.LENGTH_SHORT)
                                .show()
                        }

                    }
            }

        }

    }
}