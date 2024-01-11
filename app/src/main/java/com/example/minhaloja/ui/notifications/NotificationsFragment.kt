package com.example.minhaloja.ui.notifications

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.minhaloja.R
import com.example.minhaloja.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val view: View = binding.root

        _binding!!.call.setOnClickListener{
            ligar(view)
        }
        _binding!!.email.setOnClickListener{
            enviarEmail(view)
        }
        _binding!!.zap.setOnClickListener{
            enviarZap(view)
        }

       // val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
           // textView.text = it
        }
        return view
    }

    fun enviarZap(view: View) {
        openWhatsApp()
    }

    fun enviarEmail(view: View) {
        openGmail()
    }

    fun ligar(view: View) {
        call()
    }

    private fun openGmail() {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("mailto:" + getString(R.string.emailId) + "?subject=" + getString(R.string.app_name) + " feedback")
            startActivity(Intent.createChooser(intent, "E_mail"))
        } catch (e: ActivityNotFoundException) {
            // Tratar exceção, se necessário
        }
    }

    private fun call() {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:" + getString(R.string.contactNo))
        startActivity(intent)
    }

    private fun openWhatsApp() {
        try {
            val toNumber = getString(R.string.contactNo)
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://api.whatsapp.com/send?phone=$toNumber&text=")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}