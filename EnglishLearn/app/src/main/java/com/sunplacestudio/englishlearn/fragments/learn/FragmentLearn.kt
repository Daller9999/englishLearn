package com.sunplacestudio.englishlearn.fragments.learn

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sunplacestudio.englishlearn.databinding.FragmentLearnBinding
import com.sunplacestudio.englishlearn.visibleOrInvisible
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class FragmentLearn : Fragment() {

    private val viewModel by viewModel<LearnViewModel>()

    private lateinit var binding: FragmentLearnBinding

    private val textToSpeechEngine: TextToSpeech by lazy {
        TextToSpeech(requireContext()) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeechEngine.language = Locale.US
            }
        }
    }

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
            buttonListen.setOnClickListener {
                val word = viewModel.word.value?.word?.word ?: return@setOnClickListener
                textToSpeechEngine.speak(word, TextToSpeech.QUEUE_FLUSH, null, "null")
            }
            buttonStart.setOnClickListener {
                val min = editTextMin.text.toString().toIntOrNull()
                val max = editTextMax.text.toString().toIntOrNull()
                if (min == null || max == null) return@setOnClickListener
                viewModel.start(min, max)
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
        viewModel.size.observe(viewLifecycleOwner) {
            with(binding) {
                editTextMax.setText(it.toString())
                editTextMin.setText("0")
            }
        }
        viewModel.currentSize.observe(viewLifecycleOwner) {
            val text = "current size = $it"
            binding.textViewCurrentSize.text = text
        }
    }

}