package com.benboonya.pokemoninfo

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.benboonya.pokemoninfo.berries.BerryDetailScreen
import com.benboonya.pokemoninfo.berries.BerryDetailView
import com.benboonya.pokemoninfo.berries.BerryScreen
import com.benboonya.pokemoninfo.berries.ui.detail.BerryDetailViewModel
import com.benboonya.pokemoninfo.berries.ui.list.BerryListViewModel
import com.benboonya.pokemoninfo.common.BERRY_DETAIL
import com.benboonya.pokemoninfo.common.POKEMON_DETAIL
import com.benboonya.pokemoninfo.common.ui.accent
import com.benboonya.pokemoninfo.common.ui.primary
import com.benboonya.pokemoninfo.common.ui.primaryDark
import com.benboonya.pokemoninfo.drawer.Drawer
import com.benboonya.pokemoninfo.drawer.DrawerScreens
import com.benboonya.pokemoninfo.pokemon.PokemonDetailScreen
import com.benboonya.pokemoninfo.pokemon.PokemonScreen
import com.benboonya.pokemoninfo.pokemon.ui.detail.PokemonDetailViewModel
import com.benboonya.pokemoninfo.pokemon.ui.list.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ComposeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colors = lightColors(
                    primary = primary,
                    primaryVariant = primaryDark,
                    secondary = accent,
                    background = Color.White
                )
            ) {
                MainScreen()
            }
        }
    }

    @Composable
    fun MainScreen() {
        val navController = rememberNavController()
        Surface {
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            val openDrawer = {
                scope.launch {
                    drawerState.open()
                }
            }

            ModalDrawer(
                drawerState = drawerState,
                gesturesEnabled = drawerState.isOpen,
                drawerContent = {
                    Drawer(onDestinationClicked = { route ->
                        scope.launch {
                            drawerState.close()
                        }
                        navController.navigate(route) {
                            popUpTo("pokemon")
                            launchSingleTop = true
                        }
                    }
                    )
                }
            ) {

                NavHost(
                    navController = navController,
                    startDestination = DrawerScreens.Pokemon.route
                ) {
                    composable(DrawerScreens.Pokemon.route) {
                        val viewModel = hiltViewModel<PokemonListViewModel>()
                        PokemonScreen(
                            openDrawer = {
                                openDrawer()
                            },
                            viewModel,
                            navController,
                        )
                    }
                    composable(POKEMON_DETAIL) { backStackEntry ->
                        backStackEntry.arguments?.getString("url")?.let {
                            val viewModel = hiltViewModel<PokemonDetailViewModel>()
                            PokemonDetailScreen(
                                viewModel = viewModel,
                                navController = navController,
                                url = it,
                            )
                        }
                    }
                    composable(DrawerScreens.Berry.route) {
                        val viewModel = hiltViewModel<BerryListViewModel>()
                        BerryScreen(
                            openDrawer = {
                                openDrawer()
                            },
                            viewModel,
                            navController,
                        )
                    }
                    composable(BERRY_DETAIL) { backStackEntry ->
                        backStackEntry.arguments?.getString("url")?.let {
                            val viewModel = hiltViewModel<BerryDetailViewModel>()
                            BerryDetailScreen(
                                viewModel = viewModel,
                                navController = navController,
                                url = it
                            )
                        }
                    }
                }
            }
        }
    }
}