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
import androidx.navigation.fragment.findNavController
import com.example.pixabayapi.core.isOnline
import com.example.pixabayapi.databinding.FragmentListPictureBinding
import com.example.pixabayapi.domain.model.hit.HitPictureState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PictureListFragment : Fragment() {

    private lateinit var binding: FragmentListPictureBinding
    private lateinit var adapter: PictureAdapter
    private val listViewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListPictureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listViewModel.getPictureHits(
            isOnline = requireContext().isOnline(),
            searchQuery = binding.etSearchInputPicture.text.toString()
        )
        initObserver()
        initAdapter()
        initTextChangeListener()
    }

    private fun initAdapter() {
        adapter = PictureAdapter {
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(hit = it)
            findNavController().navigate(directions = action)
        }
        binding.rvPictureList.adapter = adapter
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            listViewModel.hitPictureState.collectLatest {
                when (it) {
                    HitPictureState.Loading -> binding.pbLoading.isVisible = true
                    is HitPictureState.SuccessLoadingImages -> {
                        adapter.submitList(it.hits)
                        binding.pbLoading.isVisible = false
                    }
                    is HitPictureState.Failure ->
                        Toast.makeText(requireContext(), it.throwable.message, Toast.LENGTH_SHORT).show()
                    else -> Unit
                }
            }
        }
    }

    private fun initTextChangeListener() {
        binding.etSearchInputPicture.doOnTextChanged { text, _, _, _ ->
            listViewModel.getPictureHits(requireContext().isOnline(), text.toString())
        }
    }

}