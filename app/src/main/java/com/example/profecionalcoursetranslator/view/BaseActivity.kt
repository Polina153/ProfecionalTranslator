package com.example.profecionalcoursetranslator.view

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.profecionalcoursetranslator.BaseViewModel
import com.example.profecionalcoursetranslator.R
import com.example.profecionalcoursetranslator.databinding.LoadingLayoutBinding
import com.example.profecionalcoursetranslator.interactor.Interactor
import com.example.profecionalcoursetranslator.model.data.DataModel
import isOnline


abstract class BaseActivity<T : AppState, I : Interactor<T>> : AppCompatActivity() {

    //private lateinit var binding: LoadingLayoutBinding

    // В каждой Активити будет своя ViewModel, которая наследуется от BaseViewModel
    abstract val model: BaseViewModel<T>
    protected var isNetworkAvailable: Boolean = false

    private var loadingFrameLayout: View? = null
    private var progressBarHorizontal: ProgressBar? = null
    private var progressBarRound: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isNetworkAvailable = isOnline(applicationContext)
    }

    override fun onResume() {
        super.onResume()
        //  binding = LoadingLayoutBinding.inflate(layoutInflater)
        if (loadingFrameLayout == null) {
            loadingFrameLayout = findViewById(R.id.loading_frame_layout)
            progressBarHorizontal = findViewById(R.id.progress_bar_horizontal)
            progressBarRound = findViewById(R.id.progress_bar_round)
        }

        isNetworkAvailable = isOnline(applicationContext)
        if (!isNetworkAvailable && isDialogNull()) {
            showNoInternetConnectionDialog()
        }
    }

    // Каждая Активити будет отображать какие-то данные в соответствующем состоянии
    protected fun renderData(appState: T) {
        when (appState) {
            is AppState.Success -> {
                showViewWorking()
                appState.data?.let {
                    if (it.isEmpty()) {
                        showAlertDialog(
                            getString(R.string.dialog_tittle_sorry),
                            getString(R.string.empty_server_response_on_success)
                        )
                    } else {
                        setDataToAdapter(it)
                    }
                }
            }

            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    progressBarHorizontal?.visibility = View.VISIBLE
                    progressBarRound?.visibility = View.GONE
                    progressBarHorizontal?.progress = appState.progress ?: 0
                } else {
                    progressBarHorizontal?.visibility = View.GONE
                    progressBarRound?.visibility = View.VISIBLE
                }
            }

            is AppState.Error -> {
                showViewWorking()
                showAlertDialog(getString(R.string.error_stub), appState.error.message)
            }
        }
    }


    protected fun showNoInternetConnectionDialog() {
        showAlertDialog(
            getString(R.string.dialog_title_device_is_offline),
            getString(R.string.dialog_message_device_is_offline)
        )
    }

    protected fun showAlertDialog(title: String?, message: String?) {
        AlertDialogFragment.newInstance(title, message)
            .show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    private fun showViewWorking() {
        loadingFrameLayout?.visibility = View.GONE
    }

    private fun showViewLoading() {
        loadingFrameLayout?.visibility = View.VISIBLE
    }


    private fun isDialogNull(): Boolean {
        return supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    // Объявим абстрактный метод и будем вызывать его в renderData, когда данные
    // будут готовы для отображения
    abstract fun setDataToAdapter(data: List<DataModel>)


    companion object {
        private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
    }
}
/*
abstract class BaseActivity<T : AppState> : AppCompatActivity(), View {
    // Храним ссылку на презентер
    protected lateinit var presenter: Presenter<T, View>
    protected abstract fun createPresenter(): Presenter<T, View>
    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }
    // Когда View готова отображать данные, передаём ссылку на View в презентер
    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }
    // При пересоздании или уничтожении View удаляем ссылку, иначе в презентере
    // будет ссылка на несуществующую View
    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}*/
