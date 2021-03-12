package com.bnarra.yelp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bnarra.yelp.databinding.FragmentDetailsBinding
import com.bnarra.yelp.remote.DetailsRemoteImpl
import com.bnarra.yelp.remote.ListingsRemoteImpl
import com.bnarra.yelp.remote.retrofit.RetorfitProvider
import com.bnarra.yelp.repo.DetailsDataStoreFactory
import com.bnarra.yelp.repo.DetailsRepositoryImpl
import com.bnarra.yelp.repo.ListingsDataStoreFactory
import com.bnarra.yelp.repo.ListingsRepositoryImpl
import com.bnarra.yelp.viewmodel.DetailsViewModel
import com.bnarra.yelp.viewmodel.DetailsViewModelFactory
import com.bnarra.yelp.viewmodel.SearchViewModel
import com.bnarra.yelp.viewmodel.SearchViewModelFactory
import com.bumptech.glide.Glide

//TODO Bhavya: add more content to this Fragment's view
class DetailsFragment: Fragment() {

    lateinit var binding: FragmentDetailsBinding

    lateinit var viewModel: SearchViewModel
    lateinit var detailsViewModel: DetailsViewModel

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

        detailsViewModel = ViewModelProvider(this,
            DetailsViewModelFactory(DetailsRepositoryImpl(
                DetailsDataStoreFactory(DetailsRemoteImpl(RetorfitProvider())))))
            .get(DetailsViewModel::class.java)

        viewModel.selectedBusinessModel.value?.id?.let { detailsViewModel.fetchDetails(it) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailsBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsViewModel.details.observe(viewLifecycleOwner, Observer {
            Glide.with(requireContext())
                    .load(it.imgUrl)
                    .centerCrop()
                    .into(binding.businessImage)
        })
    }
}