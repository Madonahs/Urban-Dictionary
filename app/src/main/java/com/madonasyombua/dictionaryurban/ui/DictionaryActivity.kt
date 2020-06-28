package com.madonasyombua.dictionaryurban.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.madonasyombua.dictionaryurban.R
import com.madonasyombua.dictionaryurban.data.api.ERROR_STATUS
import com.madonasyombua.dictionaryurban.data.api.Intent1
import com.madonasyombua.dictionaryurban.data.api.observeWith
import com.madonasyombua.dictionaryurban.data.response.Word
import com.madonasyombua.dictionaryurban.databinding.ActivityDictionaryBinding
import com.madonasyombua.dictionaryurban.ui.adapter.DictionaryAdapter
import com.madonasyombua.dictionaryurban.ui.viewmodels.DictionaryViewModel
import com.madonasyombua.dictionaryurban.utils.ErrorHelper
import kotlinx.android.synthetic.main.activity_dictionary.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DictionaryActivity : AppCompatActivity() {
     val dictionaryViewModel: DictionaryViewModel by viewModel()
    private lateinit var binding : ActivityDictionaryBinding
    private lateinit var dictionaryAdapter: DictionaryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_dictionary)
        binding.recyclerWord.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        dictionaryAdapter = DictionaryAdapter()
        binding.recyclerWord.adapter = dictionaryAdapter

        dictionaryViewModel.progress.observeWith(this, this::onProgress)
        dictionaryViewModel.uiModel.observeWith(this, this:: onSuccess)
        dictionaryViewModel.errorBase.observeWith(this, this::onError)
        //open  definition activity
        dictionaryAdapter.setOnClickListener(object : DictionaryAdapter.OnItemClickListener {
            override fun onClick(view: View, data: Word) {
                startActivity(Intent1(data))
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.queryHint = getString(R.string.search)
        searchView.setBackgroundResource(R.drawable.shape)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                dictionaryViewModel.getDefinition(s.trim())
                return true
            }
            override fun onQueryTextChange(s: String): Boolean {

                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return super.onOptionsItemSelected(item)
    }

    //on success
    private fun onSuccess(data: List<Word>){
        display(this)
        dictionaryAdapter.addWord(data)
    }

    private fun display(activity: Activity){
        val methodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        methodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun onProgress(isShown: Boolean){
        progress_search_bar.visibility =
            if(isShown) View.VISIBLE
            else View.GONE
    }

    private fun onError(errorHelper: ErrorHelper) = when (errorHelper.errorStatus) {
        ERROR_STATUS.ERR -> {
            Toast.makeText(this, getString(R.string.create_new_word), Toast.LENGTH_SHORT).show()
        }
        ERROR_STATUS.NETWORK -> {
            Toast.makeText(this, getString(R.string.network_issue), Toast.LENGTH_SHORT).show()
        }
        else -> {
            Toast.makeText(this, getString(R.string.try_again), Toast.LENGTH_SHORT).show()
        }
    }
}
