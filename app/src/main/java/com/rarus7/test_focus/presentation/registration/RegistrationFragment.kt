package com.rarus7.test_focus.presentation.registration

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.View

import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.rarus7.test_focus.R
import com.rarus7.test_focus.databinding.FragmentRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegistrationFragment :
    Fragment(R.layout.fragment_registration) {

    private val vm: RegistrationViewModel by viewModels()

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        _binding = FragmentRegistrationBinding.bind(view)

        binding.registerButton.setOnClickListener {

            vm.register(
                binding.nameEditText.text.toString(),
                binding.emailEditText.text.toString(),
                binding.passwordEditText.text.toString(),
                binding.confirmPasswordEditText.text.toString()
            )
        }

        observe()
    }

    private fun observe() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.toast.collect {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
