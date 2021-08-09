package com.benboonya.pokemoninfo.common

import android.nfc.tech.MifareUltralight

object Config {
    object PagedList {
        val config = androidx.paging.PagedList.Config.Builder()
                .setInitialLoadSizeHint(MifareUltralight.PAGE_SIZE * 2)
                .setEnablePlaceholders(false)
                .setPageSize(MifareUltralight.PAGE_SIZE)
                .setPrefetchDistance(MifareUltralight.PAGE_SIZE)
                .build()
    }
}
