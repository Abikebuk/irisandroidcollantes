package com.example.androidiris

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidiris.auth.Authenticate
import com.example.androidiris.database.PostHandler
import com.example.androidiris.databinding.FragmentNewsBinding
import com.example.androidiris.databinding.FragmentPostBinding
import com.google.firebase.firestore.local.LruGarbageCollector.Params.Default
import com.squareup.okhttp.Dispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [newsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var currentUser = Authenticate.client.getCurrentUser()?.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        currentUser?.let {
            if(param1 == "a"){
                GlobalScope.async {
                    withContext(Dispatchers.Default) {
                        val documents = PostHandler.getAllFromUserAndFriend(it)
                        for(doc in documents){
                            Log.d("zeojizeiofj", doc.toString())
                        }
                        val tr = childFragmentManager.beginTransaction()
                        for (doc in documents) {
                            val post = PostFragment.newInstance(doc)
                            tr.add(R.id.testWrapper, post)
                        }
                        tr.commitAllowingStateLoss()
                    }

                }
            }
            else {
                PostHandler.getAllFromUser(it)
                    .addOnSuccessListener { querySnapshot ->
                        val documents = PostHandler.querySnapshotToPosts(querySnapshot)
                        val tr = childFragmentManager.beginTransaction()
                        for(doc in documents){
                            val post = PostFragment.newInstance(doc)
                            tr.add(R.id.testWrapper,post)
                        }
                        tr.commitAllowingStateLoss()
                    }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment newsFragment.
         */
        // TODO: Rename and change types abrand number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}