package com.orbitalsonic.adsinrecylerviewdiffutil


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.orbitalsonic.adsinrecylerviewdiffutil.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mAdapter: AdapterCountry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        createRecyclerView()
    }

    private fun createRecyclerView() {
        mAdapter = AdapterCountry()
        binding.recyclerview.adapter = mAdapter
        binding.recyclerview.layoutManager = GridLayoutManager(this, 1)
        mAdapter.submitList(dataProvider())

        mAdapter.setOnItemClickListener(object : OnCountryItemClickListener {
            override fun onItemClick(position: Int) {
                showMessage(mAdapter.currentList[position].countryName)
            }

        })

    }

    private fun dataProvider():ArrayList<CountryItem>{
        val mList:ArrayList<CountryItem> = ArrayList()
        mList.add(CountryItem("+61","Australia",R.drawable.australia_flag,1))
        mList.add(CountryItem("+55","Brazil",R.drawable.brazil_flag,1))
        mList.add(CountryItem("+1","Canada",R.drawable.canada_flag,1))
        mList.add(CountryItem("Ads","Ads",R.drawable.canada_flag,2))
        mList.add(CountryItem("+86","China",R.drawable.china_flag,1))
        mList.add(CountryItem("+49","Germany",R.drawable.germany_flag,1))
        mList.add(CountryItem("+91","India",R.drawable.india_flag,1))
        mList.add(CountryItem("Ads","Ads",R.drawable.canada_flag,2))
        mList.add(CountryItem("+39","Italy",R.drawable.italy_flag,1))
        mList.add(CountryItem("+52","Mexico",R.drawable.mexico_flag,1))
        mList.add(CountryItem("+31","Netherlands",R.drawable.netherlands_flag,1))
        mList.add(CountryItem("Ads","Ads",R.drawable.canada_flag,2))
        mList.add(CountryItem("+47","Norway",R.drawable.norway_flag,1))
        mList.add(CountryItem("+92","Pakistan",R.drawable.pakistan_flag,1))
        mList.add(CountryItem("+34","Spain",R.drawable.spain_flag,1))
        mList.add(CountryItem("Ads","Ads",R.drawable.canada_flag,2))
        mList.add(CountryItem("+41","Switzerland",R.drawable.switzerland_flag,1))
        mList.add(CountryItem("+90","Turkey",R.drawable.turkey_flag,1))
        mList.add(CountryItem("+44","United Kingdom",R.drawable.united_kingdom_flag,1))
        mList.add(CountryItem("Ads","Ads",R.drawable.canada_flag,2))
        mList.add(CountryItem("+1","United States",R.drawable.united_states_flag,1))


        return mList

    }

    private fun showMessage(message:String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }


}