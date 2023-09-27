package com.abhinavdev.supergallery.ui.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.abhinavdev.supergallery.classes.SortName
import com.abhinavdev.supergallery.classes.ZoomOutPageTransformer
import com.abhinavdev.supergallery.databinding.ActivityImagePagerBinding
import com.abhinavdev.supergallery.models.ImageModel
import com.abhinavdev.supergallery.ui.adapters.ViewPagerAdapter
import com.abhinavdev.supergallery.ui.fragments.OpenImageFragment
import com.abhinavdev.supergallery.utils.AbhinavUtil
import com.abhinavdev.supergallery.utils.AbhinavUtil.formatDate
import com.abhinavdev.supergallery.utils.FileUtil
import com.abhinavdev.supergallery.utils.ImageListUtil
import com.abhinavdev.supergallery.utils.LogUtil
import java.lang.ref.WeakReference

class ImagePagerActivity : AppCompatActivity() {
    companion object {
        const val CURRENT_POSITION_ARG = "current_position"
    }

    private val binding by lazy { ActivityImagePagerBinding.inflate(layoutInflater) }
    private var currentPosition = -1
    private var imageList: List<ImageModel> = emptyList()
    private val intentLauncher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                // Delete request was successful

            }
        }
    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            LogUtil.log(this@ImagePagerActivity, "image ${getCurrentImage()}")
            currentPosition = position
            binding.toolbar.title = getCurrentImage().dateAdded.formatDate()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        currentPosition = intent.getIntExtra(CURRENT_POSITION_ARG, -1)
        imageList = ImageListUtil.imageList
        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = getCurrentImage().dateAdded.formatDate()
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        setupViewPager2()

        binding.shareBtn.setOnClickListener {
            shareImage(getCurrentImage().uri)
        }
        binding.deleteBtn.setOnClickListener {
            deleteImage(getCurrentImage())
        }
        binding.rotateBtn.setOnClickListener {
            rotateImage(getCurrentImage())
        }
    }

    private fun shareImage(uri: Uri?) {
        val intent = AbhinavUtil.createShareSongFileIntent(uri)
        startActivity(Intent.createChooser(intent, "Share Via ..."))
    }

    private fun deleteImage(imageModel: ImageModel) {
        FileUtil.deleteFromDevice(
            WeakReference(this),
            listOf(imageModel),
            intentLauncher,
            OpenImageFragment.isFolderHidden
        )
    }

    private fun rotateImage(imageModel: ImageModel) {

    }

    fun getCurrentImage(): ImageModel {
        return imageList[currentPosition]
    }

    private fun setupViewPager2() {
        binding.imageViewPager.adapter = ViewPagerAdapter(this, imageList, false)
        binding.imageViewPager.registerOnPageChangeCallback(pageChangeCallback)
        binding.imageViewPager.setPageTransformer(ZoomOutPageTransformer())
        binding.imageViewPager.currentItem = currentPosition
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.imageViewPager.unregisterOnPageChangeCallback(pageChangeCallback)
    }

}