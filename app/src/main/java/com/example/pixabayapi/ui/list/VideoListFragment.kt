package com.example.pixabayapi.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import com.example.pixabayapi.databinding.FragmentListVideoBinding
import com.example.pixabayapi.domain.model.video.VideoState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class VideoListFragment : Fragment() {

    private lateinit var binding: FragmentListVideoBinding
    private lateinit var adapter: VideoAdapter
    private val listViewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listViewModel.getVideoHits(
            searchQuery = binding.etSearchInputVideo.text.toString()
        )
        initObserver()
        initAdapter()
        initTextChangeListener()
    }

    private fun initAdapter() {
        adapter = VideoAdapter()
        binding.rvVideoList.adapter = adapter
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            listViewModel.hitVideoState.collectLatest {
                when (it) {
                    VideoState.Loading -> binding.pbLoading.isVisible = true
                    is VideoState.SuccessVideo -> {
                        adapter.submitList(it.videos)
                        binding.pbLoading.isVisible = false
                    }
                    is VideoState.Failure ->
                        Toast.makeText(requireContext(), it.throwable.message, Toast.LENGTH_SHORT).show()
                    else -> Unit
                }
            }
        }
    }

    private fun initTextChangeListener() {
        binding.etSearchInputVideo.doOnTextChanged { text, _, _, _ ->
            listViewModel.getVideoHits(text.toString())
        }
    }

}