package com.abhinavdev.supergallery.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abhinavdev.supergallery.R
import com.abhinavdev.supergallery.databinding.FragmentOpenImageBinding
import com.abhinavdev.supergallery.models.ImageModel
import com.abhinavdev.supergallery.utils.ImageLoadingUtil
import java.io.File

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val IMAGE_MODEL = "image_model"

/**
 * A simple [Fragment] subclass.
 * Use the [OpenImageFragment.newNormalInstance] factory method to
 * create an instance of this fragment.
 */
class OpenImageFragment : Fragment() {
    companion object {
        var isFolderHidden: Boolean = false

        /**
         * @return A new instance of fragment OpenImageFragment.
         */
        @JvmStatic
        fun newNormalInstance(imageModel: ImageModel?) =
            OpenImageFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(IMAGE_MODEL, imageModel)
                }
                isFolderHidden = false
            }

        /**
         * @return A new instance of fragment OpenImageFragment.
         */
        @JvmStatic
        fun newInstanceForHiddenFolder(imageModel: ImageModel?) =
            OpenImageFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(IMAGE_MODEL, imageModel)
                }
                isFolderHidden = true
            }
    }

    private var imageModel:ImageModel? = null
    private var __fragmentOpenImageBinding: FragmentOpenImageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = __fragmentOpenImageBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                 it.getSerializable(IMAGE_MODEL,ImageModel::class.java)
            }else{
                it.getSerializable(IMAGE_MODEL) as ImageModel
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        __fragmentOpenImageBinding = FragmentOpenImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageModel?.apply {
            if (isFolderHidden) {
                ImageLoadingUtil.loadFromFile(
                    File(data),
                    placeholderImage = R.drawable.placeholder_image,
                    imageView = binding.myZoomageView,
                    imageMeasure = height.coerceAtLeast(width)
                )
            } else {
                ImageLoadingUtil.loadFromUri(
                    uri,
                    placeholderImage = R.drawable.placeholder_image,
                    imageView = binding.myZoomageView,
                    imageMeasure = height.coerceAtLeast(width)
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        __fragmentOpenImageBinding = null
    }
}