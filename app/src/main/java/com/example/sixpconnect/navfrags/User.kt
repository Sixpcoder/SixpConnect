package com.example.sixpconnect.navfrags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sixpconnect.R
import com.example.sixpconnect.databinding.FragmentUserBinding
import com.google.firebase.auth.FirebaseAuth

class User : Fragment() {
  private lateinit var binding: FragmentUserBinding
  private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentUserBinding.inflate(inflater,container,false)
        return binding.root

    }

    companion object {

    }

    override fun onStart() {
        super.onStart()


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}