package ir.dastyar.assistant

import android.os.Handler
import android.os.Looper

object UiBus {

    private val handler = Handler(Looper.getMainLooper())
    private var listener: ((String) -> Unit)? = null

    fun setListener(block: (String) -> Unit) {
        listener = block
    }

    fun post(message: String) {
        handler.post {
            listener?.invoke(message)
        }
    }
}