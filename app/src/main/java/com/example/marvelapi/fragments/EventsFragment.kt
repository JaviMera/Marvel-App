package com.example.marvelapi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.marvelapi.adapters.events.EventsAdapter
import com.example.marvelapi.common.RecyclerAdapterBase
import com.example.marvelapi.databinding.FragmentEventsBinding
import com.example.marvelapi.viewmodels.events.EventsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventsFragment : Fragment() {

    private var _binding: FragmentEventsBinding? = null
    private val binding get() = _binding!!

    private val eventsViewModel: EventsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEventsBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this

        val adapter = EventsAdapter(RecyclerAdapterBase.OnItemClickListener {

        })
        binding.eventsList.adapter = adapter

        eventsViewModel.getEvents().observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
        return binding.root
    }
}