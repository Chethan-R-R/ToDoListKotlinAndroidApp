package com.example.todolist

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager
import com.example.todolist.databinding.FragmentListBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_list, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        binding.addBtn.setOnClickListener {
            it.findNavController()
                .navigate(ListFragmentDirections.actionListFragmentToAddToListFragment())
        }
        binding.completedBtn.setOnClickListener {
            it.findNavController()
                .navigate(ListFragmentDirections.actionListFragmentToCompletedFragment())
        }
        populateData()

        return binding.root
    }

    fun populateData() {
        for ((index, i) in GlobalData.titleList.withIndex()) {
            if (!GlobalData.completed[index]) {
                val context = requireContext()
                val container = LinearLayout(context)
                val layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(0,5,0,5)
                container.layoutParams=layoutParams
                container.orientation = LinearLayout.HORIZONTAL

                val item1 = TextView(context)
                item1.text = i
                item1.textSize = 24f
                item1.layoutParams = LinearLayout.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    3f
                )
                val item2 = LinearLayout(context)
                item2.layoutParams = LinearLayout.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    1f
                )
                item2.orientation = LinearLayout.VERTICAL

                val doneBtn = Button(context)
                doneBtn.text = "Done"
                doneBtn.setOnClickListener {
                    GlobalData.completed[index] = !GlobalData.completed[index]
                    if (GlobalData.completed[index]) {
                        container.setBackgroundColor(Color.GREEN)
                        doneBtn.text = "not done"
                    } else {
                        container.setBackgroundColor(0)
                        doneBtn.text = "Done"
                    }
                }
                if (GlobalData.completed[index]) {
                    container.setBackgroundColor(Color.GREEN)
                    doneBtn.text = "not done"
                }

                val descBtn = Button(context)
                descBtn.text = "More"
                descBtn.setOnClickListener {
                    it.findNavController().navigate(
                        ListFragmentDirections.actionListFragmentToToDoDiscriptionFragment(index)
                    )
                }

                item2.addView(doneBtn)
                item2.addView(descBtn)
                container.addView(item1)
                container.addView(item2)
                binding.listitems.addView(container)
            }
        }
    }



}
