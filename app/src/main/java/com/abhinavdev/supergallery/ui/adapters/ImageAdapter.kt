package com.abhinavdev.supergallery.ui.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhinavdev.supergallery.R
import com.abhinavdev.supergallery.databinding.ShowImageLayoutBinding
import com.abhinavdev.supergallery.models.ImageModel
import com.abhinavdev.supergallery.ui.screens.MainActivity
import com.abhinavdev.supergallery.ui.screens.ImagePagerActivity
import com.abhinavdev.supergallery.utils.AbhinavUtil.getSizeOfView
import com.abhinavdev.supergallery.utils.ImageLoadingUtil
import com.abhinavdev.supergallery.utils.ImageMover
import com.abhinavdev.supergallery.utils.StorageUtil
import java.lang.ref.WeakReference

class ImageAdapter(private val context: Context, private var list: MutableList<ImageModel>) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    private var mover: ImageMover? = null
    private val storageUtil: StorageUtil = StorageUtil(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.show_image_layout, parent, false)
        val binding = ShowImageLayoutBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position],position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(items: MutableList<ImageModel>) {
        list = ArrayList(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ShowImageLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        internal fun onBind(item: ImageModel,position: Int) {
            val height = storageUtil.getImageHeight()

            if (height == -1 || height == 0 || height == null) {
                binding.root.getSizeOfView {
                    setImageViewHeight(it.width, binding.root)
                    storageUtil.saveImageHeight(it.width)
                }
            } else {
                setImageViewHeight(height, binding.root)
            }

            mover = ImageMover(WeakReference(context as Activity))

            ImageLoadingUtil.loadFromModel(item, R.drawable.placeholder_image, imageView = binding.imageView, imageMeasure = 256)

            binding.imageView.setOnClickListener {
                //opening fragment when clicked on playlist
                if (context is MainActivity) {
                    context.startActivity(Intent(context,ImagePagerActivity::class.java).apply {
                        putExtra(ImagePagerActivity.CURRENT_POSITION_ARG,position)
                    }, ActivityOptions.makeSceneTransitionAnimation(context).toBundle())
                }

            }
            binding.imageView.setOnLongClickListener {

                true
            }

        }

        private fun setImageViewHeight(height: Int?, view: View) {
            val params = view.layoutParams
            if (height != null) {
                params.height = height
            }
            view.layoutParams = params
        }

    }
}
