package dut.t2.basemvp.service

import dut.t2.basemvp.service.model.Image
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("movies_2017.json")
    fun getImages() : Call<List<Image>>
}