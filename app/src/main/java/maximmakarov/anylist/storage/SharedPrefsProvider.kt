package maximmakarov.anylist.storage

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * @author Maxim Makarov
 * @since 05.05.2017
 */
class SharedPrefsProvider {

    private val preferences: SharedPreferences
    private val context: Context

    constructor(context: Context) {
        this.context = context
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
    }


}