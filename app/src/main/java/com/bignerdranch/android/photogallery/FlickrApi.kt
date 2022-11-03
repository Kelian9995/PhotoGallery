package com.bignerdranch.android.photogallery


import retrofit2.Call
import retrofit2.http.GET


interface FlickrApi {
    @GET(
        "services/rest/? method=flickr.interestingness.getList" +
                "&api_key=860d5d1776612833216835622f893480" +
                "&format=json"+
                "&nojsoncallback=1" +
                "&extras=url_s"


    )

    fun fetchPhotos(): Call<FlickrResponse>
}