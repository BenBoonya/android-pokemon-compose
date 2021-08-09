package com.benboonya.pokemoninfo.berries

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.benboonya.pokemoninfo.berries.ui.list.BerryListViewModel
import com.benboonya.pokemoninfo.common.getBerryDetailRoute
import com.benboonya.pokemoninfo.common.ui.PaginatedList
import com.benboonya.pokemoninfo.drawer.TopBar

@Composable
fun BerryScreen(
    openDrawer: () -> Unit,
    viewModel: BerryListViewModel,
    navController: NavController,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Berry",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )

        PaginatedList(
            viewModel.berryListResult
        ) {
            navController.navigate(getBerryDetailRoute(it))
        }
    }
}