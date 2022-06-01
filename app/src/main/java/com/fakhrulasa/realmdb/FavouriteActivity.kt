package com.fakhrulasa.realmdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_detail.*

class FavouriteActivity : AppCompatActivity() {

    private lateinit var realm: Realm

    private lateinit var favouritesList: ArrayList<Cat>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.let {
            it.title = ""
            it.setDisplayHomeAsUpEnabled(true)
        }


        realm = Realm.getDefaultInstance()

        favouritesList = ArrayList()
        val results: RealmResults<Cat> =
            realm.where(Cat::class.java).findAll()

        favouritesList.addAll(results)

        recyclerViewCat.layoutManager = LinearLayoutManager(this)
        recyclerViewCat.adapter = CatListAdapter(object : CatActionListener {
            override fun onCatLikes() {
                Toast.makeText(this@FavouriteActivity, "Cat", Toast.LENGTH_SHORT).show()
            }
        }, favouritesList)
    }


}