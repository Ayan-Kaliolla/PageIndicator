package kz.infinit.pageindicator

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*
import kz.infinit.pageindicator.lifecycle.LifecycleComponentObserver

class MainActivity : Activity() , LifecycleOwner{
    companion object{
        private const val TAG = "MainActivity"
    }

    private lateinit var lifecycle: LifecycleRegistry
    private val lifecycleComponentObserver = LifecycleComponentObserver()
    private val data = CustomLiveData<String>()

    override fun getLifecycle() = lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle = LifecycleRegistry(this)

        lifecycleComponentObserver.observe(this, {

        })



        data.observe(this, Observer {
            Log.i(TAG, it)
        })
        Thread({Thread.sleep(5000); data.postValue("hello")}).start()

        lifecycle.currentState = Lifecycle.State.CREATED
    }

    override fun onStart() {
        lifecycle.currentState = Lifecycle.State.STARTED
        super.onStart()
    }

    override fun onResume() {
        lifecycle.currentState = Lifecycle.State.RESUMED
        super.onResume()
    }

    override fun onDestroy() {
        lifecycle.currentState = Lifecycle.State.DESTROYED
        super.onDestroy()
    }
}
