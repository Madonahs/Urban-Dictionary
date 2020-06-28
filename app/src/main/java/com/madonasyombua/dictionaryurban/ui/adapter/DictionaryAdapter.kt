package com.madonasyombua.dictionaryurban.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.madonasyombua.dictionaryurban.data.response.Word
import com.madonasyombua.dictionaryurban.databinding.WordAdapterBinding

/**
 * @author madona
 * Note: Lateinit comes very handy when a non-null initializer cannot be supplied in the constructor,
 * but the developer is certain that the variable will not be null when accessing it,
 * thus avoiding null checks when referencing it later.
 */
class DictionaryAdapter : RecyclerView.Adapter<DictionaryAdapter.DictionaryBindingHolder>(){

    private lateinit var listener : OnItemClickListener
    private var wordList : ArrayList<Word> = ArrayList()
    // inner class Dictionary Binding Holder
    class DictionaryBindingHolder(var bindingHolder: WordAdapterBinding) : RecyclerView.ViewHolder(bindingHolder.root)
    // on create view hold to hook the item to the UI
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryBindingHolder {
        setOnClickListener(listener)
        val  layoutInflater = LayoutInflater.from(parent.context)
        val bindingHolder = WordAdapterBinding.inflate(layoutInflater,parent, false)

        return  DictionaryBindingHolder(bindingHolder)
    }
    //get exact count
    override fun getItemCount(): Int {
        return wordList.size
    }
    // bind the views
    override fun onBindViewHolder(holder: DictionaryBindingHolder, position: Int) {

        val data = wordList[position]
        holder.bindingHolder.model = data // binding the data to the view
        holder.bindingHolder.root.setOnClickListener {
            listener.onClick(it,data)
        }
    }

    fun setOnClickListener(listener: OnItemClickListener){
        this.listener = listener
    }
    // interface
    interface  OnItemClickListener{
        fun onClick(view: View, data : Word)
    }

    // method to add words
    fun addWord(list: List<Word>){
        if(wordList.isNotEmpty()){
            wordList.clear()
        }else
            wordList.addAll(list)
        notifyDataSetChanged() // notify the data change
    }
}