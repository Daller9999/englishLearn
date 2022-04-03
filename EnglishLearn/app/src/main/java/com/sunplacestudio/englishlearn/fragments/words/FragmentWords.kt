package com.sunplacestudio.englishlearn.fragments.words

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunplacestudio.englishlearn.MainActivity
import com.sunplacestudio.englishlearn.R
import com.sunplacestudio.englishlearn.databinding.FragmentWordsBinding
import com.sunplacestudio.englishlearn.fragments.editword.FragmentEditWord.Companion.EDIT_WORD_ARGS
import com.sunplacestudio.englishlearn.fragments.words.adapter.WordAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentWords : Fragment() {

    private val viewModel by viewModel<WordsViewModel>()

    private lateinit var binding: FragmentWordsBinding

    private val navController by lazy {
        Navigation.findNavController(activity as MainActivity, R.id.nav_host_fragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWordsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.update()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wordAdapter = WordAdapter {
            navController.navigate(
                R.id.action_fragmentWords_to_fragmentEditWord,
                bundleOf(EDIT_WORD_ARGS to it.id)
            )
        }

        with(binding) {
            wordRecycler.layoutManager = LinearLayoutManager(root.context)
            wordRecycler.adapter = wordAdapter
            checkBoxTranslate.setOnClickListener { viewModel.update() }
            checkBoxWord.setOnClickListener { viewModel.update() }
        }

        viewModel.words.observe(viewLifecycleOwner) {
            val isShowWord = binding.checkBoxWord.isChecked
            val isShowTranslate = binding.checkBoxTranslate.isChecked
            wordAdapter.submitList(it, isShowWord, isShowTranslate)
        }
    }
}