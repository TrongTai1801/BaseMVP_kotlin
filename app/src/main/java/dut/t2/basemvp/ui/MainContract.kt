package dut.t2.basemvp.ui

import dut.t2.basemvp.base.BaseView
import dut.t2.basemvp.service.model.Image

interface MainContract {
    interface MainView : BaseView {
        fun getImageResult(images: List<Image>?)
    }

    interface MainPresenter {
        fun getImage()
    }
}