package maximmakarov.anylist.core.utils

import android.widget.EditText
import android.widget.TextView

/**
 * @author Maxim Makarov
 * @since 13.06.2017
 */
object TextUtils {
    fun isEmpty(textView: TextView?): Boolean {
        return textView == null || textView.text == null || textView.text.toString().isEmpty()
    }

    fun isEmpty(editText: EditText?): Boolean {
        return editText == null || editText.text == null || editText.text.toString().isEmpty()
    }

    fun isEmpty(string: String?): Boolean {
        return string == null || string.isEmpty()
    }
}
