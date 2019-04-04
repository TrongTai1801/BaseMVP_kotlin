package dut.t2.basemvp.ui

import dut.t2.basemvp.R
import dut.t2.basemvp.base.BaseActivity
import dut.t2.basemvp.service.model.Image
import kotlinx.android.synthetic.main.activity_main.*
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_main)
class MainActivity : BaseActivity<MainContract.MainView, MainPresenterImpl>(), MainContract.MainView {

    override var mPresenter: MainPresenterImpl = MainPresenterImpl()

    override fun afterViews() {
        mPresenter.getImage()
    }

    override fun getImageResult(images: List<Image>?) {
        dismissLoading()
        tv.setText(images!!.get(0).image)
    }
}
