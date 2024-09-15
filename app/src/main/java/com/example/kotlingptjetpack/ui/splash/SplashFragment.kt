package com.example.kotlingptjetpack.ui.splash


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.kotlingptjetpack.R
import com.example.kotlingptjetpack.ui.main.MainActivity

class SplashFragment : Fragment() {

    companion object {
        private const val ARG_LAYOUT_RES_ID = "layoutResId"

        fun newInstance(layoutResId: Int): SplashFragment {
            val fragment = SplashFragment()
            val args = Bundle()
            args.putInt(ARG_LAYOUT_RES_ID, layoutResId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutResId = arguments?.getInt(ARG_LAYOUT_RES_ID) ?: 0
        val view = inflater.inflate(layoutResId, container, false)

        // Handle finish button click if it's the third fragment layout
        if (layoutResId == R.layout.fragment_third) {
            val finishButton: Button = view.findViewById(R.id.finishButton)
            finishButton.setOnClickListener {
                startActivity(Intent(activity, MainActivity::class.java))
                activity?.finish() // Finish SplashActivity so that it cannot be returned to
            }
        }

        return view
    }
}

