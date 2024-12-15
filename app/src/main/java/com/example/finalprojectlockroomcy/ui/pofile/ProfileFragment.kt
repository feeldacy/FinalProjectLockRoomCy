package com.example.finalprojectlockroomcy.ui.pofile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.finalprojectlockroomcy.databinding.FragmentProfileBinding
import com.example.finalprojectlockroomcy.ui.auth.LoginActivity
import com.example.finalprojectlockroomcy.ui.auth.RegisterActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val sharedPreferences = requireContext().getSharedPreferences("userData", Context.MODE_PRIVATE)


        val username = sharedPreferences.getString("username", null)
        val email = sharedPreferences.getString("email", null)
        val phone = sharedPreferences.getString("phone", null)

        binding.txtUsername.text = "$username"
        binding.txtUserEmail.text = "$email"
        binding.txtUserPhone.text = "$phone"


        binding.icLogout.setOnClickListener{
            sharedPreferences.edit().clear().apply()
            Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show()

            val intent = Intent(requireContext(), RegisterActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}