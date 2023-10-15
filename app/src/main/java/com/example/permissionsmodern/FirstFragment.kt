package com.example.permissionsmodern

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.permissionsmodern.databinding.FragmentFirstBinding
import com.google.android.material.snackbar.Snackbar

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val requestPermission =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    Log.i("DEBUG", "permission granted")
                    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                } else {
                    Log.i("DEBUG", "permission denied")
                    Snackbar.make(
                        binding.firstFragment,
                        "Sorry, you cannot go",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }

        binding.buttonFirst.setOnClickListener {
            Log.d("DEBUG", "buttonFirst.setOnClickListener")
            requestPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}