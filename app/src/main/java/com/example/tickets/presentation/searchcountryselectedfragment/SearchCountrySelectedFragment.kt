package com.example.tickets.presentation.searchcountryselectedfragment

import android.app.DatePickerDialog
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
import com.example.tickets.presentation.adapters.SearchCountryAdapter
import com.example.tickets.presentation.models.DataForSearch
import com.example.tickets.presentation.models.StateLoad
import com.example.tickets.presentation.models.Way
import com.example.tickets.presentation.showallticketsfragment.ShowAllTicketsFragment
import com.example.tickets.R
import com.example.tickets.databinding.FragmentSearchCountrySelectedBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.Calendar
import javax.inject.Inject

@AndroidEntryPoint
class SearchCountrySelectedFragment : Fragment() {
    private lateinit var way: Way
    private val countPassenger = 1
    private var dateDeparture = LocalDate.now()
    private var dateReturn = LocalDate.now()
    private lateinit var calendar: Calendar
    private lateinit var searchCountryAdapter: SearchCountryAdapter

    @Inject
    lateinit var viewModelsFactory: SearchCountrySelectedViewModelsFactory
    private val viewModel: SearchCountrySelectedViewModel by viewModels { viewModelsFactory }

    private var _binding: FragmentSearchCountrySelectedBinding? = null
    private val binding: FragmentSearchCountrySelectedBinding
        get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            way = it.getParcelable(ARG_PARAM, Way::class.java)
                ?: Way(resources.getString(R.string.moscow), resources.getString(R.string.moscow))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchCountrySelectedBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendar = Calendar.getInstance()
        searchCountryAdapter = SearchCountryAdapter(requireContext())
        showDepartureDate()
        displayPath()
        getListRecommendationsPlacesArrival()
        clickButtonShowAllTicket()
        clickIconSwapPlaces()
        getState()

        binding.buttonDepartureDate.setOnClickListener {
            showCalendar(dateDeparture, ::changeDepartureDate) { showDepartureDate() }
        }

        binding.buttonBackDate.setOnClickListener {
            showCalendar(dateReturn, ::changeReturnDate) { showReturnDate() }
        }

        binding.iconBack.setOnClickListener {
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

    private fun clickIconSwapPlaces() {
        binding.iconSwapPlaces.setOnClickListener {
            way = Way(
                cityWhereFrom = binding.editWhere.text.toString(),
                cityEditWhere = binding.editWhereFrom.text.toString()
            )
            displayPath()
        }
    }

    private fun clickButtonShowAllTicket() {
        binding.buttonShowAllTicket.setOnClickListener {
            val dataForSearch = DataForSearch(way, dateDeparture, countPassenger)
            val bundle = Bundle().apply {
                putParcelable(ShowAllTicketsFragment.ARG_PARAM, dataForSearch)
            }
            findNavController().navigate(
                R.id.action_searchCountrySelectedFragment_to_showAllTicketsFragment,
                args = bundle
            )
        }
    }

    private fun getListRecommendationsPlacesArrival() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.listRecommendationsPlacesArrival.collect {
                searchCountryAdapter.setList(it)
                binding.recyclerForAllStraightRails.adapter = searchCountryAdapter
            }
        }
    }

    private fun declareDownloadError(stateLoad: StateLoad) {
        when (stateLoad) {
            StateLoad.SUCCESS -> binding.textError.visibility = View.GONE
            StateLoad.ERROR -> binding.textError.visibility = View.VISIBLE
        }
    }

    private fun showCalendar(
        date: LocalDate,
        funChangeDate: (year: Int, month: Int, day: Int) -> Unit,
        funcShow: () -> Unit
    ) {
        val datePicker =
            DatePickerDialog(
                requireContext(),
                { _, year: Int, month: Int, day: Int ->
                    funChangeDate(year, month + 1, day)
                    funcShow()
                },
                date.year,
                date.monthValue - 1,
                date.dayOfMonth
            )
        datePicker.show()
    }

    private fun changeDepartureDate(year: Int, month: Int, day: Int) {
        val newDate = LocalDate.of(year, month, day)
        dateDeparture = newDate
    }

    private fun changeReturnDate(year: Int, month: Int, day: Int) {
        val newDate = LocalDate.of(year, month, day)
        dateReturn = newDate
    }

    private fun showDepartureDate() {
        val strDate =
            "${dateDeparture.dayOfMonth} ${resources.getStringArray(R.array.abbreviated_month)[dateDeparture.monthValue]}, ${
                resources.getStringArray(R.array.day)[dateDeparture.dayOfWeek.ordinal]
            }"
        binding.buttonDepartureDate.text = strDate
    }

    private fun showReturnDate() {
        val strDate =
            "${dateReturn.dayOfMonth} ${resources.getStringArray(R.array.abbreviated_month)[dateReturn.monthValue]}, ${
                resources.getStringArray(R.array.day)[dateReturn.dayOfWeek.ordinal]
            }"
        binding.buttonBackDate.text = strDate
        binding.buttonBackDate.icon = null
    }

    private fun displayPath() {
        binding.editWhere.text = way.cityEditWhere
        binding.editWhereFrom.text = way.cityWhereFrom
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val ARG_PARAM = "ARG_PARAM"
    }
}
