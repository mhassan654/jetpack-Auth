package com.saavatech.jetpackauthentication.presentation.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saavatech.jetpackauthentication.Destinations
import com.saavatech.jetpackauthentication.DestinationsNavigator
import com.saavatech.jetpackauthentication.R
import com.saavatech.jetpackauthentication.ui.theme.PurpleBg

@Composable
fun WelcomeScreen(navController: DestinationsNavigator){
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(40.dp))
                .height(378.dp)
                .fillMaxWidth(),
//            backgroundColor = PurpleBg
        ) {
            Image(
                painter = painterResource(R.drawable.image),
                contentDescription = "Welcome Screen Image",
                modifier = Modifier
                    .height(520.dp)
            )

        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Welcome to the \n Login/Register Tutorial ",
            fontSize = 26.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Just playing around with Authentication",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontWeight = FontWeight.Light
        )

        Spacer(modifier = Modifier.height(64.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            Button(
                onClick = {
                    navController.navigateTo("Register")
                },
                // Uses ButtonDefaults.ContentPadding by default
                contentPadding = PaddingValues(
                    start = 40.dp,
                    top = 12.dp,
                    end = 40.dp,
                    bottom = 12.dp
                ),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PurpleBg,
                    contentColor = Color.White)
            ) {

                Text(
                    "Register",
                    fontSize = 20.sp
                )
            }
            
            Spacer(modifier = Modifier.width(2.dp))
            Button(
                onClick = {
                    navController.navigateTo("Login")
                },
                // Uses ButtonDefaults.ContentPadding by default
                contentPadding = PaddingValues(
                    start = 40.dp,
                    top = 12.dp,
                    end = 40.dp,
                    bottom = 12.dp
                ),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PurpleBg,
                    contentColor = Color.White)
            ) {
                Text(
                    "Login",
                    fontSize = 20.sp
                )
            }
        }
    }
}