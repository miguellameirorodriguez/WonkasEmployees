package com.miguel4meiro.wonkasemployees.classes.modules.list.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miguel4meiro.wonkasemployees.R
import com.miguel4meiro.wonkasemployees.classes.models.app.GenderEnum
import com.miguel4meiro.wonkasemployees.classes.models.app.SortBy
import com.miguel4meiro.wonkasemployees.classes.models.app.Loompa
import com.miguel4meiro.wonkasemployees.classes.models.app.ProfessionEnum
import com.miguel4meiro.wonkasemployees.classes.modules.common.ListScrollListener
import com.miguel4meiro.wonkasemployees.classes.modules.detail.view.DetailFragment
import com.miguel4meiro.wonkasemployees.classes.modules.list.viewmodel.ListViewModelInterface
import com.miguel4meiro.wonkasemployees.databinding.ListFragmentBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment: Fragment(), ListRecyclerViewAdapter.ListItemClickInterface, FilterBottomSheet.FilterListenerInterface {
    private val viewModel: ListViewModelInterface by viewModel()
    private lateinit var binding: ListFragmentBinding

    private var loompaAdapter: ListRecyclerViewAdapter? = null
    private var recyclerView: RecyclerView? = null
    lateinit var scrollListener: ListScrollListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListFragmentBinding.inflate(layoutInflater)

        recyclerView = binding.employeesRv
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView?.layoutManager = layoutManager
        loompaAdapter = ListRecyclerViewAdapter(requireContext(), this)
        recyclerView?.adapter = loompaAdapter

        scrollListener = object : ListScrollListener(layoutManager) {
            override fun onLoadMore() {
                viewModel.loadNextPage()
            }
        }
        recyclerView?.addOnScrollListener(scrollListener)
        viewModel.getLoompas()

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBtnFilter()

        viewModel.viewModelScope.launch {
            viewModel.uiState.collect { uiState ->
                loadLoompas(uiState.loompasList)
                chargeCurrentFilters(uiState.sortBy)
            }
        }
    }

    private fun setupBtnFilter() {
        binding.filterBtn.setOnClickListener {
            val bottomSheetDialog = FilterBottomSheet(this)
            bottomSheetDialog.choosenGender = viewModel.uiState.value.genderFilter
            bottomSheetDialog.choosenProfession = viewModel.uiState.value.professionFilter

            bottomSheetDialog.show(requireActivity().supportFragmentManager, "FilterBottomSheet")
        }
    }

    private fun loadLoompas(loompas: List<Loompa>) {
        if (loompas.isEmpty()) return
        if (loompas.size < 10) viewModel.loadNextPage()
        loompaAdapter?.addLoompas(loompas)
    }

    private fun chargeCurrentFilters(sortBy: SortBy) {
        with(binding){
            when(sortBy) {
                SortBy.NONE -> {
                    allBtn.visibility = View.VISIBLE
                    genderBtn.visibility = View.GONE
                    professionBtn.visibility = View.GONE
                }
                SortBy.GENDER -> {
                    genderBtn.visibility = View.VISIBLE
                    allBtn.visibility = View.GONE
                }
                SortBy.PROFESSION -> {
                    professionBtn.visibility = View.VISIBLE
                    allBtn.visibility = View.GONE
                }
                SortBy.BOTH -> {
                    allBtn.visibility = View.GONE
                    genderBtn.visibility = View.VISIBLE
                    professionBtn.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onLoompaClick(id: Int) {
        binding.filterBtn.visibility = View.GONE

        val detailFragment = DetailFragment.newInstance(id)
        val fragmentTransaction = childFragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.detail_layout, detailFragment)
        fragmentTransaction.commit()
        fragmentTransaction.addToBackStack(null)
    }

    override fun applyFilter(genderFilter: GenderEnum?, professionFilter: ProfessionEnum?) {
        loompaAdapter?.clearItems()
        scrollListener.resetState()

        viewModel.applyFilter(genderFilter, professionFilter)
    }

    override fun resetFilter() {
        loompaAdapter?.clearItems()
        scrollListener.resetState()

        viewModel.resetFilters()
    }
}