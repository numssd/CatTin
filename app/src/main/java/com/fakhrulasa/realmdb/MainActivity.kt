package com.fakhrulasa.realmdb

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import com.bumptech.glide.Glide
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response


class MainActivity : Activity() {
    private var catList: List<Cat>? = null
    private var currentIndex: Int = 0

    private lateinit var favouritesList: ArrayList<Cat>

    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Realm.init(this)
        realm = Realm.getDefaultInstance()

        favouritesList = ArrayList()

        val call = RetrofitInstance().catService().getImage()
        call.enqueue(object : retrofit2.Callback<List<Cat>?> {
            override fun onResponse(
                call: Call<List<Cat>?>?,
                response: Response<List<Cat>?>?
            ) {
                response?.body()?.let {
                    catList = it.toList()
                    val cat: Cat = it[currentIndex]

                    Glide
                        .with(this@MainActivity)
                        .load(cat.url)
                        .into(imageViewCat)
                }
            }

            override fun onFailure(
                call: Call<List<Cat>?>?,
                t: Throwable?
            ) {
            }
        })

        buttonLike.setOnClickListener {

            if (currentIndex < catList!!.size - 1) {
                currentIndex += 1

            } else {
                currentIndex = 0
            }
            favouritesList.add(catList!![currentIndex - 1])
            realm.beginTransaction()

            realm.copyToRealmOrUpdate(favouritesList)
            realm.commitTransaction()

            Glide
                .with(this)
                .load(catList!![currentIndex].url)
                .into(imageViewCat)
        }

        buttonDislike.setOnClickListener {
            if (currentIndex < catList!!.size - 1) {
                currentIndex += 1

            } else {
                currentIndex = 0
            }

            Glide
                .with(this)
                .load(catList!![currentIndex].url)
                .into(imageViewCat)
        }

        buttonFavourites.setOnClickListener {
            if (realm.isEmpty) {
                Toast.makeText(this, "Empty", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(this, FavouriteActivity::class.java)
                startActivity(intent)
            }
        }
    }
}

