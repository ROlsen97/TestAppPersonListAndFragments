package com.example.viewmodelsimpleexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewmodelsimpleexample.databinding.FragmentFirstBinding
import com.example.viewmodelsimpleexample.databinding.FragmentThirdBinding

class PersonListFragment: Fragment() {
    private var _binding: FragmentThirdBinding? = null
    private val viewModel: PersonViewModel by activityViewModels()

    private val binding get() = _binding!!

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // configure RecyclerView

        // https://medium.com/geekculture/everything-you-should-know-to-create-a-recyclerview-3defdb660a2f
         //binding.recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, true)
        //binding.recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        //val students: List<Student> = viewModel.students.value!!

        viewModel.persons.observe(viewLifecycleOwner) {persons->
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)
            val adapter = PersonsAdapter(persons) { position ->
                viewModel.selected.value = viewModel[position]
                viewModel.adding.value = false
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
            binding.recyclerView.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}