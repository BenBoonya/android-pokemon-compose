package com.benboonya.pokemoninfo.berries

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.benboonya.pokemoninfo.berries.model.Berry
import com.benboonya.pokemoninfo.berries.ui.detail.BerryDetailViewModel

@Composable
fun BerryDetailScreen(
    viewModel: BerryDetailViewModel,
    navController: NavController,
    url: String,
) {

    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = true)
    val berry: Berry? by viewModel.berryDetail.observeAsState()
    viewModel.getBerryDetail(url)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Berry") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                }
            )
        },
        content = {
            BerryDetailView(berry)
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator()
                }

            }
        }
    )
}

@Composable
fun BerryDetailView(berry: Berry?) {
    berry?.let {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
            ) {
                Text(text = berry.name, style = MaterialTheme.typography.h3)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Size: ${berry.size} Millimeter")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Growth Time: ${berry.growthTime} Hour")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Max harvest: ${berry.maxHarvest}")
            }
        }
    }
}