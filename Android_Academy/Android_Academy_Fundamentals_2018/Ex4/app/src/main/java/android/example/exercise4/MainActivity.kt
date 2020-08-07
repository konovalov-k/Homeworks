package android.example.exercise4

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    //Your task is to synchronize threads,
    // so that output is always «Left» before «Right» (i.e. Left-Right-Left-Right)

    //ToDO: 1)Kotlin way
    // https://developer.android.com/kotlin/coroutines
    //ToDO: 1.1) Define Job
    var viewModelJob = Job()

    //ToDO: 1.3) Define CoroutineScope (Thread + Job)
    //To specify where the coroutines should run,
    //Kotlin provides three dispatchers that you can use:
    //Dispatchers.Main - Use this dispatcher to run a coroutine on the main Android thread.
    //This should be used only for interacting with the UI and performing quick work.
    //Examples include calling suspend functions, running Android UI framework operations, and updating LiveData objects.
    //Dispatchers.IO - This dispatcher is optimized to perform disk or network I/O outside of the main thread.
    //Examples include using the Room component, reading from or writing to files, and running any network operations.
    //Dispatchers.Default - This dispatcher is optimized to perform CPU-intensive work outside of the main thread.
    //Example use cases include sorting a list and parsing JSON.

    val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    //ToDO: 1.4) Define func
    //You must add with which context function must run.
    // Or it will launch by default setting of parent CoroutineScope.
    var countMeBaby:Int = 0
    suspend fun run() = withContext(Dispatchers.IO) {
        println("Right step")
        countMeBaby++
    }
    suspend fun run2() = withContext(Dispatchers.Default) {
        println("Left step")
        countMeBaby++
    }
    suspend fun run3() = withContext(Dispatchers.Main) {
        println("Front step")
        countMeBaby++
    }

    fun kotlinWayToThreads(){
        //ToDO: Action to launch Job (and coroutines inside)
        uiScope.launch {
                //If Job is Active do
                while(isActive){
                    run()
                    run2()
                    run3()
                    if (countMeBaby>1000) cancel()
                }
        }
    }

    override fun onStart() {
        super.onStart()
        //TODO: Uncomment which way do you prefer
        //kotlinWayToThreads()
        initThreads()
    }

    private fun initThreads(){
        Do.isThreadRunning=1
        Thread(ThreadLeftLeg()).start()
        Thread(ThreadRightLeg()).start()
    }
    //ToDo: 2) Threads
    //ToDo: 2.1)Create Class for print msg

    private class Do{
        companion object{
            var isThreadRunning:Int = 0
            //Important thin is synchronized
            //isThreadRunning running like semaphore
            //Syncronized allow to
            @Synchronized fun writeMe(str:String,int:Int){
                //println(str)
                this.isThreadRunning = int
            }}
        }

    //ToDO: 2.2) Create a class for each Threads
    private class ThreadLeftLeg : Runnable {
        override fun run() {
            while (Do.isThreadRunning>0) {
                //Переключаем выполнения потока
                if(Do.isThreadRunning==1)Do.writeMe("Left Step",2)
            }
        }
    }

    private class ThreadRightLeg : Runnable {
        override fun run() {
            while (Do.isThreadRunning>0){
                if(Do.isThreadRunning==2)Do.writeMe("Right Step",1)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        Log.e("MainActivity","onStopCalled")
    }
    //ToDO: 1.2) Cancel all coroutines in the Job at the end
    override fun onDestroy() {
        super.onDestroy()
        Log.e("MainActivity","onDestroyCalled")
        viewModelJob.cancel()
        //ToDO: 2.3) Cancel all Threads and links for them
        Do.isThreadRunning=0
        Thread(ThreadLeftLeg()).interrupt()
        Thread(ThreadRightLeg()).interrupt()
    }
}
