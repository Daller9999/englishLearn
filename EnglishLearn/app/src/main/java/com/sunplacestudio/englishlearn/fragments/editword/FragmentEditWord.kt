package com.sunplacestudio.englishlearn.fragments.editword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.sunplacestudio.englishlearn.MainActivity
import com.sunplacestudio.englishlearn.R
import com.sunplacestudio.englishlearn.database.repository.Word
import com.sunplacestudio.englishlearn.databinding.FragmentEditWordBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentEditWord : Fragment() {

    companion object {
        const val EDIT_WORD_ARGS = "EDIT_WORD_ARGS"
    }

    private val viewModel by viewModel<EditWordViewModel>()

    private lateinit var binding: FragmentEditWordBinding

    private val navController by lazy {
        Navigation.findNavController(activity as MainActivity, R.id.nav_host_fragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        if (args != null) {
            val id = args.getLong(EDIT_WORD_ARGS, -1)
            if (id > 0) {
                viewModel.uploadWord(id)
            }
        }

        viewModel.word.observe(viewLifecycleOwner) {
            with(binding) {
                editTextTranslate.setText(it.translate)
                editTextWord.setText(it.word)
            }
        }

        with(binding) {
            buttonAdd.setOnClickListener {
                val translate = editTextTranslate.text.toString()
                val word = editTextWord.text.toString()
                if (translate.isEmpty() || word.isEmpty()) return@setOnClickListener

                viewModel.updateWord(Word(word, translate))

                val context = root.context
                Toast.makeText(
                    context,
                    context.getString(R.string.text_word_updated),
                    Toast.LENGTH_SHORT
                ).show()

                navController.popBackStack()
            }
        }
    }
}