package com.example.sixpconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.FirebaseAuth

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            if(FirebaseAuth.getInstance().currentUser==null){
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
            else{
                startActivity(Intent(this,Home::class.java))
                finish()
            }
        },3000)
    }
}