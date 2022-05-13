package com.example.mymovielist.onboardingscreen
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.viewpager.widget.ViewPager
import com.example.mymovielist.R
import com.example.mymovielist.login.PreLoginActivity

class OnBoardingMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_main)

        val viewpager = findViewById<ViewPager>(R.id.viewPager)
        val dot1 = findViewById<ImageView>(R.id.dot1)
        val dot2 = findViewById<ImageView>(R.id.dot2)
        val dot3 = findViewById<ImageView>(R.id.dot3)
        val btnSkip = findViewById<Button>(R.id.bttnSkip)
        val btnGetStarted = findViewById<Button>(R.id.bttnGetStarted)
        viewpager.adapter = ViewPagerAdapter(supportFragmentManager)

        viewpager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }
            override fun onPageSelected(position: Int) {
                if (position == 0){
                    btnSkip.isVisible = true
                    btnGetStarted.isGone = true
                    dot1.setImageResource(R.drawable.selected_indicator)
                    dot2.setImageResource(R.drawable.unselected_indicator)
                    dot3.setImageResource(R.drawable.unselected_indicator)
                }else if(position == 1){
                    btnSkip.isVisible = true
                    btnGetStarted.isGone = true
                    dot1.setImageResource(R.drawable.unselected_indicator)
                    dot2.setImageResource(R.drawable.selected_indicator)
                    dot3.setImageResource(R.drawable.unselected_indicator)
                }else{
                    btnSkip.isGone = true
                    btnGetStarted.isVisible = true
                    dot1.setImageResource(R.drawable.unselected_indicator)
                    dot2.setImageResource(R.drawable.unselected_indicator)
                    dot3.setImageResource(R.drawable.selected_indicator)
                }
            }
        })

        btnSkip.setOnClickListener {
            val intent = Intent(applicationContext, PreLoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnGetStarted.setOnClickListener {
            val intent = Intent(applicationContext, PreLoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}