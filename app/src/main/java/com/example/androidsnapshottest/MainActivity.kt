package com.example.androidsnapshottest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.androidsnapshottest.component.ProfileCard
import com.example.androidsnapshottest.ui.theme.AndroidSnapShotTestTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidSnapShotTestTheme {
                Column {
                    MainScreen(mainViewModel = mainViewModel)
                }
            }
        }
    }
}


@Composable
fun MainScreen(mainViewModel: MainViewModel) {

    val viewState by mainViewModel.viewState.collectAsStateWithLifecycle()

    Spacer(modifier = Modifier.height(8.dp))

    when (viewState) {
        is ViewState.Loading -> LoadingComponent()
        is ViewState.Success -> {
            val model = (viewState as? ViewState.Success)?.profile
            ProfileCard(
                name = model?.name.orEmpty(),
                country = model?.country.orEmpty(),
                profileImageUrl = model?.profileImageUrl,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "More Options"
                    )
                },
                onProfileClick = {
                },
                onMoreIconClick = {
                }
            )
        }

        is ViewState.Error -> ErrorComponent((viewState as? ViewState.Error)?.message.orEmpty())
    }

}

@Composable
fun ErrorComponent(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Error: $message", color = Color.Red)
    }
}

@Composable
fun LoadingComponent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
