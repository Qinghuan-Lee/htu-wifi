package com.example.campusnethelper.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.campusnethelper.R
import com.example.campusnethelper.data.Account
import com.example.campusnethelper.data.Preferences
import com.example.campusnethelper.databinding.FragmentHomeBinding
import com.example.campusnethelper.network.LoginRepository

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val vm by viewModels<HomeViewModel> { androidx.lifecycle.ViewModelProvider.NewInstanceFactory() }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val prefs = Preferences(requireContext())
        val id = prefs.defaultAccountId()
        val acc = if (id != null) Account(id, id, "", true) else null
        vm.loadDefaultAccount(acc)
        binding.accountText.text = "登录账号 ${id ?: "-"}"
        binding.reconnectButton.setOnClickListener {
            vm.reconnect()
        }
        binding.accountEntry.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_account)
        }
        binding.settingsEntry.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_settings)
        }
        vm.state.observe(viewLifecycleOwner) { s ->
            when (s) {
                is NetState.Disconnected -> {
                    binding.statusText.text = "未连接"
                    binding.statusIcon.setColorFilter(Color.parseColor("#E74C3C"))
                    binding.ipText.text = "IP地址 -"
                }
                is NetState.Connecting -> {
                    binding.statusText.text = "正在连接"
                    binding.statusIcon.setColorFilter(Color.parseColor("#F1C40F"))
                }
                is NetState.Connected -> {
                    binding.statusText.text = "已连接"
                    binding.statusIcon.setColorFilter(Color.parseColor("#2ECC71"))
                    binding.ipText.text = "IP地址 ${s.ip}"
                }
            }
        }
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
