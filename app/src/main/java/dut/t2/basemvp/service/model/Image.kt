package dut.t2.basemvp.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Image(@SerializedName("title") @Expose var title: String,
                 @SerializedName("image") @Expose var image: String,
                 @SerializedName("price") @Expose var  price: String)