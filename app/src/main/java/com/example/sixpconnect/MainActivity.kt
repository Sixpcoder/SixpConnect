package com.example.sixpconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
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

    private val launcher=registerForActivityResult(ActivityResultContracts.GetContent()){
        uri->
        uri?.let {
             uploadpic(uri, USER_PROFILE_FOLDER){
                 if (it==null){

                 }
                 else{
                     binding.photo.setImageURI(uri)
                     user.photo=it


                 }
             }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        data = FirebaseDatabase.getInstance()
        auth =FirebaseAuth.getInstance()

        user= Usermodel()
        binding.button.setOnClickListener {

            if (binding.name.text.toString().isEmpty() or
                binding.email.text.toString().isEmpty() or
                binding.Password.text.toString().isEmpty()){

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
                                    startActivity(Intent(this,Home::class.java))
                                    finish()

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

        binding.photo.setOnClickListener {
            launcher.launch("image/*")
        }

        binding.login.setOnClickListener {
            startActivity(Intent(this,Login::class.java))
        }

    }
}