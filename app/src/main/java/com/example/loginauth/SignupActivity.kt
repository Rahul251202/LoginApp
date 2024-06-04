package com.example.loginauth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginauth.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        firebaseAuth= FirebaseAuth.getInstance()
        binding.SignupButton.setOnClickListener{
            val email=binding.SignupEmail.text.toString()
            val password=binding.SignupPassword.text.toString()
            if(email.isNotEmpty()&&password.isNotEmpty()){
                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){
                    task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"Signup Successful",Toast.LENGTH_SHORT).show()
                        val intent=Intent(this,LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        Toast.makeText(this,"Signup Unsuccessful",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                Toast.makeText(this,"Enter email and password",Toast.LENGTH_SHORT).show()
            }
        }
        binding.LoginText.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }
}