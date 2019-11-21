package kz.infinit.pageindicator

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class CustomLiveData<T> : MutableLiveData<T>() {
    companion object{
        private const val TAG = "CustomLiveData"
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        Log.i(TAG, "observe owner state:${owner.lifecycle.currentState}")
        super.observe(owner, observer)
    }

    override fun observeForever(observer: Observer<in T>) {
        Log.i(TAG, "observeForever")
        super.observeForever(observer)
    }

    override fun removeObserver(observer: Observer<in T>) {
        Log.i(TAG, "removeObserver")
        super.removeObserver(observer)
    }

    override fun removeObservers(owner: LifecycleOwner) {
        Log.i(TAG, "removeObservers")
        super.removeObservers(owner)
    }

}