package com.condor.thesports.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.condor.thesports.databinding.ActivityMainBinding
import com.condor.thesports.helpers.AutoFitGridLayoutManager

class MainActivity : AppCompatActivity() {


    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDataBinding()
        setupRecyclerView()
    }

    private fun setupDataBinding() {
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
    }

    private fun setupRecyclerView() {
        mainBinding.recyclerViewTeams.layoutManager = AutoFitGridLayoutManager(this, 2)
    }
}