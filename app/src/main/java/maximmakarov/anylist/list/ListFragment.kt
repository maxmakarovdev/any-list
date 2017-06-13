package maximmakarov.anylist.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arellomobile.mvp.presenter.ProvidePresenterTag
import kotlinx.android.synthetic.main.list_fragment.*
import maximmakarov.anylist.R
import maximmakarov.anylist.core.utils.FragmentUtils
import maximmakarov.anylist.core.utils.SpeechUtils
import maximmakarov.anylist.core.utils.TextUtils
import maximmakarov.anylist.item.Item


class ListFragment : MvpFragment(), IListView, IListAdapterCallback {

    val CODE_SPEECH = 100

    @InjectPresenter
    lateinit var presenter: ListPresenter

    @ProvidePresenter
    fun providePresenter(): ListPresenter = ListPresenter(arguments.getParcelable(BUNDLE_PARENT_ITEM))

    @ProvidePresenterTag(presenterClass = ListPresenter::class)
    fun providePresenterTag(): String {
        val item: Item = arguments.getParcelable(BUNDLE_PARENT_ITEM)
        return item.id.toString()
    }

    var adapter: ListAdapter = ListAdapter(this)

    companion object {
        val BUNDLE_PARENT_ITEM = "BUNDLE_PARENT_ITEM"

        fun newInstance(item: Item): ListFragment {
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

        presenter.loadData()

        initRecyclerView()

        btn_voice_input.setOnClickListener {
            SpeechUtils.startSpeechActivity(this, CODE_SPEECH)
        }
        btn_add_item.setOnClickListener {
            if (TextUtils.isEmpty(et_new_item)) {
                et_new_item.error = getString(R.string.error_enter_name)
            } else {
                presenter.addItem(et_new_item.text.toString())
                et_new_item.setText("")
                et_new_item.error = null
            }
        }
    }

    private fun initRecyclerView() {
        list_items.layoutManager = LinearLayoutManager(activity)
        list_items.adapter = adapter

        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false //todo
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                when (swipeDir) { //todo
                    ItemTouchHelper.LEFT -> {

                    }
                    ItemTouchHelper.RIGHT -> {

                    }
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(list_items)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            CODE_SPEECH -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    et_new_item.setText(result[0])
                }
            }
        }
    }

    override fun updateItem(item: Item) {
        presenter.updateItem(item)
    }

    override fun openSubList(item: Item) {
        FragmentUtils.replace(fragmentManager, ListFragment.newInstance(item))
    }

    override fun onItemsLoaded(items: MutableList<Item>) {
        adapter.setItems(items)
    }
}
