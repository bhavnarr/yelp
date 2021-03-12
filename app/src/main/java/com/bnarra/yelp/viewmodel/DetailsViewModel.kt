package com.bnarra.yelp.viewmodel

import androidx.lifecycle.*
import com.bnarra.yelp.remote.DetailsRemoteImpl
import com.bnarra.yelp.remote.model.BusinessModel
import com.bnarra.yelp.remote.retrofit.RetorfitProvider
import com.bnarra.yelp.repo.DetailsDataStoreFactory
import com.bnarra.yelp.repo.DetailsRepository
import com.bnarra.yelp.repo.DetailsRepositoryImpl
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DetailsViewModel(val repo: DetailsRepository): ViewModel() {
//    private val repo = DetailsRepositoryImpl(DetailsDataStoreFactory( DetailsRemoteImpl(RetorfitProvider())))
    private val _details: MutableLiveData<BusinessModel> = MutableLiveData()
    val details: LiveData<BusinessModel> = _details

    fun fetchDetails(id: String) {
        viewModelScope.launch {
            val detailsAsync = async { repo.details(id) }

            _details.value = detailsAsync.await()
        }
    }
}
class DetailsViewModelFactory(private val repo: DetailsRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(repo) as T
    }

}