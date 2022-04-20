package com.example.mymovielist.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TableLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.mymovielist.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PreLoginActivity : AppCompatActivity() {
    var tabTitle = arrayOf("Login", "Sign Up")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pre_login)
        var pager = findViewById<ViewPager2>(R.id.viewPager)
        var tab1 = findViewById<TabLayout>(R.id.tabLayout2)
        pager.adapter = PageAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(tab1, pager){
            tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }
}