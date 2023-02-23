package com.vipulasri.jetinstagram.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.vipulasri.jetinstagram.R
import com.vipulasri.jetinstagram.data.PostsRepository
import com.vipulasri.jetinstagram.ui.theme.JetInstagramTheme


val posts by PostsRepository.posts

@Preview
@Composable
fun Favorites() {
    Scaffold(topBar = { composeTopBar()}){
        Column{
            composeTextView()
            SearchBar()
            AlignUserElements()
        }
    }
}

@Preview
@Composable
private fun composeTopBar() {
    Surface(color = Color.White) {
        TopAppBar(
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(
                        text = "Favorites",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .size(500.dp)
                            .wrapContentSize()
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.Clear,
                        contentDescription = "Clear Btn")
                }
            },
            actions = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add User"
                    )
                }
            },

            backgroundColor = Color.Transparent,
            contentColor = Color.Black,
        )
    }
}

@Preview
@Composable
private fun composeTextView(
) {
    Surface(
        color = Color.LightGray,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(){
            Text(
                text = buildAnnotatedString {
                append(stringResource(R.string.disclaimer))
                addStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold
                    ),
                    start = 98,
                    end = 110
                )
            },
            modifier = Modifier.padding(14.dp),
            fontSize = 18.sp)

        }

    }
}

@Preview
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .border(BorderStroke(width = 1.dp, color = Color.LightGray),
            shape = RoundedCornerShape(50))
) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White
        ),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 3.dp)
            .border(BorderStroke(width = 1.dp, color = Color.White),
                shape = RoundedCornerShape(80))
    )
}


@Composable
fun UserElement(
    imageURL: String,
    name: String,
    username: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(imageURL),
            contentDescription =  null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Column(
            modifier = Modifier.weight(1f)
        ){
            Text( text = name)
            Text( text = username)
        }
        Button( onClick = {}
            , modifier = Modifier.padding(4.dp)
        ) {
            Text(text = "Add")
        }
    }
}

@Preview
@Composable
fun AlignUserElements(
){
    LazyColumn {
        items(posts){ item ->
            UserElement(item.image, item.user.name, item.user.username)
        }
    }
}
@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun AlignUserElementsPreview() {
    JetInstagramTheme() {
        AlignUserElements()
    }
}