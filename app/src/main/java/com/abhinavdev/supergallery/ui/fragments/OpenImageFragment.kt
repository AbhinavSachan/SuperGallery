package com.abhinavdev.supergallery.ui.fragments

import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.abhinavdev.supergallery.R
import com.abhinavdev.supergallery.databinding.FragmentOpenImageBinding
import com.abhinavdev.supergallery.utils.ImageLoadingUtil
import java.io.File

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val URI_OF_IMAGE = "uri_of_image"
private const val DATA_OF_IMAGE = "data_of_image"
private const val IS_HIDDEN = "is_hidden"

/**
 * A simple [Fragment] subclass.
 * Use the [OpenImageFragment.newNormalInstance] factory method to
 * create an instance of this fragment.
 */
class OpenImageFragment : Fragment() {
    private var uri: Uri = Uri.parse("")
    private var data: String = ""
    private var isHidden: Boolean = false
    private var __fragmentOpenImageBinding: FragmentOpenImageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = __fragmentOpenImageBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isHidden = it.getBoolean(IS_HIDDEN, false)
            if (isHidden) {
                data = it.getString(DATA_OF_IMAGE, "")
            }else{
                uri = it.getString(URI_OF_IMAGE, "").toUri()
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
        if (isHidden){
            ImageLoadingUtil.loadFromFile(File(data), placeholderImage = R.drawable.placeholder_image, imageView = binding.myZoomageView, overrideSize = false, imageMeasure = 2048)
        }else{
            ImageLoadingUtil.loadFromUri(uri, placeholderImage = R.drawable.placeholder_image, imageView = binding.myZoomageView, overrideSize = false, imageMeasure = 2048)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        __fragmentOpenImageBinding = null
    }

    companion object {
        /**
         * @param uri uri of image when its not hidden.
         * @return A new instance of fragment OpenImageFragment.
         */
        @JvmStatic
        fun newNormalInstance(uri: Uri?) =
            OpenImageFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(IS_HIDDEN,false)
                    putString(URI_OF_IMAGE, uri.toString())
                }
            }
        /**
         * @param data path of image only when file is hidden.
         * @return A new instance of fragment OpenImageFragment.
         */
        @JvmStatic
        fun newInstanceForHiddenFolder(data:String?) =
            OpenImageFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(IS_HIDDEN,true)
                    putString(DATA_OF_IMAGE, data)
                }
            }
    }
}