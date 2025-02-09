package com.example.mplcricket

import androidx.appcompat.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class ScoreBoardAddActivity : AppCompatActivity() {
    private var bolls_in_over:Int=0
    private var Player_one_hash_map:HashMap<Any,Any> = HashMap()
    private var Player_two_hash_map:HashMap<Any,Any> = HashMap()
    private var team_one_hash_map:HashMap<Any,Any> = HashMap()
    private var team_two_hash_map:HashMap<Any,Any> = HashMap()
    private var match_hash_map:HashMap<Any,Any> = HashMap()
    private lateinit var arry_list_of_team_one_player_name:Array<String>
    private lateinit var arry_list_of_team_two_player_name:Array<String>
    private lateinit var arry_list_of_team_one_player_uid:Array<String>
    private lateinit var arry_list_of_team_two_player_uid:Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_board_add)
        setSupportActionBar(findViewById(R.id.score_bord_app_bar_layout))
        supportActionBar?.title = " Cit Abu v/s  cit mount"


        val data = intent.extras
        val country = data?.getString("pushkey")
        Toast.makeText(this,country,Toast.LENGTH_LONG).show()



    }
    override fun onBackPressed() {
        super.onBackPressed()
        AlertDialog.Builder(this)
            .setTitle("Alert")
            .setMessage("if you go back match will be paused")
            .setNegativeButton("cancel", null) // dismisses by defaultbuilder.setPositiveButton(android.R.string.yes) { dialog, which ->
            .setPositiveButton("it's ok") { dialog, which ->
                startActivity(Intent(this,MainActivity::class.java))
            }
            .create()
            .show()
    }
}