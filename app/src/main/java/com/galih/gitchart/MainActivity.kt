package com.galih.gitchart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.galih.gitchart.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private lateinit var list: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "User list"

        list = getData()
        adapter = UserAdapter(list)
        binding.rvUserList.setHasFixedSize(true)
        binding.rvUserList.layoutManager = LinearLayoutManager(this)
        binding.rvUserList.adapter = adapter

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                showSelectedUser(data)
            }
        })
    }

    private fun getData(): ArrayList<User> {
        val jsonFileString = getJsonDataFromAsset(applicationContext, "githubuser.json")
        val gson = Gson()
        val users: Users = gson.fromJson(jsonFileString, Users::class.java)
        return users.users
    }

    private fun showSelectedUser(data : User){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("extra", data)
        startActivity(intent)
    }

    inner class Users (
        val users: ArrayList<User>
    )
}