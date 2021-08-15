package com.dio.businesscard.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.dio.businesscard.App
import com.dio.businesscard.data.MainViewModel
import com.dio.businesscard.data.MainViewModelFactory
import com.dio.businesscard.databinding.ActivityMainBinding
import com.dio.businesscard.util.Image

class MainActivity : AppCompatActivity() {

    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}

    private val mainViewModel: MainViewModel by viewModels{
        MainViewModelFactory((application as App).repository)
    }

    private val adapter by lazy { BusinessCardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rvCards.adapter = adapter
        setContentView(binding.root)
        insertListeners()
        getAllBusinessCards()
    }


     private fun insertListeners(){

         binding.fatAdd.setOnClickListener {
             val intent = Intent(this, AddBusinessCardActivity::class.java)
             startActivity(intent)
         }

     }

    private fun getAllBusinessCards(){
        mainViewModel.getAll().observe(this) { businessCards ->
            adapter.submitList(businessCards)
        }

        adapter.listenerShare = { card ->

            Image.share(this@MainActivity, card)
        }
    }

}