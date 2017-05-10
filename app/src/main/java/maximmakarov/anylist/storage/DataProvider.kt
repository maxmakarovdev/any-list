package maximmakarov.anylist.storage

import android.content.Context
import io.requery.Persistable
import io.requery.android.sqlite.DatabaseSource
import io.requery.kotlin.eq
import io.requery.query.Result
import io.requery.reactivex.KotlinReactiveEntityStore
import io.requery.sql.KotlinEntityDataStore
import io.requery.sql.TableCreationMode
import maximmakarov.anylist.item.Item
import maximmakarov.anylist.item.Models
import java.util.*

/**
 * @author Maxim Makarov
 * @since 05.05.2017
 */
class DataProvider {
    val DB_VERSION: Int = 1
    val ABSENT_PARENT_ID = 0

    val data: KotlinReactiveEntityStore<Persistable>

    constructor(context: Context) {
        val source = DatabaseSource(context, Models.DEFAULT, DB_VERSION)
        source.setTableCreationMode(TableCreationMode.DROP_CREATE)
        data = KotlinReactiveEntityStore<Persistable>(KotlinEntityDataStore(source.configuration))
    }

    fun loadAllItems(): Result<Item> {
        return (data select (Item::class)).get()
    }

    fun loadChilds(item: Item?): Result<Item> {
        return (data select (Item::class) where (Item::parentId eq (item?.id ?: 0))).get()
    }

    fun getPathToItem(i: Item) {
        //todo try to optimize without loading all items
        val items = loadAllItems()
        val list = ArrayList<Item>()
        var item = i
        list.add(item)
        while (item.parentId != ABSENT_PARENT_ID) {
            val parent = items.first { item.parentId == it.id }
            list.add(parent)
            item = parent
        }
        list.reversed()
    }

    fun saveItem(item: Item) {
        data insert item
    }

    fun updateItem(item: Item) {
        data update item
    }

    fun deleteItem(item: Item) {
        data delete item
    }

    /** remove item with all sub-hierarchy */
    fun removeItemSafe(item: Item) {
        getAllSubItems(item).forEach { deleteItem(it) }
        deleteItem(item)
    }

    fun getAllSubItems(item: Item): List<Item> {
        val subItems = ArrayList<Item>()
        val childs = loadChilds(item)

        subItems.addAll(childs)
        for (child in childs) {
            subItems.addAll(getAllSubItems(child))
        }
        return subItems
    }
}