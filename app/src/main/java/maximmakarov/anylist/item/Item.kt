package maximmakarov.anylist.item

import android.os.Parcelable
import io.requery.*

/**
 * @author Maxim Makarov
 * @since 03.05.2017
 */
@Entity
interface Item : Parcelable, Persistable {
    @get:Key
    @get:Generated
    val id: Int

    var parentId: Int
    var name: String
    var description: String
    var colorValue: Int
    var isChecked: Boolean
    var dateMillis: Long
    var remindMillis: Long
    var sort: Sort
    var sortOrder: SortOrder
    var listPosition: Int

    //non-storable
    var childsCount: Int
}