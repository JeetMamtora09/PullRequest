package com.demo.navitask.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.navitask.databinding.ActivityMainBinding
import com.demo.navitask.presentation.MainViewModel
import com.demo.navitask.util.LinearSpacingDecoration
import com.demo.navitask.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val pullRequestAdapter = PullRequestAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerview()
        startAndSetObservers()
        loadPullRequest()
    }

    private fun setUpRecyclerview() {
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(LinearSpacingDecoration(10))
            adapter = pullRequestAdapter
        }
    }

    private fun startAndSetObservers() {
        viewModel.pullResponse.observe(this) { response ->
            when (response) {
                is Resource.Success -> {
                    showOrHideProgressBar(false)
                    response.data?.let {
                        if (it.isEmpty())
                            showEmpty()
                        else {
                            binding.rvList.isVisible = true
                            pullRequestAdapter.updatePullRequestList(it)
                        }
                    }
                }
                is Resource.Error -> {
                    showOrHideProgressBar(false)
                    response.message?.let { message ->
                        showError(message)
                    }
                }
                is Resource.Loading -> {
                    showOrHideProgressBar(true)
                }
            }
        }
    }

    private fun showEmpty() {
        binding.apply {
            rvList.isVisible = false
            txtError.isVisible = true
            txtError.setTextColor(ContextCompat.getColor(this@MainActivity, android.R.color.black))
            txtError.text = "No Data Found !!!"
        }
    }

    private fun showError(message: String) {
        binding.apply {
            rvList.isVisible = false
            txtError.isVisible = true

            txtError.setTextColor(
                ContextCompat.getColor(
                    this@MainActivity,
                    android.R.color.holo_red_dark
                )
            )
            txtError.text = message
        }

    }

    private fun showOrHideProgressBar(showProgress: Boolean) {
        binding.apply {
            rvList.isVisible = false
            txtError.isVisible = false
            binding.progress.isVisible = showProgress
        }
    }

    private fun loadPullRequest() {
        viewModel.loadCatFact()
    }
}