package com.example.androidiris

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.example.androidiris.database.UserHandler
import com.example.androidiris.database.UserQuery
import com.example.androidiris.databinding.FragmentFriendListBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FriendListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FriendListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val currentUser =  "Bz7MWLrjTRdkespEGu4ySb2GqQy1"
    private val currentFriendList = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentFriendListBinding = FragmentFriendListBinding.inflate(inflater, container, false)
        binding.friendListSearch.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_SEARCH
                || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                searchUsers(binding.friendListSearch.text.toString());
                true
            }else {
                false
            }
        }
        binding.friendListSearchButton.setOnClickListener {
            searchUsers(binding.friendListSearch.text.toString());
        }

        getFriends()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun getFriends(){
        getFriends(true)
    }

    private fun getFriends(showFriends : Boolean){
        UserHandler.get(currentUser)
            .addOnSuccessListener { documentSnapshot ->
                currentFriendList.clear()
                val user = UserHandler.documentSnapshotToDocument(documentSnapshot);
                if (user != null) {

                    val tr = childFragmentManager.beginTransaction()
                    for ( friendId in user.friends!!){
                        Log.d("ZRGERGK", friendId )
                        if(showFriends){
                            val friendFragment = FriendFragment.newInstance(currentUser, friendId, true)
                            tr.add(R.id.friendListWrapper, friendFragment)
                        }
                        currentFriendList.add(friendId)
                    }
                    if(showFriends){
                        tr.commitAllowingStateLoss()
                    }
                }
            }
    }

    private fun searchUsers (query : String){
        getFriends(false)
        clearFragments()
        if (query != null && query != ""){
            Log.d("ZEFIK",  "looking for ${query}")
            val uq = UserQuery(query)
            var async = GlobalScope.launch {
                var res = uq.get();
                var tr = childFragmentManager.beginTransaction()
                for (user in res){
                    Log.d("EFIOZEFI", user.toString())
                    val friendFragment =
                        user._user.id?.let {
                            Log.d("EFIOZEFI", "it : ${it.toString()} , ${currentFriendList.contains(it)}")

                            FriendFragment.newInstance(currentUser, it, currentFriendList.contains(it))
                        }
                    if (friendFragment != null) {
                        tr.add(R.id.friendListWrapper, friendFragment)
                    }
                }
                tr.commitAllowingStateLoss()
            }
        }else{
            getFriends()
        }
    }

    fun clearFragments(){
        Log.d("EFIOZEFI", "CLEAR")

        val manager = childFragmentManager.findFragmentById(R.id.friendListWrapper)
        childFragmentManager.fragments
        if (manager != null) {
            Log.d("EFIOZEFI",  manager.childFragmentManager.fragments.toString())
            Log.d("EFIOZEFI",          childFragmentManager.fragments.toString())
        }

        if (manager != null) {
            val tr = childFragmentManager.beginTransaction()
            for(f in childFragmentManager.fragments){
                Log.d("EFIOZEFI", "removing : ${f.toString()}")
               tr.remove(f)
            }
            tr.commit()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FriendListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FriendListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}