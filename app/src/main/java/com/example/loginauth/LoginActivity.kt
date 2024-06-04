package com.example.loginauth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginauth.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    private lateinit var firebaseAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        firebaseAuth= FirebaseAuth.getInstance()
        binding.LoginButton.setOnClickListener{
            val email=binding.LoginEmail.text.toString()
            val password=binding.LoginPassword.text.toString()
            if(email.isNotEmpty()&&password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){
                        task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show()
                        val intent=Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                Toast.makeText(this,"Enter email and password",Toast.LENGTH_SHORT).show()
            }
        }
        binding.SignupText.setOnClickListener{
            startActivity(Intent(this,SignupActivity::class.java))
            finish()
        }
    }
}