package com.miguel4meiro.wonkasemployees.classes.modules.common

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class ListScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val visibleThreshold: Int = 5
): RecyclerView.OnScrollListener() {

    private var isLoading = false
    private var previousItems = 0
    private var currentPage = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItems = layoutManager.childCount
        val totalItems = layoutManager.itemCount
        val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (isLoading && totalItems > previousItems) {
            isLoading = false
            previousItems = totalItems
        } else if (!isLoading && totalItems - visibleItems <= firstVisibleItem + visibleThreshold) {
            onLoadMore()
            isLoading = true
        }
    }

    fun resetState() {
        currentPage = 1
        isLoading = false
    }

    abstract fun onLoadMore()
}