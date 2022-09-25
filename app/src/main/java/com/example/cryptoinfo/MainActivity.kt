package com.example.cryptoinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptoinfo.base.OnBackButtonListener
import com.example.cryptoinfo.crypto_list.view.CryptoListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, CryptoListFragment())
            .commit()
    }

    override fun onBackPressed() {
        val backStackCount = supportFragmentManager.backStackEntryCount
        if (backStackCount > 0) {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
            if (currentFragment is OnBackButtonListener && currentFragment.onBackPressed()) {
                return
            }
        }
        super.onBackPressed()
    }
}