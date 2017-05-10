package maximmakarov.anylist.list

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import maximmakarov.anylist.R
import maximmakarov.anylist.core.utils.FragmentUtils

/**
 * @author Maxim Makarov
 * @since 05.05.2017
 */
class ListActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_activity)
        FragmentUtils.replace(fragmentManager, ListFragment.newInstance())
    }
}