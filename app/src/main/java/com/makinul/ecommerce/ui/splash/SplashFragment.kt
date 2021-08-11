package com.makinul.ecommerce.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.android.hilt.navigator.Screens
import com.makinul.ecommerce.R
import com.makinul.ecommerce.ui.main.MainActivity
import com.makinul.ecommerce.ui.settings.SettingsActivity
import com.makinul.ecommerce.util.DateFormatter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    @Inject
    lateinit var dateFormatter: DateFormatter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        holder.textView.text = "${log.msg}\n\t${daterFormatter.formatDate(log.timestamp)}"

        val currentDate = dateFormatter.formatDate(System.currentTimeMillis())
        Log.v(TAG, "currentDate: $currentDate")

        view.findViewById<Button>(R.id.all_logs).setOnClickListener {
            val mainIntent = Intent(activity, SettingsActivity::class.java)
            requireActivity().startActivity(mainIntent)
            requireActivity().finish()
        }
    }

    companion object {
        const val TAG = "SplashFragment"
    }
}