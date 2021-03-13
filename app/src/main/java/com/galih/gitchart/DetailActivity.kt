package com.galih.gitchart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.galih.gitchart.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val accountData = intent.getParcelableExtra<Account>("extra")
        binding.tvDetailName.text = accountData?.name
        binding.tvDetailUsername.text = accountData?.username
        binding.tvDetailRepository.text = accountData?.repository
        binding.tvDetailFollower.text = accountData?.follower
        binding.tvDetailFollowing.text = accountData?.following
        binding.tvDetailLocation.text = accountData?.location
        binding.tvDetailWork.text = accountData?.company

        Glide.with(this)
            .load(accountData?.avatarId)
            .apply(RequestOptions().override(150))
            .into(binding.imgDetail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Detail Account"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}