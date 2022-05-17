package com.example.mymovielist.models.TopFilms

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class PageAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle){
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return TopFilmsInformationFragment()
            1 -> return TopFilmsActorsFragment()
            2 -> return TopFilmsReviewsFragment()
            else -> return TopFilmsReviewsFragment()
        }
    }
}
