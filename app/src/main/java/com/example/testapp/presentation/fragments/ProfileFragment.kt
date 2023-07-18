package com.example.testapp.presentation.fragments

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.databinding.FragmentProfileBinding
import com.example.testapp.presentation.adapters.StrongWeakSideAdapter
import com.example.testapp.presentation.models.StrongWeakSideItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val weakSideAdapter by lazy { StrongWeakSideAdapter() }
    private val strongSideAdapter by lazy { StrongWeakSideAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val content = SpannableString(getString(R.string.change_profile))
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        binding.profileHeaderLayout.tvChangeProfile.text = content
        setUpStrongSideList()
        setUpWeakSideList()
    }

    private fun setUpWeakSideList() {
        binding.rvWeakSide.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = weakSideAdapter
            weakSideAdapter.setList(
                listOf(
                    StrongWeakSideItem(
                        1,
                        context.getString(R.string.Perfectionism),
                        R.color.color_FF7E73,
                        R.drawable.background_red
                    ),
                    StrongWeakSideItem(
                        2,
                        context.getString(R.string.analytics),
                        R.color.color_FF7E73,
                        R.drawable.background_red
                    )
                )
            )
        }
    }

    private fun setUpStrongSideList() {
        binding.rvStrongSide.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = strongSideAdapter
            strongSideAdapter.setList(
                listOf(
                    StrongWeakSideItem(
                        1,
                        context.getString(R.string.analytics),
                        R.color.color_479696,
                        R.drawable.background_blue
                    ),
                    StrongWeakSideItem(
                        2,
                        context.getString(R.string.Perfectionism),
                        R.color.color_479696,
                        R.drawable.background_blue
                    ),
                    StrongWeakSideItem(
                        3,
                        context.getString(R.string.analytics),
                        R.color.color_479696,
                        R.drawable.background_blue
                    )
                )
            )
        }
    }

    companion object {
        const val TAB_POSITION = 1
        fun newInstance(): ProfileFragment = ProfileFragment()
    }
}