package com.alirnp.babalhavaej.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.alirnp.babalhavaej.R
import com.alirnp.babalhavaej.databinding.FragmentHomeBinding
import com.alirnp.babalhavaej.model.HomeItem
import com.alirnp.babalhavaej.ui.adapter.HomeItemsAdapter
import com.alirnp.babalhavaej.viewModel.HomeViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val mViewModel = HomeViewModel()
    private val adapter = HomeItemsAdapter()

    private var layoutManager = GridLayoutManager(context, 3)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment HomeFragment.
         */
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayoutManager()
        initRecyclerView()
        observeHomeItems()
    }

    private fun initRecyclerView() {
        adapter.listener = object : HomeItemsAdapter.OnItemClickListener{
            override fun onItemClick(item: HomeItem) {
                Toast.makeText(context, "{${item.name}}", Toast.LENGTH_SHORT).show()
            }

        }
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }

    private fun initLayoutManager() {
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val lastItemPosition = adapter.itemCount - 1
                return when (position) {
                    lastItemPosition -> 3
                    else -> 1
                }
            }
        }

    }


    private fun observeHomeItems() {
        mViewModel.mHomeItems.observe(requireActivity(), { items -> adapter.setItems(items) })
        mViewModel.mReportCard.observe(requireActivity(), { reportCard ->
            activity?.let { activity ->
                activity.runOnUiThread { adapter.setReportCard(reportCard) }
            }
        })

    }
}