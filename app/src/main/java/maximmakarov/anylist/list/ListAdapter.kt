package maximmakarov.anylist.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_item.view.*
import maximmakarov.anylist.R
import maximmakarov.anylist.item.Item

/**
 * @author Maxim Makarov
 * @since 09.05.2017
 */
class ListAdapter(var callback: IListAdapterCallback) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<Item> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false))

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as ItemViewHolder
        val item = items[position]

        holder.itemView.checkbox.isChecked = item.isChecked
        holder.itemView.checkbox.setOnCheckedChangeListener { _, isChecked ->
            item.isChecked = isChecked
            callback.updateItem(item)
        }

        holder.itemView.layout_naming.setOnClickListener { callback.openSubList(item) }
        holder.itemView.text_name.text = item.name
        holder.itemView.text_description.text = item.description
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: MutableList<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun addItem(item: Item) {
        items.add(item)
        notifyItemInserted(itemCount - 1)
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view)
}