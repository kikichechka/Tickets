package com.example.tickets.presentation.airticketsfragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tickets.presentation.searchcountryselectedfragment.SearchCountrySelectedFragment
import com.example.tickets.presentation.adapters.PopularDestinationAdapter
import com.example.tickets.presentation.models.Way
import com.example.tickets.R
import com.example.tickets.databinding.FragmentCustomSearchDialogBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
@SuppressLint("ViewConstructor")
class CustomSearchDialogFragment : BottomSheetDialogFragment() {
    private lateinit var cityName: String
    private lateinit var popularDestinationAdapter: PopularDestinationAdapter

    @Inject
    lateinit var airTicketsViewModelsFactory: AirTicketsViewModelsFactory
    private val viewModel: CustomSearchDialogViewModel by viewModels { airTicketsViewModelsFactory }
    private var _binding: FragmentCustomSearchDialogBinding? = null
    private val binding: FragmentCustomSearchDialogBinding
        get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cityName = it.getString(ARG_PARAM_TASK, resources.getString(R.string.moscow))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCustomSearchDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(STYLE_NO_FRAME, R.style.AppBottomSheetDialogTheme)
        return super.onCreateDialog(savedInstanceState)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.post {
            val parent = view.parent as View
            val behavior = BottomSheetBehavior.from(parent)
            val layoutParams = parent.layoutParams
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            parent.layoutParams = layoutParams
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        binding.editWhereFrom.text = cityName

        getListPopularDestination()

        binding.iconDifficultRoute.setOnClickListener {
            findNavController().navigate(R.id.action_airTicketsFragment_to_plugFragment)
        }

        binding.iconWeekends.setOnClickListener {
            findNavController().navigate(R.id.action_airTicketsFragment_to_plugFragment)
        }

        binding.iconHotTickets.setOnClickListener {
            findNavController().navigate(R.id.action_airTicketsFragment_to_plugFragment)
        }

        binding.iconAnywhere.setOnClickListener {
            binding.editWhere.text = binding.textAnywhere.text
        }

        binding.iconClear.setOnClickListener {
            binding.editWhere.text = ""
        }
    }

    private fun getListPopularDestination() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.listPopularDestination.collect {
                popularDestinationAdapter =
                    PopularDestinationAdapter(it, click = { name -> callback(name) })
                binding.recyclerForListPopularDestination.adapter = popularDestinationAdapter
            }
        }
    }

    private fun callback(name: String) {
        binding.editWhere.text = name

        val way = Way(cityWhereFrom = cityName, cityEditWhere = name)
        val bundle = Bundle().apply {
            putParcelable(SearchCountrySelectedFragment.ARG_PARAM, way)
        }
        findNavController().navigate(
            R.id.action_airTicketsFragment_to_searchCountrySelectedFragment,
            bundle
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val TAG = "ModalBottomSheet"
        const val ARG_PARAM_TASK = "ARG_PARAM_TASK"
    }
}
