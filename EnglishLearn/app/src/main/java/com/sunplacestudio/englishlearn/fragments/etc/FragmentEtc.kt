package com.sunplacestudio.englishlearn.fragments.etc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sunplacestudio.englishlearn.databinding.FragmentEtcBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentEtc : Fragment() {

    private val viewModel by viewModel<EtcViewModel>()

    private lateinit var binding: FragmentEtcBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEtcBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            buttonPrintToConsole.setOnClickListener {
                viewModel.printAllToConsole()
            }
            buttonExportDB.setOnClickListener {
                viewModel.closeDB()
            }
        }

        viewModel.state.observe(viewLifecycleOwner) {
            with(binding) {
                val text = "words count: ${it.count}"
                textViewWordsCount.text = text
            }
        }
    }

}