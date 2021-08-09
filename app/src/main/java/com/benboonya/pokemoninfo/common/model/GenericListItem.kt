package com.benboonya.pokemoninfo.common.model

import androidx.recyclerview.widget.DiffUtil

data class GenericListItem(
        val name: String,
        val url: String
) {
    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<GenericListItem> = object : DiffUtil.ItemCallback<GenericListItem>() {
            override fun areItemsTheSame(oldItem: GenericListItem, newItem: GenericListItem): Boolean = (oldItem.url == newItem.url)

            override fun areContentsTheSame(oldItem: GenericListItem, newItem: GenericListItem): Boolean = oldItem == newItem
        }
    }
}