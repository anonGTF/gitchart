package com.galih.gitchart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.galih.gitchart.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AccountAdapter
    private lateinit var list: ArrayList<Account>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = getData()
        list.map {
            it.avatarId = getAvatarId(it)
        }
        adapter = AccountAdapter(list)
        binding.rvAccountList.setHasFixedSize(true)
        binding.rvAccountList.layoutManager = LinearLayoutManager(this)
        binding.rvAccountList.adapter = adapter

        adapter.setOnItemClickCallback(object : AccountAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Account) {
                showSelectedAccount(data)
            }
        })
    }

    private fun getAvatarId(data: Account): Int =
        resources.getIdentifier(data.avatar, null, packageName)

    private fun getData(): ArrayList<Account> {
        val jsonFileString = getJsonDataFromAsset(applicationContext, "githubuser.json")
        val gson = Gson()
        val accounts: Users = gson.fromJson(jsonFileString, Users::class.java)
        return accounts.users
    }

    private fun showSelectedAccount(data : Account){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("extra", data)
        startActivity(intent)
    }

    inner class Users (
        val users: ArrayList<Account>
    )
}