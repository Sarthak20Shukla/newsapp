package com.example.newsapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.viewmodel.NewsViewModel

@Composable
fun Homescreen(viewModel: NewsViewModel = hiltViewModel()) {
    val res = viewModel.articles.value


    if (res.isLoading) {
        Box(modifier = Modifier.fillMaxSize())
        {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

    if (res.error.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = res.error, modifier = Modifier.align(Alignment.Center))
        }

    }


    res.data?.let {
        LazyColumn {
            items(it) {
                ArticleItem(it)
            }
        }

    }


}

@Composable
fun ArticleItem(it: Article) {

    Column(modifier = Modifier) {

        Image(
            painter = rememberImagePainter(data = it.urlToImage), contentDescription = null,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .shadow(10.dp,shape = RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop

        )

        Text(
            text = it.title, style = androidx.compose.ui.text.TextStyle(color = Color.Gray,
                fontWeight = FontWeight.SemiBold, fontSize = 20.sp
            ),
            modifier = Modifier.padding(start = 12.dp, top = 12.dp, end = 12.dp, bottom = 5.dp)
        )
        Text(
                text = it.author, style = androidx.compose.ui.text.TextStyle(color = Color.Gray,
        fontWeight = FontWeight.Light, fontSize = 15.sp
        ),
        modifier = Modifier.padding(start = 12.dp, bottom = 12.dp)
        )
        Spacer(modifier=Modifier.height(12.dp))

    }


}

