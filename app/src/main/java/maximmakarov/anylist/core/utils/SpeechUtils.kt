package maximmakarov.anylist.core.utils

import android.app.Fragment
import android.content.ActivityNotFoundException
import android.content.Intent
import android.speech.RecognizerIntent
import android.widget.Toast
import maximmakarov.anylist.R
import java.util.*

/**
 * @author Maxim Makarov
 * @since 13.06.2017
 */
object SpeechUtils {
    fun startSpeechActivity(fr: Fragment, requestCode: Int) {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, fr.getString(R.string.speech_say))
        try {
            fr.startActivityForResult(intent, requestCode)
        } catch (a: ActivityNotFoundException) {
            Toast.makeText(fr.activity, fr.getString(R.string.speech_not_supported), Toast.LENGTH_SHORT).show()
        }
    }
}
