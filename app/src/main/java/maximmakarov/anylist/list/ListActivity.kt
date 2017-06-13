package maximmakarov.anylist.list

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import maximmakarov.anylist.R
import maximmakarov.anylist.core.utils.FragmentUtils
import maximmakarov.anylist.item.ItemEntity
import maximmakarov.anylist.storage.DataProvider

/**
 * @author Maxim Makarov
 * @since 05.05.2017
 */
class ListActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_activity)

        val rootItem = ItemEntity()
        rootItem.id = DataProvider.ROOT_PARENT_ID
        rootItem.parentId = DataProvider.ABSENT_PARENT_ID

        FragmentUtils.replace(fragmentManager, ListFragment.newInstance(rootItem))
    }
}