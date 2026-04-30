package com.example.history

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.core.BaseActivity
import com.example.history.databinding.ActivityHistoryBinding
import com.example.model.AppState
import com.example.model.DataModel
import org.koin.androidx.viewmodel.ext.android.viewModel   // импорт Koin-делегата

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    private lateinit var binding: ActivityHistoryBinding
    override val model: HistoryViewModel by viewModel()
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // iniViewModel()
        initViews()
        model.subscribe().observe(this, Observer<AppState> { renderData(it) })
    }

    // Сразу запрашиваем данные из локального репозитория
    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    // Вызовется из базовой Activity, когда данные будут готовы
    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

   /* private fun iniViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: HistoryViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@HistoryActivity, Observer<AppState> { renderData(it) })
    }*/

    private fun initViews() {
        setSupportActionBar(binding.includedHistory.toolbar)
        binding.historyActivityRecyclerview.adapter = adapter
        setSupportActionBar(binding.includedHistory.toolbar)
    }
}
