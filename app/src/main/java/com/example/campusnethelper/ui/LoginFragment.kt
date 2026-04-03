package com.example.campusnethelper.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.campusnethelper.data.Account
import com.example.campusnethelper.data.Preferences
import com.example.campusnethelper.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val prefs = Preferences(requireContext())
        binding.rememberCheck.isChecked = prefs.rememberCredentials()
        binding.loginButton.setOnClickListener {
            val acc = binding.accountInput.text?.toString().orEmpty()
            val pwd = binding.passwordInput.text?.toString().orEmpty()
            val remember = binding.rememberCheck.isChecked
            if (remember) prefs.setRememberCredentials(true) else prefs.setRememberCredentials(false)
            if (acc.isNotEmpty() && pwd.isNotEmpty()) {
                prefs.setDefaultAccountId(acc)
                binding.statusText.text = "登录成功"
                findNavController().navigate(com.example.campusnethelper.R.id.action_login_to_home)
            } else {
                binding.statusText.text = "账号或密码错误"
            }
        }
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
