package com.example.tickets.presentation.airticketsfragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.tickets.presentation.adapters.MusicallyAdapter
import com.example.tickets.presentation.models.StateLoad
import com.example.tickets.databinding.FragmentAirTicketsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AirTicketsFragment : Fragment() {
    private lateinit var adapter: MusicallyAdapter

    @Inject
    lateinit var airTicketsViewModelsFactory: AirTicketsViewModelsFactory
    private val viewModel: AirTicketsViewModel by viewModels { airTicketsViewModelsFactory }

    private var _binding: FragmentAirTicketsBinding? = null
    private val binding: FragmentAirTicketsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAirTicketsBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MusicallyAdapter(requireContext())
        binding.recyclerMusically.adapter = adapter

        getEditWhereFrom()
        getOfferToFly()
        getState()
        clickEditWhere()
    }

    private fun clickEditWhere() {
        binding.editWhere.setOnClickListener {
            val modalBottomSheet = CustomSearchDialogFragment()
            val bundle = Bundle()
            bundle.putString(
                CustomSearchDialogFragment.ARG_PARAM_TASK,
                binding.editWhereFrom.text.toString()
            )
            modalBottomSheet.arguments = bundle
            modalBottomSheet.show(childFragmentManager, CustomSearchDialogFragment.TAG)
        }
    }

    private fun getState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect {
                declareDownloadError(it)
            }
        }
    }

    private fun getEditWhereFrom() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.city.collect {
                binding.editWhereFrom.setText(it)
            }
        }
    }

    private fun getOfferToFly() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.offerToFly.collect {
                adapter.setList(it)
            }
        }
    }

    private fun declareDownloadError(stateLoad: StateLoad) {
        when (stateLoad) {
            StateLoad.SUCCESS -> binding.textError.visibility = View.GONE
            StateLoad.ERROR -> binding.textError.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.saveCityName(binding.editWhereFrom.text.toString())
        _binding = null
    }
}
