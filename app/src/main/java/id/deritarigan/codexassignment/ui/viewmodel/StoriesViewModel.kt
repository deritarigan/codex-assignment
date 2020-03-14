package id.deritarigan.codexassignment.ui.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.viewModelScope
import id.deritarigan.codexassignment.model.StoryData
import id.deritarigan.codexassignment.model.StoryResponse
import id.deritarigan.codexassignment.model.api.IApi
import kotlinx.coroutines.launch
import ru.gildor.coroutines.retrofit.Result
import ru.gildor.coroutines.retrofit.awaitResult

class StoriesViewModel(val apiService: IApi) : BaseViewModel() {

    var topStoriesId = MutableLiveData<MutableList<Int>>()
    var storyData = MutableLiveData<StoryResponse>()
    var totalData = MutableLiveData(0)
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onGetTotalTopStories() {
        viewModelScope.launch {
            when (val result = apiService.getTopStories().awaitResult()) {
                is Result.Ok -> {
                    isLoading.postValue(false)
                    topStoriesId.value = result.value
                }
                else -> result.ErrorResponse()
            }
        }
    }

    @Synchronized
    fun onGetDataStories( id: Int) {
        viewModelScope.launch {
            when (val result = apiService.getStory(id).awaitResult()) {
                is Result.Ok -> {
                    totalData.value = totalData.value?.plus(1)
                    storyData.postValue(result.value)
                }
                else -> {
                    totalData.value = totalData.value?.plus(1)
                    result.ErrorResponse()
                }
            }
        }
    }
}