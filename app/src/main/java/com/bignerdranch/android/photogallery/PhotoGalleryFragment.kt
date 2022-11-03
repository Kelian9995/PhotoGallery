package com.bignerdranch.android.photogallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call

import retrofit2.Callback
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "PhotoGalleryFragment"


class PhotoGalleryFragment : Fragment() {
    private lateinit var photoGalleryViewModel: PhotoGalleryViewModel
    private lateinit var photoRecyclerView:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.flickr.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        val flickrApi: FlickrApi = retrofit.create(FlickrApi::class.java)

        */
       /* val flickrHomePageRequest: Call<String> = flickrApi.fetchContents()

        flickrHomePageRequest.enqueue(object: Callback<String> {
            override fun onFailure(call:Call<String>, t: Throwable) {
                Log.e(TAG,"Failed to fetch photos", t)

            }
            override fun onResponse(
                call: Call<String>,
                response:Response<String>
            ) {
                Log.d(TAG, "Responce received: ${response.body()}")
            }
        })

        */
        /*val flickrLiveData: LiveData<List<GalleryItem>> =
            FlickrFetchr().fetchPhotos()
        flickrLiveData.observe(
            this,
            Observer {
                galleryItems ->
                Log.d(TAG, "Response received: $galleryItems")
            } )

         */

        photoGalleryViewModel = ViewModelProviders.of(this).get(PhotoGalleryViewModel::class.java)
    }

    override fun onCreateView (
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val view = inflater.inflate(R.layout.fragment_photo_gallery,container,false)
        photoRecyclerView = view.findViewById(R.id.photo_recycler_view)
        photoRecyclerView.layoutManager = GridLayoutManager(context, 3)
        return view

    }
    override fun onViewCreated(view:View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoGalleryViewModel.galleryItemLiveData.observe(
            viewLifecycleOwner,
            Observer {
                galleryItems ->
                //Log.d(TAG, "Have gallery items from ViewModel $galleryItems")
                //Обновить данные поддерживающие представление утилизатора
                photoRecyclerView.adapter = PhotoAdapter(galleryItems)
            }
        )
    }
    private class PhotoHolder(itemTextView: TextView):RecyclerView.ViewHolder(itemTextView)
    {
        val bindTitle:(CharSequence) -> Unit = itemTextView::setText
    }

    private class PhotoAdapter(private val galleryItems: List<GalleryItem>)
        :RecyclerView.Adapter<PhotoHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
            val textView=TextView(parent.context)
            return PhotoHolder(textView)
        }
        override fun getItemCount():Int = galleryItems.size
        override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
            val galleryItem = galleryItems[position]
            holder.bindTitle(galleryItem.title)
        }
    }

    companion object {
        fun newInstance() = PhotoGalleryFragment()
    }
}