package maximmakarov.anylist.list

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import maximmakarov.anylist.item.Item

/**
 * @author Maxim Makarov
 * @since 05.05.2017
 */
@StateStrategyType(value = AddToEndSingleStrategy::class)
interface ListView : MvpView {
    fun onItemsLoaded(items: List<Item>)
}