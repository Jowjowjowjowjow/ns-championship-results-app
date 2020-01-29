package com.example.nschampionshipresultsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.models.Player
import kotlinx.android.synthetic.main.players_list_item.view.*

class PlayersListAdapter(private val context: Context) :
    RecyclerView.Adapter<PlayersListAdapter.PlayersViewHolder>() {

    var playersList: List<Player> = listOf()

    inner class PlayersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(player: Player) {
            itemView.textViewPlayerName.text = player.name
            itemView.textViewPlayerTeam.text = player.team
            itemView.setOnLongClickListener {
                //editPlayer.onPlayerLongClick(player)
                Toast.makeText(
                    context,
                    "Clique longo no jogador " + player.name,
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnLongClickListener true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder =
        PlayersViewHolder(
            LayoutInflater.from(context).inflate
                (R.layout.players_list_item, parent, false)
        )


    override fun getItemCount() = playersList.size

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) =
        holder.bindView(playersList[position])

    fun setList(playersList: List<Player>) {
        this.playersList = playersList
        notifyDataSetChanged()
    }
}