package com.example.officialcocktailapp

import Drink
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import fetchCocktails
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@Composable
fun MainScreen(navController: NavController) {
    Screenmenu()
}


@Preview
@Composable
fun Screenmenu(){
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )
    val scope = rememberCoroutineScope()

    val gesturesEnabled = drawerState.isOpen

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet (
                modifier = Modifier.width(300.dp)
            ){
                DrawerContent()
            }
        },
        gesturesEnabled = gesturesEnabled  //per controllare il gesto di swap per aprire il menu
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    onOpenDrawer = {
                        scope.launch {
                            drawerState.apply{
                                if (isClosed) open() else close()

                            }
                        }
                    }
                )
            }
        ) {  padding ->
            ScreenContent(modifier = Modifier.padding(padding))
        }

    }
}

@Composable
fun DrawerContent(modifier: Modifier = Modifier) {

    /*Image(
        painter = painterResource(R.drawable.ic_launcher_background),
        contentDescription = null
    )*/
    Text(
        text = "App Name",
        fontSize = 24.sp,
        modifier = Modifier.padding(16.dp)
    )

    HorizontalDivider()

    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = Icons.Rounded.AccountCircle,
                contentDescription = "Account",
                modifier = Modifier.size(27.dp)

            )
        },
        label ={
            Text(
                text = "Account",
                fontSize = 17.sp,
            )
        },
        selected = false,
        onClick = {

        }
    )

    Spacer(modifier = Modifier.height(4.dp))

    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = "Notification",
                modifier = Modifier.size(27.dp)

            )
        },
        label ={
            Text(
                text = "Notification",
                fontSize = 17.sp,
                modifier = Modifier.padding(16.dp)
            )
        },
        selected = false,
        onClick = {

        }
    )

    Spacer(modifier = Modifier.height(4.dp))

    NavigationDrawerItem(
        icon = {
            Icon(
                imageVector = Icons.Rounded.Email,
                contentDescription = "Inbox",
                modifier = Modifier.size(27.dp)

            )
        },
        label ={
            Text(
                text = "Inbox",
                fontSize = 17.sp,
                modifier = Modifier.padding(16.dp)
            )
        },
        selected = false,
        onClick = {

        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var searchText by remember { mutableStateOf("") }
        var searchType by remember { mutableStateOf(SearchType.ByName) }
        var expanded by remember { mutableStateOf(false) }
        var searchPerformed by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            OutlinedTextField(
                value = searchType.label,
                onValueChange = { },
                readOnly = true,
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Dropdown"
                    )
                }
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                SearchType.values().forEach { type ->
                    DropdownMenuItem(
                        text = { Text(type.label) },
                        onClick = {
                            searchType = type
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                placeholder = { Text("Search a cocktail") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search Icon"
                    )
                },
                shape = RoundedCornerShape(16.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Go),
                keyboardActions = KeyboardActions(onGo = {
                    searchPerformed = true
                    println("Search with: $searchText, type: $searchType")
                })
            )

            Button(
                onClick = {
                    searchPerformed = true
                    println("Search with: $searchText, type: $searchType")
                },
                modifier = Modifier.height(56.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Go")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (searchPerformed) {
            var p: String = ""
            when (SearchType.ByName.label) {
                "By Name" -> p = "s"
                "By Ingredient" -> p = "f"
                "By First Letter" -> p = "i"
                else -> println("Non hai detto né ciao né mondo.")
            }
            printDrinkResults(p, searchText)
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Placeholder Image",
                modifier = Modifier.size(200.dp)
            )
        }
    }
}

@Composable
fun printDrinkResults(p: String, searchText: String) {
    var drinks by remember { mutableStateOf<List<Drink>?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(p, searchText) {
        try {
            drinks = fetchCocktails(p, searchText)
            Log.d("drinks", drinks.toString())
            errorMessage = null
        } catch (e: Exception) {
            errorMessage = "Error fetching cocktails: ${e.message}"
            drinks = null
        }
    }

    Column {
        if (errorMessage != null) {
            Text(text = errorMessage!!)
        } else if (drinks == null) {
            Text(text = "Loading...")
        } else if (drinks!!.isEmpty()) {
            Text(text = "No cocktails found.")
        } else {
            LazyColumn {
                items(drinks!!) { drink ->
                    // Usa l'operatore "safe call"" per evitare NullPointerException
                    ItemDrinkClick(drink)
                }
            }
        }
    }
}

@Composable
fun ItemDrinkClick(drink: Drink,) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {  }
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Immagine del drink
            AsyncImage(
                model = drink.imageUrl,
                contentDescription = "Drink Image",
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 8.dp)
            )

            // Informazioni drink
            Column {
                Text(
                    text = drink.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                drink.category?.let {
                    Text(text = it, fontSize = 14.sp, color = Color.Gray)
                }
            }
        }
    }
}

enum class SearchType(val label: String) {
    ByName("By Name"),
    ByIngredient("By Ingredient"),
    ByFirstLetter("By First Letter")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onOpenDrawer: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(0.6f)
        ),
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
                    .size(28.dp)
                    .clickable {
                        onOpenDrawer()
                    }
            )
        },
        title = {

            Text(text = "Cocktail App", modifier = Modifier.padding(start = 8.dp))

        },
        actions = {

            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Menu",
                modifier = Modifier
                    .size(30.dp)

            )

            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Menu",
                modifier = Modifier
                    .padding(start = 8.dp, end = 16.dp)
                    .size(30.dp)

            )
        }

    )

}