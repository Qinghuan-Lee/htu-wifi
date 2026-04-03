package com.example.campusnethelper.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.campusnethelper.data.Preferences
import com.example.campusnethelper.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val prefs = Preferences(requireContext())
        binding.autoLoginSwitch.isChecked = prefs.autoLoginEnabled()
        binding.wifiDetectSwitch.isChecked = prefs.wifiDetectEnabled()
        binding.autoLoginSwitch.setOnCheckedChangeListener { _, isChecked -> prefs.setAutoLogin(isChecked) }
        binding.wifiDetectSwitch.setOnCheckedChangeListener { _, isChecked -> prefs.setWifiDetect(isChecked) }
        binding.logButton.setOnClickListener { }
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
