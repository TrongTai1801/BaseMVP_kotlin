package dut.t2.basemvp.ui

import dut.t2.basemvp.base.BasePresenter
import dut.t2.basemvp.service.core.ApiClient
import dut.t2.basemvp.service.model.Image
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenterImpl : BasePresenter<MainContract.MainView>(), MainContract.MainPresenter {
    override fun getImage() {

        var call: Call<List<Image>> = ApiClient.getService()!!.getImages()
        call.enqueue(object : Callback<List<Image>> {
            override fun onResponse(call: Call<List<Image>>, response: Response<List<Image>>) {
                if (response.isSuccessful) view!!.getImageResult(response.body())
                else {
                    view!!.dismissLoading()
                    view!!.showToast(response.message())
                }
            }

            override fun onFailure(call: Call<List<Image>>, t: Throwable) {
                view!!.dismissLoading()
                view!!.showToast(t.toString())
            }
        })
    }
}