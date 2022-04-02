
package com.example.androidiris

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidiris.auth.Authenticate
import com.example.androidiris.database.UserHandler
import com.example.androidiris.databinding.FragmentPostBinding
import com.example.androidiris.schemas.Post

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "post"

/**
 * A simple [Fragment] subclass.
 * Use the [PostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var post: Post? = null
    private var currentUser = Authenticate.client.getCurrentUser()?.uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            post = it.getParcelable<Post>(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:FragmentPostBinding = FragmentPostBinding.inflate(inflater,container,false)
        binding.titlePost.text = post?.title ?: ""
        binding.contentPost.text = post?.text ?: ""
        post?.userId?.let { UserHandler.get(it).addOnSuccessListener{documentSnapshot->
            val user = UserHandler.documentSnapshotToDocument(documentSnapshot)
            if (user != null) {
                binding.nameProfile.text = "${user.lastname} ${user.firstname}"
            }
        } }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PostFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(post : Parcelable) =
            PostFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, post)
                }
            }
    }
}