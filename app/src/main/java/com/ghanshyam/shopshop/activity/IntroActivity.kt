package com.ghanshyam.shopshop.activity

import android.content.Intent
import android.os.Bundle
import com.ghanshyam.shopshop.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {
    private lateinit var binding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            discover.setOnClickListener {
                val i = Intent(applicationContext, MainActivity::class.java)
                startActivity(i)
            }
        }
    }
}