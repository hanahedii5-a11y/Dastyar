package ir.dastyar.assistant

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

class Speaker(context: Context) {

    private val tts: TextToSpeech

    init {
        tts = TextToSpeech(context.applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts.setLanguage(Locale("fa", "IR"))

                if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    tts.language = Locale("fa")
                }
            }
        }
    }

    fun say(text: String) {
        tts.speak(
            text,
            TextToSpeech.QUEUE_FLUSH,
            null,
            "dastyar"
        )
    }

    fun destroy() {
        tts.stop()
        tts.shutdown()
    }
}