package com.orbitalsonic.adsinrecylerviewdiffutil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.orbitalsonic.adsinrecylerviewdiffutil.databinding.ItemAdmobAdsBinding
import com.orbitalsonic.adsinrecylerviewdiffutil.databinding.ItemCountryBinding

class AdapterCountry :
    ListAdapter<CountryItem, RecyclerView.ViewHolder>(DATA_COMPARATOR) {

    private var mListener: OnCountryItemClickListener? = null

    fun setOnItemClickListener(listener: OnCountryItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val layoutInflater = LayoutInflater.from(parent.context)
        viewHolder = when (viewType) {
            1 -> {
                val binding: ItemCountryBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_country, parent, false)
                CountryViewHolder(binding)
            }
            else -> {
                val binding: ItemAdmobAdsBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_admob_ads, parent, false)
                ViewHolderAdMob(binding)
            }
        }
        return viewHolder

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = getItem(position)
        when (holder.itemViewType) {
            1 -> {
                val viewHolder = holder as CountryViewHolder
                viewHolder.bind(currentItem)
            }
            else -> {
                val viewHolder = holder as ViewHolderAdMob
                viewHolder.bind()
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position] != null) 1 else 2
    }

    inner class CountryViewHolder(val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.item.setOnClickListener {
                val mPosition = adapterPosition
                mListener?.onItemClick(mPosition)
            }
        }

        fun bind(mCurrentItem: CountryItem) {
            binding.countryItemData = mCurrentItem
        }

    }

    inner class ViewHolderAdMob(val binding: ItemAdmobAdsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
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