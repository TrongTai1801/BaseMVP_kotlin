package dut.t2.basemvp.base

import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import dut.t2.basemvp.R
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity
import java.lang.Exception

@EActivity
abstract class BaseActivity<V : BaseView, T : BasePresenter<V>> : AppCompatActivity(), BaseView {

    private val TAG = this.javaClass.simpleName
    protected abstract var mPresenter: T
    protected var mActionBar: ActionBar? = null

    lateinit var mProgressDialog: ProgressDialog

    protected abstract fun afterViews()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        if (mPresenter != null) mPresenter.attachView(this as V)
        initProgressDialog()
    }

    @AfterViews
    protected fun initViews() {
        initActionBar()
        this.afterViews()
    }

    override fun onDestroy() {
        mPresenter.detachView()
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing) {
                mProgressDialog.dismiss()
            }
        } catch (e: Exception) {
            Log.e(TAG, "" + e.message)
        }
        super.onDestroy()
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun initProgressDialog() {
        mProgressDialog = ProgressDialog(this)
        mProgressDialog.setMessage(getString(R.string.loading))
        mProgressDialog.setCancelable(false)
    }

    override fun showLoading() {
        if (mProgressDialog != null && !mProgressDialog.isShowing && !isFinishing) mProgressDialog.show()
    }

    override fun dismissLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing && !isFinishing) mProgressDialog.dismiss()
    }

    override fun showError(error: String) {
        showAlertDialog(error)
    }

    override fun showMessage(message: String) {
        showAlertDialog(message)
    }

    protected fun showAlertDialog(msg: String) {
        try {
            AlertDialog.Builder(this)
                .setMessage(msg)
                .setPositiveButton(android.R.string.ok, null)
                .show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun initActionBar() {
        mActionBar = supportActionBar
        if (mActionBar != null) {
            val viewActionBar = layoutInflater.inflate(R.layout.actionbar, null)
            val params = ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER
            )
            mActionBar!!.setCustomView(viewActionBar, params)
            mActionBar!!.setDisplayShowCustomEnabled(true)
            mActionBar!!.setDisplayShowTitleEnabled(false)
            mActionBar!!.setDisplayHomeAsUpEnabled(true)
            mActionBar!!.setHomeButtonEnabled(true)
            mActionBar!!.show()
        }
    }
}