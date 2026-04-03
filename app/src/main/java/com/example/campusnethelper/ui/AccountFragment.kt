package com.example.campusnethelper.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.campusnethelper.data.Preferences
import com.example.campusnethelper.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {
    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val prefs = Preferences(requireContext())
        binding.accountCard1.setOnClickListener {
            prefs.setDefaultAccountId(binding.accountNumber1.text.toString())
            Toast.makeText(requireContext(), "设为默认账号", Toast.LENGTH_SHORT).show()
        }
        binding.accountCard2.setOnClickListener {
            prefs.setDefaultAccountId(binding.accountNumber2.text.toString())
            Toast.makeText(requireContext(), "设为默认账号", Toast.LENGTH_SHORT).show()
        }
        binding.accountCard2.setOnLongClickListener {
            Toast.makeText(requireContext(), "删除账号", Toast.LENGTH_SHORT).show()
            true
        }
        binding.addAccountFab.setOnClickListener {
            Toast.makeText(requireContext(), "添加账号", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
