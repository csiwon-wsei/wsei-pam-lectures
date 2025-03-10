package pl.wsei.pam.lectures.lecture7

import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import androidx.activity.*
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.wsei.pam.lectures.R
import pl.wsei.pam.lectures.lecture7.dagger.DaggerNamesService
import pl.wsei.pam.lectures.lecture7.dagger.NamesService
import pl.wsei.pam.lectures.lecture7.ui.theme.LecturesTheme as LecturesTheme1


class Lecture07Activity : ComponentActivity() {
    lateinit var service: NamesService
    lateinit var names: List<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        service = DaggerNamesService.create()
        names = List(10) {
            service.names().getName()
        }
        setContent {
            LecturesTheme1 {
                Scaffold(
                    bottomBar = { BottomNavigation() }
                ) {
                    HomeScreen(Modifier.padding(it))
                }
            }
        }
    }

    @Composable
    fun HomeScreen(modifier: Modifier = Modifier) {
        Column(
            modifier
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(16.dp))
            SearchBar(Modifier.padding(horizontal = 16.dp))
            HomeSection(title = "Home") {
                AlignBodyElement("Adam")
            }
            HomeSection(title = "List") {
                AlignRow()
            }
            Spacer(Modifier.height(16.dp))
        }
    }
    @Composable
    private fun BottomNavigation(modifier: Modifier = Modifier) {
        NavigationBar(
            modifier = modifier
        ) {
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = "Settings"
                    )
                },
                selected = true,
                onClick = {}
            )
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = "Profile"
                    )
                },
                selected = false,
                onClick = {}
            )
        }
    }

    @Composable
    fun Main() {
        SearchBar()
    }


    @Composable
    fun AlignRow(
        modifier: Modifier = Modifier
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = modifier
        ) {
            items(items = names){ item ->
                AlignBodyElement(item)
            }
        }
    }


    @Composable
    fun HomeSection(
        title: String,
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit
    ) {
        Column(modifier) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                    .padding(horizontal = 16.dp)
            )
            content()
        }
    }

    @Composable
    fun CardItem(
        @DrawableRes drawable: Int,
        @StringRes text: Int,
        modifier: Modifier = Modifier
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = modifier
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.width(255.dp)
            ) {
                Image(
                    painter = painterResource(drawable),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(80.dp)
                )
                Text(
                    text = stringResource(text),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }


    @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
    @Composable
    fun FavoriteCollectionCardPreview() {
        LecturesTheme1 {
            CardItem(
                text = R.string.place_holder,
                drawable = R.drawable.wsei_logo_svg,
                modifier = Modifier.padding(8.dp)
            )
        }
    }

    @Composable
    fun SearchBar(modifier: Modifier = Modifier) {
        TextField(
            value = "",
            onValueChange = {},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface
            ),
            placeholder = {
                Text(stringResource(R.string.place_holder))
            },
            modifier = modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)
        )
    }

    @Preview(showBackground = true, backgroundColor = 0x115577)
    @Composable
    fun PreviewAlignBodyElement() {
        LecturesTheme1 {
            AlignBodyElement(
                text = service.names().getName()
            )
        }
    }

    @Composable
    fun AlignBodyElement(
        text: String,
        modifier: Modifier = Modifier
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
            )
            Text(
                text = text,
                modifier = Modifier
                    .paddingFromBaseline(top = 24.dp, bottom = 8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        LecturesTheme1 {
            Surface {
                Main()
            }
        }
    }
}


