package com.saavatech.jetpackauthentication.presentation.posts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.saavatech.jetpackauthentication.DestinationsNavigator
import com.saavatech.jetpackauthentication.data.remote.response.PostsResponse
import com.saavatech.jetpackauthentication.presentation.PostViewModel

@Composable

fun PostsScreen(
    navController: DestinationsNavigator,
    viewModel: PostViewModel = hiltViewModel()
){

    val postState = viewModel.postsState.value
    val postsData = viewModel.postsListState.value
    
    Scaffold {contentPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
//                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            if (postState.isLoading){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            Spacer(modifier = Modifier.height(64.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Hello Again!",
                fontSize = 26.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Welcome Back you've been missed",
                fontSize = 19.sp,
                color = Color.Black,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))

            LazyColumn(contentPadding = PaddingValues(16.dp)) {
                items(postsData) { post ->
                   Post(post = post)
                }
            }

        }
    }
}

@Composable
fun Post(post: PostsResponse){
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(post.image),
                contentDescription =null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(text = post.title)
            Text(text = post.body)
        }

    }
}