package com.example.viewmodelsimpleexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.viewmodelsimpleexample.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PersonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSaveAndGreet.setOnClickListener {
            val name = binding.editTextName.text.trim().toString()
            if (name.isEmpty()) {
                binding.editTextName.error = "No name"
                return@setOnClickListener
            }
            val ageStr = binding.editTextAge.text.trim().toString()
            if (ageStr.isEmpty()) {
                binding.editTextAge.error = "No age"
                return@setOnClickListener
            }
            val gender = binding.editTextGender.text.trim().toString()
            if (gender.isEmpty()) {
                binding.editTextGender.error = "No gender"
                return@setOnClickListener
            }
            val person : Person = Person(0,name, ageStr.toInt(), gender)
            viewModel.selected.value = person
        }

        viewModel.selected.observe(viewLifecycleOwner){ person ->
            binding.textviewName.text =
                "Navn: ${person.name}\n" + "Age: ${person.age}\n" + "Gender: ${person.gender}"
        }

        binding.buttonAdd.setOnClickListener {
            val name = binding.editTextName.text.trim().toString()
            val age = binding.editTextAge.text.trim().toString().toInt()
            val gender = binding.editTextGender.text.trim().toString()
            val person = Person(
                name = name,
                age = age,
                gender = gender,
            )
            viewModel.add(person)
            println("Added person")
            findNavController().popBackStack()
        }

        binding.buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}