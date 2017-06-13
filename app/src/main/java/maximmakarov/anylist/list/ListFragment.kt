package maximmakarov.anylist.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
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
    fun providePresenter(): ListPresenter {
        return ListPresenter(arguments.getParcelable(BUNDLE_PARENT_ITEM))
    }

    val adapter: ListAdapter = ListAdapter(this)

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
