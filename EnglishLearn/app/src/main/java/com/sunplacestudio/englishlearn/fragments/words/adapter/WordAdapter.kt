package com.sunplacestudio.englishlearn.fragments.words.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sunplacestudio.englishlearn.database.repository.Word
import com.sunplacestudio.englishlearn.databinding.ItemWordBinding
import com.sunplacestudio.englishlearn.visibleOrGone

class WordAdapter(
    private val onWordClicked: (Word) -> Unit
) : RecyclerView.Adapter<WordAdapter.WordView>() {

    private val wordList = arrayListOf<Word>()
    private var isShowWord = true
    private var isShowTranslate = true

    class WordView(
        view: View,
        private val onWordClicked: (Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        private val binding = ItemWordBinding.bind(view)

        init {
            binding.root.setOnClickListener {
                onWordClicked(adapterPosition)
            }
        }

        fun bind(word: Word, isShowTranslate: Boolean, isShowWord: Boolean) {
            with(binding) {
                textViewTranslate.visibleOrGone(isShowTranslate)
                textViewWord.visibleOrGone(isShowWord)
                textViewWord.text = word.word
                textViewTranslate.text = word.translate
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<Word>, isShowWord: Boolean, isShowTranslate: Boolean) {
        this.isShowTranslate = isShowTranslate
        this.isShowWord = isShowWord
        wordList.clear()
        wordList.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordView {
        val inflater = LayoutInflater.from(parent.context)
        return WordView(
            ItemWordBinding.inflate(
                inflater,
                parent,
                false
            ).root,
            this::onWordPressed
        )
    }

    private fun onWordPressed(position: Int) {
        onWordClicked(wordList[position])
    }

    override fun onBindViewHolder(holder: WordView, position: Int) {
        holder.bind(wordList[position], isShowTranslate, isShowWord)
    }

    override fun getItemCount(): Int {
        return wordList.size
    }
}