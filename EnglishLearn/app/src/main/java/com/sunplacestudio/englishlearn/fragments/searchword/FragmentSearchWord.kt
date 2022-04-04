package com.sunplacestudio.englishlearn.fragments.searchword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunplacestudio.englishlearn.MainActivity
import com.sunplacestudio.englishlearn.R
import com.sunplacestudio.englishlearn.databinding.FragmentSearchWordBinding
import com.sunplacestudio.englishlearn.databinding.FragmentWordsBinding
import com.sunplacestudio.englishlearn.fragments.editword.FragmentEditWord
import com.sunplacestudio.englishlearn.fragments.words.WordsViewModel
import com.sunplacestudio.englishlearn.fragments.words.adapter.WordAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentSearchWord : Fragment() {

    private val viewModel by viewModel<SearchViewModel>()

    private lateinit var binding: FragmentSearchWordBinding

    private val navController by lazy {
        Navigation.findNavController(activity as MainActivity, R.id.nav_host_fragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wordAdapter = WordAdapter {
            navController.navigate(
                R.id.action_fragmentSearchWord_to_fragmentEditWord,
                bundleOf(FragmentEditWord.EDIT_WORD_ARGS to it.id)
            )
        }

        with(binding) {
            editTextWord.requestFocus()
            wordRecycler.layoutManager = LinearLayoutManager(root.context)
            wordRecycler.adapter = wordAdapter
            editTextWord.addTextChangedListener {
                val text = it.toString()
                if (text.isEmpty()) return@addTextChangedListener
                viewModel.search(text)
            }
        }

        viewModel.words.observe(viewLifecycleOwner) {
            wordAdapter.submitList(it, true, true)
        }
    }

}