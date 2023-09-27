package com.abhinavdev.supergallery.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.abhinavdev.supergallery.models.ImageModel
import com.abhinavdev.supergallery.ui.fragments.OpenImageFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity, var imageList: List<ImageModel>,val isHidden: Boolean) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun getItemId(position: Int): Long {
        return imageList[position].id
    }

    override fun createFragment(position: Int): Fragment {
        return if (isHidden) {
            OpenImageFragment.newInstanceForHiddenFolder(imageList[position])
        } else {
            OpenImageFragment.newNormalInstance(imageList[position])
        }
    }
}
