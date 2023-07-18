package com.example.testapp.presentation.fragments

import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.databinding.FragmentHomeBinding
import com.example.testapp.presentation.adapters.NewsAdapter
import com.example.testapp.presentation.models.NewsItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val newsAdapter by lazy { NewsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setAdapter() {
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = newsAdapter
            newsAdapter.setList(
                listOf(
                    NewsItem(
                        1,
                        context.getString(R.string.item_text),
                        R.drawable.background_type_one
                    ),
                    NewsItem(
                        2,
                        context.getString(R.string.item_text),
                        R.drawable.background_type_two
                    ),
                    NewsItem(
                        3,
                        context.getString(R.string.item_text),
                        R.drawable.background_type_three
                    )
                )
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        val progressValue = 60
        setProgressBar(progressValue)
    }

    private fun setProgressBar(progressValue: Int) {
        val title = getString(R.string.progress_title)
        val spannableString = SpannableString("$title $progressValue%")
        val colorSpan = ForegroundColorSpan(resources.getColor(R.color.color_C93F8D))
        spannableString.setSpan(colorSpan, 24, 28, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvProgressBarTitle.text = spannableString
        binding.pbOverallProgress.setProgress(progressValue, true)
    }

    companion object {
        const val TAB_POSITION = 0
        fun newInstance(): HomeFragment = HomeFragment()
    }
}