package com.abhinavdev.supergallery.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abhinavdev.supergallery.databinding.FragmentOpenImageBinding

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM = "position_of_image"

/**
 * A simple [Fragment] subclass.
 * Use the [OpenImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OpenImageFragment : Fragment() {
    private var position: Int = -1
    private var __fragmentOpenImageBinding: FragmentOpenImageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = __fragmentOpenImageBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_PARAM, -1)
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

    }

    override fun onDestroyView() {
        super.onDestroyView()
        __fragmentOpenImageBinding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param position Position of image.
         * @return A new instance of fragment OpenImageFragment.
         */
        @JvmStatic
        fun newInstance(position: Int = 0) =
            OpenImageFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM, position)
                }
            }
    }
}