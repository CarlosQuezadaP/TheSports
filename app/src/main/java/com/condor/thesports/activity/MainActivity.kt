package com.condor.thesports.activity

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.condor.thesports.R
import com.condor.thesports.activity.base.BaseActivity
import com.condor.thesports.databinding.ActivityMainBinding
import org.koin.androidx.fragment.android.setupKoinFragmentFactory

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val navController: NavController by lazy { findNavController(R.id.fragment_nav_host) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)

        databinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(databinding?.root)

        val btmNavMain = databinding?.bottomNavigation?.apply {
            setupWithNavController(navController)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.listTeams -> btmNavMain?.visibility = View.VISIBLE
                else -> btmNavMain?.visibility = View.GONE
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}