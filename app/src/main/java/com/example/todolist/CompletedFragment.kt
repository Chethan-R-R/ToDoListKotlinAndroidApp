package com.example.todolist

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.todolist.databinding.FragmentCompletedBinding

class CompletedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_completed, container, false)
        val binding=FragmentCompletedBinding.inflate(inflater,container,false)

        var titleList = GlobalData.titleList
        var completed = GlobalData.completed
        for ((index, i) in titleList.withIndex()) {
            if(completed[index]) {
                val container = LinearLayout(context)
                container.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                container.orientation = LinearLayout.HORIZONTAL

                val item1 = TextView(context)
//            item1.setTextAppearance(R.style.todoitem)
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
                    completed[index] = !completed[index]
                    if (completed[index]) {
                        container.setBackgroundColor(Color.GREEN)
                        doneBtn.text = "not done"
                    } else {
                        container.setBackgroundColor(0)
                        doneBtn.text = "Done"
                    }
                }
                if (completed[index]) {
                    container.setBackgroundColor(Color.GREEN)
                    doneBtn.text = "not done"
                }

                val descBtn = Button(context)
                descBtn.text = "More"


                item2.addView(doneBtn)
                item2.addView(descBtn)
                container.addView(item1)
                container.addView(item2)
                binding.listitems.addView(container)
            }
        }



        return binding.root
    }

}