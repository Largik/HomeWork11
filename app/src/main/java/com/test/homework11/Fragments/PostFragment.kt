package com.test.homework11.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.homework11.Adapters.PostAdapter
import com.test.homework11.Database.getDatabase
import com.test.homework11.Models.Post
import com.test.homework11.R
import com.test.homework11.databinding.PostListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PostFragment : Fragment(), CoroutineScope {
    private lateinit var binding: PostListBinding
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var recycler: RecyclerView

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PostListBinding.inflate(inflater, container, false)
        activity?.title = "Должности"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireNotNull(this.activity)

        val database = getDatabase(activity.application)

        launch {
            val postList = database.postDao.getPosts().map {
                it.toPost()
            }
            requireActivity().runOnUiThread {
                setList(postList)
                val progressBar: ProgressBar = binding.postProgressBar
                progressBar.visibility = View.GONE
            }
        }

        binding.goToEmployees.setOnClickListener {
            val action =
                PostFragmentDirections.actionListPostToListEmployee()
            view.findNavController().navigate(action)
        }
    }

    private fun setList(posts: List<Post>) {
        val adapter = PostAdapter(posts as MutableList<Post>)
        recycler = binding.postList
        recycler.adapter = adapter
        layoutManager = LinearLayoutManager(requireContext())
        recycler.layoutManager = layoutManager
    }
}