package com.sunplacestudio.englishlearn.fragments.learn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sunplacestudio.englishlearn.databinding.FragmentLearnBinding
import com.sunplacestudio.englishlearn.visibleOrGone
import com.sunplacestudio.englishlearn.visibleOrInvisible
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentLearn : Fragment() {

    private val viewModel by viewModel<LearnViewModel>()

    private lateinit var binding: FragmentLearnBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLearnBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            buttonNext.setOnClickListener { viewModel.next() }
            buttonShowAll.setOnClickListener {
                editTextWord.visibleOrInvisible(true)
                editTextTranslate.visibleOrInvisible(true)
            }
        }
        viewModel.word.observe(viewLifecycleOwner) { state ->
            with(binding) {
                editTextWord.text = state.word.word
                editTextTranslate.text = state.word.translate
                editTextTranslate.visibleOrInvisible(state.isShowTranslate)
                editTextWord.visibleOrInvisible(state.isShowWord)
            }
        }
    }
}