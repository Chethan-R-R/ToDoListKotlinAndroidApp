package com.example.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.todolist.databinding.FragmentAddToListBinding


class AddToListFragment : Fragment() {
    private lateinit var binding: FragmentAddToListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add_to_list, container, false)
        binding = FragmentAddToListBinding.inflate(inflater, container, false)
        binding.add.setOnClickListener {
            GlobalData.titleList.add(binding.enterTitle.text.toString())
            GlobalData.descriptionList.add(binding.enterDescription.text.toString())
            GlobalData.completed.add(false)
            it.findNavController().navigate(AddToListFragmentDirections.actionAddToListFragmentToListFragment())
        }
        return binding.root
    }

}