package com.abhinavdev.supergallery.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhinavdev.supergallery.R
import com.abhinavdev.supergallery.ui.adapters.ImageAdapter
import com.abhinavdev.supergallery.constants.ActivityNames
import com.abhinavdev.supergallery.databinding.ActivityHideBinding
import com.abhinavdev.supergallery.repositories.implementations.ImgRepoImpl
import com.abhinavdev.supergallery.utils.StorageUtil
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.ref.WeakReference

class HideActivity : AppCompatActivity() {
    private val binding: ActivityHideBinding by lazy {
        ActivityHideBinding.inflate(layoutInflater)
    }
    private lateinit var storageUtil: StorageUtil
    private lateinit var recyclerView: RecyclerView
    private var adapter: ImageAdapter? = null

    // Create a new ActivityResultLauncher to handle the delete request result
    private val deleteMusicRequestLauncher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                // Delete request was successful
                Log.d("TAG", "Delete request was successful")
            } else {
                Log.d("TAG", "Delete request Failed")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        storageUtil = StorageUtil(this)
        val toolbar: Toolbar = binding.toolbarLayout.toolbar
        setSupportActionBar(toolbar)
        toolbar.title = "Hide Activity"
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }



        recyclerView = binding.rv
        val fab: FloatingActionButton = binding.recoveryAct
        val lm = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        adapter = ImageAdapter(this, list)
//        recyclerView.layoutManager = lm
//        recyclerView.adapter = adapter

        fab.setOnClickListener {

        }
    }

    override fun onResume() {
        storageUtil.saveActivity(ActivityNames.HIDE_ACTIVITY)
        refresh()
        super.onResume()
    }

    fun refresh() {
    }
}