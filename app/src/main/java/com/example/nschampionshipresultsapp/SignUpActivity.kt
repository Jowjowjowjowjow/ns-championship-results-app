package com.example.nschampionshipresultsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.editTextPassword
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    val mFirebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        buttonSignUp.setOnClickListener {
            val email = editTextEmail.text.toString()
            val senha = editTextPassword.text.toString()
            when{
                email.isEmpty() -> {
                    editTextLogin.error = "Por favor, preencha com um email válido."
                    editTextLogin.requestFocus()
                }
                senha.isEmpty() -> {
                    editTextPassword.error = "Por favor, preencha com a sua senha"
                    editTextPassword.requestFocus()
                }
                email.isEmpty() && senha.isEmpty() -> {
                    Toast.makeText(this, "Campos em branco!", Toast.LENGTH_SHORT).show()
                }
                !(email.isEmpty() && senha.isEmpty()) -> {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(this){
                        if(!it.isSuccessful){
                            Toast.makeText(this, "Erro na criação da conta!\nTente novamente", Toast.LENGTH_SHORT).show()
                            Log.d("ERRO!", it.exception.toString())
                        } else {
                            Toast.makeText(this, "Conta criada com sucesso!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, HomeActivity::class.java))
                        }
                    }
                }
                else -> Toast.makeText(this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
