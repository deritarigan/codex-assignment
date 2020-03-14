package id.deritarigan.codexassignment.ui.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import id.deritarigan.codexassignment.R

class MainActivity : AppCompatActivity() {
    val bundlePublisher = MutableLiveData<Bundle>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onFragmentResult(resultCode: Int, data: Bundle) {
        if(resultCode == Activity.RESULT_OK)
       bundlePublisher.postValue(data)
    }
}
