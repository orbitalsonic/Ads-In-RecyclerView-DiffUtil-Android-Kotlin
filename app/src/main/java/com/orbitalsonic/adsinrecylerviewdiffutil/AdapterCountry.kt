package com.orbitalsonic.adsinrecylerviewdiffutil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.orbitalsonic.adsinrecylerviewdiffutil.databinding.ItemAdmobAdsBinding
import com.orbitalsonic.adsinrecylerviewdiffutil.databinding.ItemCountryBinding

class AdapterCountry :
    ListAdapter<CountryItem, RecyclerView.ViewHolder>(DATA_COMPARATOR) {

    private var mListener: OnCountryItemClickListener? = null

    fun setOnItemClickListener(listener: OnCountryItemClickListener) {
        mListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        val layoutInflater = LayoutInflater.from(parent.context)
        when (viewType) {
            1 -> {
                val binding: ItemCountryBinding = DataBindingUtil.inflate(layoutInflater,R.layout.item_country,parent,false)
                viewHolder = CountryViewHolder(binding, mListener!!)
            }
            2 -> {
                val binding: ItemAdmobAdsBinding = DataBindingUtil.inflate(layoutInflater,R.layout.item_admob_ads,parent,false)
                viewHolder = ViewHolderAdMob(binding)
            }
        }
        return viewHolder!!

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = getItem(position)
        when (holder.itemViewType) {
            1 -> {
                val viewHolder = holder as CountryViewHolder
                viewHolder.bind(currentItem)
            }
            2 -> {
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return currentList[position].typeView
    }

    class CountryViewHolder(binding:ItemCountryBinding, listener: OnCountryItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        private val mBinding = binding
        init {

            binding.item.setOnClickListener {
                val mPosition = adapterPosition
                listener.onItemClick(mPosition)
            }

        }

        fun bind(mCurrentItem: CountryItem) {
            mBinding.countryItemData = mCurrentItem
        }

    }

    class ViewHolderAdMob(binding: ItemAdmobAdsBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            val adRequest =
                AdRequest.Builder().build()
            binding.adViewB.loadAd(adRequest)
        }
    }


    companion object {
        private val DATA_COMPARATOR = object : DiffUtil.ItemCallback<CountryItem>() {
            override fun areItemsTheSame(oldItem: CountryItem, newItem: CountryItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: CountryItem, newItem: CountryItem): Boolean {
                return oldItem.countryCode == newItem.countryCode
            }
        }
    }

}