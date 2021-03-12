package com.bnarra.yelp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bnarra.yelp.R
import com.bnarra.yelp.databinding.FragmentListingsBinding
import com.bnarra.yelp.remote.ListingsRemoteImpl
import com.bnarra.yelp.remote.retrofit.RetorfitProvider
import com.bnarra.yelp.repo.ListingsDataStoreFactory
import com.bnarra.yelp.repo.ListingsRepositoryImpl
import com.bnarra.yelp.ui.adapter.ListingsAdapter
import com.bnarra.yelp.viewmodel.SearchViewModel
import com.bnarra.yelp.viewmodel.SearchViewModelFactory

/*TODO Bhavya:
1. sort implementation
2. group by category
*/

class ListingsFragment: Fragment() {

    private lateinit var binding: FragmentListingsBinding
    private lateinit var adapter: ListingsAdapter
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(),
            SearchViewModelFactory(
                ListingsRepositoryImpl(
                    ListingsDataStoreFactory(
                ListingsRemoteImpl(RetorfitProvider())
                    )
                )
            )
        ).get(SearchViewModel::class.java)

        val location = viewModel.location()
        location?.let {
            //FIXME Bhavya: loading state - show progress while results are being fetched
            viewModel.searchBusinesses(viewModel.searchTerm(), it )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListingsBinding.inflate(inflater)
        adapter= ListingsAdapter(requireContext())
        binding.gridview.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gridview.onItemClickListener =
            AdapterView.OnItemClickListener { _, itemView, position, _ ->
                if(position != ListingsAdapter.INVALID_INDEX) {
                    adapter.getItem(position)?.let { viewModel.selectedItem(it) }
                    Navigation.findNavController(itemView).navigate(R.id.listings_to_details)
                }
            }

        viewModel.businessModel.observe(viewLifecycleOwner, Observer { responseModel ->
            //FIXME Bhavya: error/ empty results state
            responseModel?.businesses?.let {
                adapter.addItems(it.take(20))
                adapter.notifyDataSetChanged()
            }
        })
    }
}