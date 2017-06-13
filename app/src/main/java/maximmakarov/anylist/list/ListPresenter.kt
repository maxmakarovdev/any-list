package maximmakarov.anylist.list

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import maximmakarov.anylist.core.di.Injector
import maximmakarov.anylist.item.Item
import maximmakarov.anylist.item.ItemEntity
import maximmakarov.anylist.storage.DataProvider
import javax.inject.Inject

/**
 * @author Maxim Makarov
 * @since 05.05.2017
 */
@InjectViewState
class ListPresenter(val parentItem: Item) : MvpPresenter<IListView>() {
    @Inject
    lateinit var dataProvider: DataProvider

    init {
        Injector.appComponent.inject(this)
    }

    fun loadData() {
        viewState.onItemsLoaded(dataProvider.loadChilds(parentItem).toMutableList())
    }

    fun addItem(name: String) {
        val item = ItemEntity()
        item.name = name
        item.parentId = parentItem.id

        dataProvider.saveItem(item)
    }

    fun updateItem(item: Item){
        dataProvider.updateItem(item)
    }
}