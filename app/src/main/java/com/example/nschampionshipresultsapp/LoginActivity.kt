package com.example.nschampionshipresultsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val mFirebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    lateinit var mAuthStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuthStateListener = FirebaseAuth.AuthStateListener {
           val mFirebaseUser = mFirebaseAuth.currentUser
            when{
                mFirebaseUser != null -> {
                    Toast.makeText(this, "Você está logado!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, HomeActivity::class.java))
                }
                else -> {
                    Toast.makeText(this, "Faça login!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        textViewRegister.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        buttonLogin.setOnClickListener {
            val email = editTextLogin.text.toString()
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
                    mFirebaseAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener {
                        when {
                            !it.isSuccessful -> {
                                Toast.makeText(this, "Erro no login, tente novamente!", Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                startActivity(Intent(this, HomeActivity::class.java))
                            }
                        }
                    }
                }
                else -> Toast.makeText(this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onStart() {
        super.onStart()
        mFirebaseAuth.addAuthStateListener(mAuthStateListener)
    }
}
