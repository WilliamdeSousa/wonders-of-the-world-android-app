package com.example.wonderoftheworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wonderoftheworld.ui.theme.WonderOfTheWorldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WonderOfTheWorldTheme {
                WondersOfTheWorldApp()
            }
        }
    }
}

class Collection(
    @StringRes val name: Int,
    val elements: Map<String, Wonder>
) {
    operator fun get(key: String): Wonder? {
        return elements[key]
    }

    @Composable
    fun Menu(
        modifier: Modifier = Modifier,
        onClickButton: () -> Unit
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            ) {
                MenuButton(
                    text = stringResource(R.string.back_button_text),
                    onClick = onClickButton
                )
                Text(
                    text = stringResource(id = name),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }

            elements.forEach { (_, value) ->
                value.Button()
            }
        }
    }

    @Composable
    fun InfoCard(elementKey: String) {
        elements[elementKey]?.InfoCard(name)
    }
}

class Wonder(
    @StringRes val name: Int,
    @StringRes val year: Int,
    @StringRes val location: Int,
    @DrawableRes val painter: Int,
    private val onClick: () -> Unit
) {
    @Composable
    fun InfoCard(@StringRes collectionName: Int) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight(.8F)
                .fillMaxWidth()

        ) {
            Text(
                text = stringResource(collectionName)
            )
            Text(
                text = stringResource(name),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 10.dp)
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10))
            ){
                Image(
                    painter = painterResource(painter),
                    contentDescription = "${stringResource(name)} image",
                    modifier = Modifier
                )
            }
            Text(
                text = stringResource(location)
            )
            Text(
                text = stringResource(year),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
        }
    }

    @Composable
    fun Button() {
        MenuButton(
            text = stringResource(name),
            onClick = onClick,
            fontSize = 22.sp,
            modifier = Modifier
                .padding(vertical = 15.dp)
                .height(55.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun WondersOfTheWorldApp() {
    var currentScreen by remember {
        mutableStateOf("initialMenu")
    }

    val wondersOfTheAncientWorldCollection = Collection(
        name = R.string.wonders_of_the_ancient_world_text,
        elements = mapOf(
            "1" to Wonder(
                name = R.string.great_pyramid_of_giza_text,
                year = R.string.great_pyramid_of_giza_year,
                location = R.string.great_pyramid_of_giza_location,
                painter = R.drawable.great_pyramid_of_giza,
                onClick = { currentScreen = "1"}
            ),
            "2" to Wonder(
                name = R.string.hanging_gardens_of_babylon_text,
                year = R.string.hanging_gardens_of_babylon_year,
                location = R.string.hanging_gardens_of_babylon_location,
                painter = R.drawable.hanging_gardens_of_babylon,
                onClick = { currentScreen = "2"}
            ),
            "3" to Wonder(
                name = R.string.statue_of_zeus_at_olympia_text,
                year = R.string.statue_of_zeus_at_olympia_year,
                location = R.string.statue_of_zeus_at_olympia_location,
                painter = R.drawable.statue_of_zeus_at_olympia,
                onClick = { currentScreen = "3"}
            ),
            "4" to Wonder(
                name = R.string.temple_of_artemis_at_ephesus_text,
                year = R.string.temple_of_artemis_at_ephesus_year,
                location = R.string.temple_of_artemis_at_ephesus_location,
                painter = R.drawable.temple_of_artemis_at_ephesus,
                onClick = { currentScreen = "4"}
            ),
            "5" to Wonder(
                name = R.string.mausoleum_at_halicarnassus_text,
                year = R.string.mausoleum_at_halicarnassus_year,
                location = R.string.mausoleum_at_halicarnassus_location,
                painter = R.drawable.mausoleum_at_halicarnassus,
                onClick = { currentScreen = "5"}
            ),
            "6" to Wonder(
                name = R.string.colossus_of_rhodes_text,
                year = R.string.colossus_of_rhodes_year,
                location = R.string.colossus_of_rhodes_location,
                painter = R.drawable.colossus_of_rhodes,
                onClick = { currentScreen = "6"}
            ),
            "7" to Wonder(
                name = R.string.lighthouse_of_alexandria_text,
                year = R.string.lighthouse_of_alexandria_year,
                location = R.string.lighthouse_of_alexandria_location,
                painter = R.drawable.lighthouse_of_alexandria,
                onClick = { currentScreen = "7"}
            )
        )
    )
    val newWondersOfTheWorldCollection = Collection(
        name = R.string.new_wonders_of_the_world_text,
        elements = mapOf(
            "8" to Wonder(
                name = R.string.great_wall_of_china_text,
                year = R.string.great_wall_of_china_year,
                location = R.string.great_wall_of_china_location,
                painter = R.drawable.great_wall_of_china,
                onClick = { currentScreen = "8" }
            ),
            "9" to Wonder(
                name = R.string.petra_text,
                year = R.string.petra_year,
                location = R.string.petra_location,
                painter = R.drawable.petra,
                onClick = { currentScreen = "9" }
            ),
            "10" to Wonder(
                name = R.string.colosseum_text,
                year = R.string.colosseum_year,
                location = R.string.colosseum_location,
                painter = R.drawable.colosseum,
                onClick = { currentScreen = "10" }
            ),
            "11" to Wonder(
                name = R.string.chichen_itz_text,
                year = R.string.chichen_itz_year,
                location = R.string.chichen_itz_location,
                painter = R.drawable.chichen_itz,
                onClick = { currentScreen = "11" }
            ),
            "12" to Wonder(
                name = R.string.machu_picchu_text,
                year = R.string.machu_picchu_year,
                location = R.string.machu_picchu_location,
                painter = R.drawable.machu_picchu,
                onClick = { currentScreen = "12" }
            ),
            "13" to Wonder(
                name = R.string.taj_mahal_text,
                year = R.string.taj_mahal_year,
                location = R.string.taj_mahal_location,
                painter = R.drawable.taj_mahal,
                onClick = { currentScreen = "12" }
            ),
            "14" to Wonder(
                name = R.string.christ_the_redeemer_text,
                year = R.string.christ_the_redeemer_year,
                location = R.string.christ_the_redeemer_location,
                painter = R.drawable.christ_the_redeemer,
                onClick = { currentScreen = "14" }
            )
        )
    )

    when (currentScreen) {
        "initialMenu" -> Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 15.dp)
        ) {
            Text(
                text = stringResource(R.string.initial_menu_app_title),
                fontSize = 50.sp,
                lineHeight = 55.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp)
            )

            InitialMenuButton(
                text = stringResource(R.string.wonders_of_the_ancient_world_text),
                onClick = { currentScreen = "ancientWorld" }
            )
            InitialMenuButton(
                text = stringResource(R.string.new_wonders_of_the_world_text),
                onClick = { currentScreen = "modernWorld" }
            )
            InitialMenuButton(
                text = stringResource(R.string.random_wonder_button_text),
                onClick = { currentScreen = (1..14).random().toString() }
            )
            Spacer(modifier = Modifier.fillMaxHeight(.4F))
            Text(
                text = stringResource(R.string.about_text),
                modifier = Modifier
                    .align(Alignment.End)
            )
        }

        "ancientWorld" -> {
            wondersOfTheAncientWorldCollection.Menu(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp, vertical = 15.dp)
            ) { currentScreen = "initialMenu" }
        }

        "modernWorld" -> {
            newWondersOfTheWorldCollection.Menu(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp, vertical = 15.dp)
            ) { currentScreen = "initialMenu" }
        }

//        "aboutTheApp" -> {
//            Column(
//                verticalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(horizontal = 10.dp, vertical = 15.dp)
//            ) {
//                Text(stringResource(R.string.about_text))
//                MenuButton(text = stringResource(R.string.back_button_text), onClick = { currentScreen = "initialMenu" })
//            }
//        }

        else -> {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp, vertical = 15.dp)
            ) {
                when (currentScreen.toInt()) {
                    in 1..7 -> wondersOfTheAncientWorldCollection.InfoCard(elementKey = currentScreen)
                    else -> newWondersOfTheWorldCollection.InfoCard(elementKey = currentScreen)
                }

                // MENU
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp, bottom = 55.dp)
                        .fillMaxWidth()
                ) {
                    MenuButton(
                        text = stringResource(R.string.previous_button_text),
                        onClick = {
                            currentScreen = if (currentScreen == "1" || currentScreen == "8")
                                (currentScreen.toInt() + 6).toString()
                            else (currentScreen.toInt() - 1).toString() }
                    )
                    MenuButton(
                        text = stringResource(R.string.initial_menu_button_text),
                        onClick = { currentScreen = "initialMenu" }
                    )
                    MenuButton(
                        text = stringResource(R.string.next_button_text),
                        onClick = { currentScreen = if (currentScreen == "7" || currentScreen == "14") (currentScreen.toInt() - 6).toString() else (currentScreen.toInt() + 1).toString() }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WonderOfTheWorldPreview() {
    WonderOfTheWorldTheme {
        WondersOfTheWorldApp()
    }
}

@Composable
fun MenuButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text = text, fontSize = fontSize)
    }
}

@Composable
fun InitialMenuButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    MenuButton(
        text = text,
        onClick = onClick,
        fontSize = 22.sp,
        modifier = modifier
            .padding(horizontal = 5.dp, vertical = 15.dp)
            .height(75.dp)
            .fillMaxWidth()
    )
}