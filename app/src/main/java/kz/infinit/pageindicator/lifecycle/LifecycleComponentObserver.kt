package kz.infinit.pageindicator.lifecycle

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.*

@SuppressLint("LongLogTag")
class LifecycleComponentObserver : LifecycleObserver {
    private var lifecycle: Lifecycle? = null

    private var isActive = false

    companion object {
        private const val TAG = "LifecycleComponentObserver"
    }

    fun observe(lifecycle: LifecycleOwner, function: () -> Unit) {
        this.lifecycle?.removeObserver(this)
        lifecycle.lifecycle.let {
            it.addObserver(this)
            this.lifecycle = it
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
        Log.i(TAG, "onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(){
        Log.i(TAG, "onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){
        Log.i(TAG, "onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){
        Log.i(TAG, "onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(){
        Log.i(TAG, "onStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(){
        Log.i(TAG, "onDestroy")

    }
}