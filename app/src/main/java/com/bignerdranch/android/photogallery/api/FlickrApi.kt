package com.bignerdranch.android.photogallery.api


import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface FlickrApi {
    @GET(
        "services/rest/? method=flickr.interestingness.getList" +
                "&api_key=860d5d1776612833216835622f893480" +
                "&format=json"+
                "&nojsoncallback=1" +
                "&extras=url_s"


    )
    //@GET("services/rest?method = flickr.interestingness.getList")
    fun fetchPhotos(): Call<FlickrResponse>
    @GET
    fun fetchUrlBytes(@Url url:String):
            Call<ResponseBody>
    @GET("services/rest?method=flickr.photos.search")
    fun searchPhotos(@Query("text") query:String):Call<FlickrResponse>
}