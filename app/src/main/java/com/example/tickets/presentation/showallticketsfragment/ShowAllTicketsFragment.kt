package com.example.tickets.presentation.showallticketsfragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tickets.R
import com.example.tickets.databinding.FragmentShowAllTicketsBinding
import com.example.tickets.presentation.adapters.AllTicketsAdapter
import com.example.tickets.presentation.models.DataForSearch
import com.example.tickets.presentation.models.StateLoad
import com.example.tickets.presentation.models.Way
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@AndroidEntryPoint
class ShowAllTicketsFragment : Fragment() {
    private lateinit var dataForSearch: DataForSearch
    private var _binding: FragmentShowAllTicketsBinding? = null
    private val binding: FragmentShowAllTicketsBinding
        get() = _binding!!
    private lateinit var allTicketsAdapter: AllTicketsAdapter

    @Inject
    lateinit var viewModelsFactory: ShowAllTicketsViewModelFactory
    private val viewModel: ShowAllTicketsViewModel by viewModels { viewModelsFactory }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dataForSearch = it.getParcelable(ARG_PARAM, DataForSearch::class.java)
                ?: DataForSearch(Way("", ""), LocalDate.now(), DEFAULT_COUNT_PASSENGER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowAllTicketsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showDataSearch()
        allTicketsAdapter = AllTicketsAdapter()
        binding.scrollForListTickets.adapter = allTicketsAdapter
        getAllTickets()
        getState()
        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun getState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect {
                declareDownloadError(it)
            }
        }
    }

    private fun getAllTickets() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.allTickets.collect {
                allTicketsAdapter.changeData(it)
            }
        }
    }

    private fun declareDownloadError(stateLoad: StateLoad) {
        when (stateLoad) {
            StateLoad.SUCCESS -> binding.textError.visibility = View.GONE
            StateLoad.ERROR -> binding.textError.visibility = View.VISIBLE
        }
    }

    private fun showDataSearch() {
        showWay()
        showDepartureDate()
        showCountPassenger()
    }

    @SuppressLint("SetTextI18n")
    private fun showWay() {
        binding.titleWay.text =
            "${dataForSearch.way.cityWhereFrom}-${dataForSearch.way.cityEditWhere}"
    }

    private fun showDepartureDate() {
        val strDate =
            "${dataForSearch.departureDate.dayOfMonth} ${resources.getStringArray(R.array.month)[dataForSearch.departureDate.monthValue]}"
        binding.date.text = strDate
    }

    private fun showCountPassenger() {
        binding.countPassenger.text = dataForSearch.countPassenger.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val ARG_PARAM = "ARG_PARAM"
        private const val DEFAULT_COUNT_PASSENGER = 1
    }
}
