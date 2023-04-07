package com.example.pixabayapi.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pixabayapi.R
import com.example.pixabayapi.databinding.FragmentListBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        binding.vpLists.adapter =
            FragmentPagerAdapter(fragment = this, fragments = arrayOf(PictureListFragment(), VideoListFragment()))
        TabLayoutMediator(binding.tlListTabs, binding.vpLists) { tab, position ->
            tab.text = if (position == 0) getString(R.string.viewpager_first_tab) else getString(R.string.viewpager_second_tab)
        }.attach()
    }

}