package com.example.nschampionshipresultsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.models.Player
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*

class HomeActivity : AppCompatActivity(), OnPlayerLongClickListener,
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: PlayersListAdapter
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Resultados NS"

        database = FirebaseDatabase.getInstance().reference

        var toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this, item.itemId, Toast.LENGTH_SHORT).show()
        when (item.itemId) {
            R.id.nav_game -> {
                startActivity(Intent(this, SignUpActivity::class.java))
                //Toast.makeText(this,"Clicou no primeiro item", Toast.LENGTH_SHORT).show()
            }
            R.id.logout -> {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setupRecycler() {
        mAdapter = PlayersListAdapter(this)
        // Configurando o gerenciador de layout para ser uma lista.
        val layoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = layoutManager

        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente
        mAdapter.setList(listOf())
        mRecyclerView.adapter = mAdapter

        // Configurando um divider entre linhas, para uma melhor visualização
        mRecyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
    }

    override fun onPlayerLongClick(player: Player) {

    }

}
