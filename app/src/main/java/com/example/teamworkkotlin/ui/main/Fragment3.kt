package com.example.teamworkkotlin.ui.main


import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teamworkkotlin.R
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.*


class Fragment3 : Fragment() {
    inner class Instancefield(
        @field:SerializedName("id") var id: Int, @field:SerializedName(
            "title"
        ) var title: String, @field:SerializedName("url") var url: String
    ) {

        override fun toString(): String {
            return title
        }

    }

    internal interface ApiService {
        @get:GET("photos")
        val instancefield: Call<List<Instancefield?>?>?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        val rootView: View = inflater.inflate(R.layout.fragment3, parent, false)
        val recyclerView =
            rootView.findViewById<View>(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        //        recyclerView.setAdapter(new RecyclerAdapter());
        val adapter = RecyclerAdapter()
        recyclerView.adapter = adapter
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitClient =
            retrofit.create(RetrofitClient::class.java)
        val getObject: Call<ArrayList<Any?>?>? =
            retrofitClient.`object`
        getObject?.enqueue(object :
            Callback<ArrayList<Instancefield?>?>! {
            override fun onResponse(
                call: Call<ArrayList<Instancefield?>>,
                response: Response<ArrayList<Instancefield?>>
            ) {
                Log.d(ContentValues.TAG, "onResponse:called  " + response.code())
                val instancefield = response.body()!!
                Log.d(ContentValues.TAG, "onResponse: HERE" + response.body())
                Log.d(
                    ContentValues.TAG,
                    "onResponse: test $instancefield"
                )
                adapter.setDatas(instancefield)
            }

            override fun onFailure(
                call: Call<ArrayList<Instancefield?>>,
                t: Throwable
            ) {
            }
        })
        return rootView
    }
}

private fun <T> Call<T>?.enqueue(callback: Callback<ArrayList<Fragment3.Instancefield>>) {

}
