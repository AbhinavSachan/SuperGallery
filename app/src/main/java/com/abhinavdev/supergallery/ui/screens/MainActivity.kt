package com.abhinavdev.supergallery.ui.screens

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.abhinavdev.supergallery.ApplicationGallery
import com.abhinavdev.supergallery.R
import com.abhinavdev.supergallery.databinding.ActivityMainBinding
import com.abhinavdev.supergallery.interfaces.ImageLoaderCallback
import com.abhinavdev.supergallery.models.ImageModel
import com.abhinavdev.supergallery.ui.adapters.ImageAdapter
import com.abhinavdev.supergallery.utils.ImageListUtil
import com.abhinavdev.supergallery.utils.PermissionUtil
import com.abhinavdev.supergallery.utils.StorageUtil
import com.abhinavdev.supergallery.viewmodels.MainActVM
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity(), ImageLoaderCallback {
    companion object {
        private val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            listOf(
                Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.READ_MEDIA_VIDEO
            )
        } else if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
            listOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        } else {
            listOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var storageUtil: StorageUtil
    private lateinit var mainViewModel: MainActVM

    private var adapter: ImageAdapter? = null
    private var isLoading: Boolean = false

    private val isPermitted: Boolean by lazy {
        PermissionUtil.arePermissionsNotRequired(this, permissions)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        storageUtil = StorageUtil(this)
        // Initialize ViewModel with repository
        mainViewModel = ViewModelProvider(this)[MainActVM::class.java]

        val toolbar: Toolbar = binding.toolbarLayout.toolbar
        setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.app_name)

        if (isPermitted) {
            scanForImage()
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            if (!isLoading) {
                scanForImage()
            }
        }
        setUpRecyclerView()
    }

    private fun scanForImage() {
        ApplicationGallery.appModule.imageRepository.fetchAllImages(WeakReference(this), this)
    }

    private fun setUpRecyclerView() {
        mainViewModel.imageList.observe(this) {
            if (adapter == null) {
                val lm = GridLayoutManager(this, 3)
                adapter = ImageAdapter(this, ArrayList(it))
                binding.rv.setHasFixedSize(true)
                binding.rv.layoutManager = lm
                binding.rv.adapter = adapter
            } else {
                adapter?.addItems(ArrayList(it))
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PermissionUtil.PERMISSION_REQUEST_CODE) {
            if (!grantResults.any { false }) {
                scanForImage()
            }
        }
    }

    override fun onLoading() {
        isLoading = true
        if (!binding.swipeRefreshLayout.isRefreshing) {
            binding.swipeRefreshLayout.isRefreshing = true
        }
    }

    override fun <T> onLoadFinished(imageList: MutableList<T>) {
        isLoading = false
        binding.swipeRefreshLayout.isRefreshing = false
        ImageListUtil.imageList = imageList.filterIsInstance<ImageModel>()
        mainViewModel.setImageList(imageList.filterIsInstance<ImageModel>())
    }
}