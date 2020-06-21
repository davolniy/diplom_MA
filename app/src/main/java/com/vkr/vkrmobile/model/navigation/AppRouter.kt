package com.vkr.vkrmobile.model.navigation

import ru.terrakok.cicerone.Router
import java.util.*

interface ResultListener {
    /**
     * Received result from screen.
     *
     * @param resultData
     */
    fun onResult(resultData: Any?)
}


class AppRouter : Router() {
    private val resultListeners = hashMapOf<Int, Stack<ResultListener>>()

    /**
     * Subscribe to the screen result.<br></br>
     * You must call a **removeResultListener()** to avoid a memory leak.
     *
     * @param resultCode key for filter results
     * @param listener   result listener
     */
    fun setResultListener(resultCode: Int, listener: (Any?) -> Unit) {
        val requestCodeStack = resultListeners[resultCode] ?: Stack()
        requestCodeStack.push(object : ResultListener {
            override fun onResult(resultData: Any?) {
                listener.invoke(resultData)
            }
        })
        resultListeners[resultCode] = requestCodeStack
    }

    /**
     * Unsubscribe from the screen result.
     *
     * @param resultCode key for filter results
     */
    fun removeResultListener(resultCode: Int) {
        val requestCodeStack = resultListeners[resultCode]
        if (requestCodeStack?.empty() == false) requestCodeStack.pop()
    }

    /**
     * Send result data to subscriber.
     *
     * @param resultCode result data key
     * @param result     result data
     * @return TRUE if listener was notified and FALSE otherwise
     */
    fun sendResult(resultCode: Int?, result: Any?): Boolean {
        val requestCodeStack = resultListeners[resultCode]
        if (requestCodeStack != null && !requestCodeStack.empty()) {
            requestCodeStack.peek().onResult(result)
            return true
        }
        return false
    }
}