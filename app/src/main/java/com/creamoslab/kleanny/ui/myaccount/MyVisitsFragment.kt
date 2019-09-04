package com.creamoslab.kleanny.ui.myaccount


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.creamoslab.kleanny.R
import com.creamoslab.kleanny.ui.adapter.MyVisitsAdapter
import kotlinx.android.synthetic.main.fragment_my_visits.*

class MyVisitsFragment : Fragment() {

    private val mTestPlacesList = listOf(
        "Restaurante La Mexicana",
        "Plaza Lomas",
        "Sanitaro público Fernando",
        "Cocina Lupita",
        "Motel Las Habanas",
        "Estación San Pedro"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_visits, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView_visits.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = MyVisitsAdapter(mTestPlacesList)
        }
    }
}
