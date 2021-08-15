package com.dio.businesscard.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.dio.businesscard.App
import com.dio.businesscard.R
import com.dio.businesscard.data.BusinessCard
import com.dio.businesscard.data.MainViewModel
import com.dio.businesscard.data.MainViewModelFactory
import com.dio.businesscard.databinding.ActivityAddBusinessCardBinding


class AddBusinessCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater) }

    private val mainViewModel: MainViewModel by viewModels{
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
    }


    private fun insertListeners(){

        binding.imbtClose.setOnClickListener {
           finish()
        }

        binding.btConfirm.setOnClickListener {
            val businessCard = BusinessCard(
                name = binding.tilName.editText?.text.toString(),
                company = binding.tilCompany.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                phone = binding.tilPhone.editText?.text.toString(),
                background = binding.tilColor.editText?.text.toString()
            )

            mainViewModel.insert(businessCard)
            Toast.makeText(this, R.string.label_show_success,Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}