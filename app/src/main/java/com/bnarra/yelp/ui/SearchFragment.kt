package com.bnarra.yelp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.bnarra.yelp.R
import com.bnarra.yelp.databinding.FragmentSearchBinding
import com.bnarra.yelp.remote.ListingsRemoteImpl
import com.bnarra.yelp.remote.retrofit.RetorfitProvider
import com.bnarra.yelp.repo.ListingsDataStoreFactory
import com.bnarra.yelp.repo.ListingsRepositoryImpl
import com.bnarra.yelp.viewmodel.SearchViewModel
import com.bnarra.yelp.viewmodel.SearchViewModelFactory

//TODO Bhavya: add more content to this Fragment's layout
class SearchFragment: Fragment(), SearchViewModel.Callback {

    lateinit var binding: FragmentSearchBinding
    lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)
        viewModel = ViewModelProvider(requireActivity(),
            SearchViewModelFactory(ListingsRepositoryImpl(ListingsDataStoreFactory(
                ListingsRemoteImpl(RetorfitProvider()))))).get(SearchViewModel::class.java)

        binding.listener = this

        return binding.root
    }

    override fun searchClicked() {
        val location = binding.location.editText?.text
        val searchTerm = binding.businessName.editText?.text
        if(location.isNullOrEmpty())  {
           binding.location.error = requireContext().getString(R.string.location_field_error)
           return
        }

        if(searchTerm.isNullOrEmpty()) {
            binding.businessName.error = requireContext().getString(R.string.category_field_error)
            return
        }

        viewModel.setLocation(location.toString())
        viewModel.setSearchTerm(binding.businessName.editText?.text.toString())
        Navigation.findNavController(binding.searchButton).navigate(R.id.search_to_listings)
    }
}