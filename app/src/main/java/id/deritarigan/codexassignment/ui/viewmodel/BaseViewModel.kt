package id.deritarigan.codexassignment.ui.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.gildor.coroutines.retrofit.Result

open class BaseViewModel : ViewModel(), LifecycleObserver {

    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val isDataBaseEmpty = MutableLiveData<Boolean>()

    fun <T> T?.ErrorResponse() {
        when (this) {
            is Result.Error -> {
                isLoading.postValue(false)
                errorMessage.postValue(exception.message())
            }
            is Result.Exception -> {
                isLoading.postValue(false)
                errorMessage.postValue(exception.message)
            }
        }
    }
}