package com.example.mymovielist.onboardingscreen

import com.example.mymovielist.R

data class OnBoardingData(val image: Int, val tittle: String, val description: String)

val onBoardItem = listOf(
    OnBoardingData(
        R.drawable.ic_drama, "Make your own movie list", "You can create your personal movie library list"
    ),
    OnBoardingData(
        R.drawable.ic_drama, "Review a movie", "Create a full movie review as you express yourself"
    ),
    OnBoardingData(
        R.drawable.ic_drama, "Ranking movies", "Our service provides a long top rated movies api so you can look for the perfect movie!"
    )
)
