package com.example.pixabayapi.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.pixabayapi.R
import com.example.pixabayapi.core.setPictureDataFromHit
import com.example.pixabayapi.core.setPictureDataFromHitRounded
import com.example.pixabayapi.databinding.FragmentDetailBinding
import com.example.pixabayapi.domain.model.hit.Hit
import com.example.pixabayapi.domain.model.hit.HitPictureState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()
    private val hitArgs: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel.setHitFromArgument(hit = hitArgs.hit)
        initObserver()
    }

    private fun initObserver() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            detailViewModel.hitPictureState.collectLatest {
                val hit = (it as HitPictureState.SuccessLoadingSingleImage).hit
                initUi(hit = hit)
            }
        }
    }

    private fun initUi(hit: Hit) {
        binding.apply {

            rowViews.tvRowLabel.text = getString(R.string.detail_label_views)
            rowDownloads.tvRowLabel.text = getString(R.string.detail_label_downloads)
            rowLikes.tvRowLabel.text = getString(R.string.detail_label_likes)
            rowComments.tvRowLabel.text = getString(R.string.detail_label_comments)
            rowUserId.tvRowLabel.text = getString(R.string.detail_label_user_id)
            rowUser.tvRowLabel.text = getString(R.string.detail_label_user)
            tvLabelImageUrl.text = getString(R.string.detail_label_user_image_url)

            rowViews.tvRowValue.text = hit.views.toString()
            rowDownloads.tvRowValue.text = hit.downloads.toString()
            rowLikes.tvRowValue.text = hit.likes.toString()
            rowComments.tvRowValue.text = hit.comments.toString()
            rowUserId.tvRowValue.text = hit.userId.toString()
            rowUser.tvRowValue.text = hit.user

            hit.largeImageURL?.let {
                ivHitDetailPicture.setPictureDataFromHit(context = requireContext(), imageUrl = it)
            }

            hit.userImageURL?.let {
                ivDetailUserPicture.setPictureDataFromHitRounded(context = requireContext(), imageUrl = it)
            }
        }
    }
}