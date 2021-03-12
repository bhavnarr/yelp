package com.bnarra.yelp.viewmodel

import androidx.lifecycle.*
import com.bnarra.yelp.remote.ListingsRemoteImpl
import com.bnarra.yelp.remote.model.BusinessModel
import com.bnarra.yelp.remote.model.ResponseModel
import com.bnarra.yelp.remote.retrofit.RetorfitProvider
import com.bnarra.yelp.repo.ListingsDataStoreFactory
import com.bnarra.yelp.repo.ListingsRepository
import com.bnarra.yelp.repo.ListingsRepositoryImpl
import kotlinx.coroutines.*

class SearchViewModel(repo: ListingsRepository): ViewModel() {
    //FIXME Bhavya: inject repo into viewModel and use Factory to create ViewModel
    private val _repo: ListingsRepository =
        ListingsRepositoryImpl(
            ListingsDataStoreFactory( ListingsRemoteImpl(RetorfitProvider()))
        )

    private val _location: MutableLiveData<String> = MutableLiveData()
    private val _searchTerm: MutableLiveData<String?> = MutableLiveData()

    private val _businesses: MutableLiveData<ResponseModel> = MutableLiveData()
    val businessModel: LiveData<ResponseModel> = _businesses

    private val _selectedBusiness: MutableLiveData<BusinessModel> = MutableLiveData()
    val selectedBusinessModel: LiveData<BusinessModel> = _selectedBusiness

    fun setLocation(selectedLocation: String) {
        _location.value = selectedLocation
    }

    fun setSearchTerm(search: String?) {
        _searchTerm.value = search
    }

    fun location(): String? = _location.value

    fun searchTerm(): String? = _searchTerm.value

     fun searchBusinesses(searchTerm: String?, location: String){
        viewModelScope.launch {
            val businessesAsync = async { _repo.findBusinesses(searchTerm, location) }
            _businesses.value = businessesAsync.await()
        }
    }

    fun selectedItem(item: BusinessModel) {
        _selectedBusiness.value = item
    }

    interface Callback {
        fun searchClicked()
    }
}

class SearchViewModelFactory(private val repo: ListingsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(repo) as T
    }

}

