package com.example.ctf240521.ui.screens.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ctf240521.data.local.entities.Trading
import com.example.ctf240521.repository.PostRepository
import com.example.ctf240521.util.Event
import com.example.ctf240521.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val repository: PostRepository
): ViewModel(){
    private val _saveStatus = MutableLiveData<Event<Resource<String>>>()
    val saveStatus:LiveData<Event<Resource<String>>> = _saveStatus
    private val _deleteStatus = MutableLiveData<Event<Resource<String>>>()
    val deleteStatus:LiveData<Event<Resource<String>>> = _deleteStatus
    private val _allTradingStatus = MutableLiveData<Resource<List<Trading>>>()
    val allTradingStatus:LiveData<Resource<List<Trading>>> = _allTradingStatus
    private val _tradingStatus = MutableLiveData<Resource<Trading>>()
    val tradingStatus:LiveData<Resource<Trading>> = _tradingStatus
    private val _buyingSearchStatus = MutableLiveData<Resource<List<Trading>>>()
    val buyingSearchStatus:LiveData<Resource<List<Trading>>> = _buyingSearchStatus
    private val _sellingSearchStatus = MutableLiveData<Resource<List<Trading>>>()
    val sellingSearchStatus:LiveData<Resource<List<Trading>>> = _sellingSearchStatus

    fun saveTrading(trading: Trading){
        _saveStatus.postValue(Event(Resource.loading(null)))
        viewModelScope.launch {
            val result=repository.saveTrading(trading)
            _saveStatus.postValue(Event(result))
        }
    }
    fun deleteTrading(trading: Trading){
        _deleteStatus.postValue(Event(Resource.loading(null)))
        viewModelScope.launch {
            val result=repository.deleteTrading(trading)
            _deleteStatus.postValue(Event(result))
        }
    }
    fun getAllTrading(){
        _allTradingStatus.postValue(Resource.loading(null))
        viewModelScope.launch {
            val result=repository.getAllTrading()
            _allTradingStatus.postValue(result)
        }
    }
//    fun getAllUserTrading(username: String){
//        _allUserTrading.postValue(Resource.loading(null))
//        viewModelScope.launch {
//            val result=repository.getAllUserTrading(username)
//            _allUserTrading.postValue(result)
//        }
//    }
    fun getTrading(trading: Trading){
        _tradingStatus.postValue(Resource.loading(null))
        viewModelScope.launch {
            val result=repository.getTrading(trading)
            _tradingStatus.postValue(result)
        }
    }
    fun getBuyingSearch(query: String){
        _buyingSearchStatus.postValue(Resource.loading(null))
            viewModelScope.launch {
                val result=repository.getBuyingSearch(query)
                _buyingSearchStatus.postValue(result)
            }
    }
    fun getSellingSearch(query: String){
        _sellingSearchStatus.postValue(Resource.loading(null))
        viewModelScope.launch {
            val result=repository.getSellingSearch(query)
            _sellingSearchStatus.postValue(result)
        }
    }

}