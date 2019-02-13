package com.hmomeni.testableapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.hmomeni.testableapp.R
import com.hmomeni.testableapp.entities.User
import com.hmomeni.testableapp.utils.*
import com.hmomeni.testableapp.vms.ListViewModel
import com.pixplicity.easyprefs.library.Prefs
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.rcl_item_user.view.*
import timber.log.Timber

class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel

    private lateinit var mAdapter: UserRclAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Prefs.getString("user", "").isBlank()) {
            findNavController().navigate(
                ListFragmentDirections.actionListFragmentToLoginFragment(),
                NavOptions.Builder().setPopUpTo(R.id.listFragment, true).build()
            )
            return
        }

        viewModel = ViewModelProviders.of(this)[ListViewModel::class.java].apply {
            inject(context!!.app())
        }

        mAdapter = UserRclAdapter(viewModel.users)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    private var disposable: Disposable? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        disposable = viewModel.fetchUsers()
            .iomain()
            .doOnSubscribe { progressBar.visible() }
            .doAfterSuccess { progressBar.gone() }
            .subscribe({
                mAdapter.notifyDataSetChanged()
            }, {
                toast("Failed loading users")
                Timber.e(it)
            })
    }

    override fun onDestroyView() {
        disposable?.dispose()
        super.onDestroyView()
    }

    class UserRclAdapter(private val users: List<User>) : RecyclerView.Adapter<UserHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
            return UserHolder(LayoutInflater.from(parent.context).inflate(R.layout.rcl_item_user, parent, false))
        }

        override fun getItemCount() = users.size

        override fun onBindViewHolder(holder: UserHolder, position: Int) {
            holder.bind(users[position])
        }

    }

    class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.textView.text = user.name
        }
    }
}