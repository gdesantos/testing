package com.overmind.mywork.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.overmind.core.data.Failure
import com.overmind.core.data.Success
import com.overmind.mywork.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configVmBindings()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun configVmBindings() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.state.collect {
                        when(it) {
                            MainViewModel.State.Idle -> binding.progress.visibility = View.GONE
                            MainViewModel.State.Loading -> binding.progress.visibility = View.VISIBLE
                        }
                    }
                }

                launch {
                    viewModel.pokemonList.collect {
                        when(it) {
                            is Failure -> Toast.makeText(this@MainActivity, "Failed", Toast.LENGTH_SHORT).show()
                            is Success -> binding.list.adapter = PokemonListAdapter(it.value) {
                                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    }
                }
            }
        }
    }
}