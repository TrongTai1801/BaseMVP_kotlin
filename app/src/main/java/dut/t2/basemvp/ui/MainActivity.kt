package dut.t2.basemvp.ui

import android.os.Bundle
import dut.t2.basemvp.R
import dut.t2.basemvp.base.BaseActivity
import dut.t2.basemvp.service.model.Image

class MainActivity : BaseActivity<MainContract.MainView, MainPresenterImpl>(), MainContract.MainView {

    override var mPresenter: MainPresenterImpl = MainPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter.getImage()
    }

    override fun getImageResult(images: List<Image>?) {
        dismissLoading()
        showToast(images!!.get(0).image)
    }
}
