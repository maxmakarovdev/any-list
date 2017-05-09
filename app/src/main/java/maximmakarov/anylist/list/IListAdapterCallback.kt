package maximmakarov.anylist.list

import maximmakarov.anylist.item.Item

/**
 * @author Maxim Makarov
 * @since 09.05.2017
 */
interface IListAdapterCallback {
    fun updateItem(item: Item)
    fun openSubList(item: Item)
}