package maximmakarov.anylist.list

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import maximmakarov.anylist.core.di.Injector
import maximmakarov.anylist.item.Item
import maximmakarov.anylist.storage.DataProvider
import javax.inject.Inject

/**
 * @author Maxim Makarov
 * @since 05.05.2017
 */
@InjectViewState
class ListPresenter: MvpPresenter<IListView>() {
    @Inject
    lateinit var dataProvider: DataProvider

    init {
        Injector.appComponent.inject(this)
    }

    fun loadData(item: Item?){
        viewState.onItemsLoaded(dataProvider.loadChilds(item).toMutableList())
    }
}