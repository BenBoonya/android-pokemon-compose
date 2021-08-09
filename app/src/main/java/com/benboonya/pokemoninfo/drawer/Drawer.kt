package com.benboonya.pokemoninfo.drawer

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.benboonya.pokemoninfo.R

sealed class DrawerScreens(val title: String, val route: String, @DrawableRes val icon: Int) {
    object Pokemon : DrawerScreens("Pokemon", "pokemon", R.drawable.ic_pikachu)
    object Berry : DrawerScreens("Berry", "berry", R.drawable.ic_razz_berry)
}

private val screens = listOf(
    DrawerScreens.Pokemon,
    DrawerScreens.Berry
)

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        screens.forEach { screen ->
            Row(
                modifier = Modifier.clickable {
                    onDestinationClicked(screen.route)
                }.padding(12.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = screen.icon),
                    contentDescription = screen.title
                )

                Spacer(modifier = modifier.width(12.dp))

                Text(
                    text = screen.title,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}

@Preview
@Composable
fun DrawerPreview() {
    Drawer(){}
}

@Composable
fun TopBar(title: String = "", buttonIcon: ImageVector, onButtonClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            IconButton(onClick = { onButtonClicked() }) {
                Icon(buttonIcon, contentDescription = "")
            }
        },
        backgroundColor = MaterialTheme.colors.primaryVariant
    )
}
