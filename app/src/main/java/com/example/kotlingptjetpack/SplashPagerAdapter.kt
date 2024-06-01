package com.example.kotlingptjetpack




import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SplashPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> SplashFragment.newInstance(R.layout.fragment_first)
            1 -> SplashFragment.newInstance(R.layout.fragment_second)
            2 -> SplashFragment.newInstance(R.layout.fragment_third)
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }

    override fun getCount(): Int {
        return 3 // number of pages
    }
}

