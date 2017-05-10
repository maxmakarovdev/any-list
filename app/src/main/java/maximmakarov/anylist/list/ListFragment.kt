package maximmakarov.anylist.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import maximmakarov.anylist.R
import maximmakarov.anylist.core.utils.FragmentUtils
import maximmakarov.anylist.item.Item

class ListFragment : MvpFragment(), IListView, IListAdapterCallback {

    @InjectPresenter
    lateinit var presenter: ListPresenter

    val adapter: ListAdapter = ListAdapter(this)

    companion object {
        val BUNDLE_PARENT_ITEM = "BUNDLE_PARENT_ITEM"

        fun newInstance(item: Item? = null): ListFragment {
            val fragment = ListFragment()
            val bundle = Bundle()
            bundle.putParcelable(BUNDLE_PARENT_ITEM, item)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.loadData(arguments.getParcelable(BUNDLE_PARENT_ITEM))
    }

    override fun updateItem(item: Item) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openSubList(item: Item) {
        FragmentUtils.replace(fragmentManager, ListFragment.newInstance(item))
    }

    override fun onItemsLoaded(items: MutableList<Item>) {
        adapter.setItems(items)
    }
}
