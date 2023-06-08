package com.example.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todolist.databinding.FragmentToDoDiscriptionBinding


class ToDoDescriptionFragment : Fragment() {
    private lateinit var binding: FragmentToDoDiscriptionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_to_do_discription, container, false)
        binding= FragmentToDoDiscriptionBinding.inflate(inflater,container,false)
        val args=ToDoDescriptionFragmentArgs.fromBundle(requireArguments())
        val titlelist=GlobalData.titleList
        val descriptionlist=GlobalData.descriptionList
        binding.title.text=titlelist[args.index]
        binding.description.text=descriptionlist[args.index]
        binding.editBtn.setOnClickListener {
            binding.display.visibility=View.GONE
            binding.Edittitle.setText(titlelist[args.index])
            binding.Editdescription.setText(descriptionlist[args.index])
            binding.edit.visibility=View.VISIBLE
        }
        binding.submitbtn.setOnClickListener {
            titlelist[args.index]=binding.Edittitle.text.toString()
            descriptionlist[args.index]=binding.Editdescription.text.toString()
            binding.title.text=titlelist[args.index]
            binding.description.text=descriptionlist[args.index]
            binding.display.visibility=View.VISIBLE
            binding.edit.visibility=View.GONE

        }
        return binding.root
    }

}