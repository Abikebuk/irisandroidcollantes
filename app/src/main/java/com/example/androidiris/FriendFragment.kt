package com.example.androidiris

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidiris.database.UserHandler
import com.example.androidiris.databinding.FragmentFriendBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "friendId"

/**
 * A simple [Fragment] subclass.
 * Use the [FriendFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FriendFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var friendId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            friendId = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentFriendBinding = FragmentFriendBinding.inflate(inflater, container, false)
        val view = binding.root

        friendId?.let {
            UserHandler.get(it)
                .addOnSuccessListener { documentSnapshot ->
                    val friend = UserHandler.documentSnapshotToDocument(documentSnapshot)
                    binding.friendName.text = "${friend?.firstname} ${friend?.lastname}"
                }
        }
        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FriendFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(friendId: String) =
            FriendFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, friendId)
                }
            }
    }
}