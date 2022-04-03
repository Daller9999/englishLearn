package com.sunplacestudio.englishlearn.fragments.addword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sunplacestudio.englishlearn.R
import com.sunplacestudio.englishlearn.database.repository.Word
import com.sunplacestudio.englishlearn.databinding.FragmentAddWordBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentAddWord : Fragment() {

    private val viewModel by viewModel<AddWordViewModel>()

    private lateinit var binding: FragmentAddWordBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            buttonAdd.setOnClickListener {
                val translate = editTextTranslate.text.toString()
                val word = editTextWord.text.toString()
                if (translate.isEmpty() || word.isEmpty()) return@setOnClickListener

                viewModel.addWord(Word(word, translate))

                val context = root.context
                Toast.makeText(
                    context,
                    context.getString(R.string.text_added),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}