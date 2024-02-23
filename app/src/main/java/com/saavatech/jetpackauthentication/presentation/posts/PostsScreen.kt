package com.saavatech.jetpackauthentication.presentation.posts

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.saavatech.jetpackauthentication.DestinationsNavigator
import com.saavatech.jetpackauthentication.common.ApiState
import com.saavatech.jetpackauthentication.common.UiEvents
import com.saavatech.jetpackauthentication.data.remote.response.PostsResponse
import com.saavatech.jetpackauthentication.presentation.PostViewModel
import timber.log.Timber

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PostsScreen(
    navController: DestinationsNavigator,
    viewModel: PostViewModel = hiltViewModel()
){

//    val postsData: List<PostsResponse> by viewModel.postsList.value.

    val postState = viewModel.postsState.value
    val postsData by viewModel.posts.collectAsState()
    val scope = rememberCoroutineScope()
    
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
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
                   Text(text = post.body)
                }
            }

        }
    }

}