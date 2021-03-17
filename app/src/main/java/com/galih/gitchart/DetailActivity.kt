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

        val userData = intent.getParcelableExtra<User>("extra")
        binding.tvDetailName.text = userData?.name
        binding.tvDetailUsername.text = userData?.username
        binding.tvDetailRepository.text = userData?.repository
        binding.tvDetailFollower.text = userData?.follower
        binding.tvDetailFollowing.text = userData?.following
        binding.tvDetailLocation.text = userData?.location
        binding.tvDetailWork.text = userData?.company

        val image = resources.getIdentifier(userData?.avatar, null, packageName)

        Glide.with(this)
            .load(image)
            .apply(RequestOptions().override(150))
            .into(binding.imgDetail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = R.string.detail_user.toString()
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