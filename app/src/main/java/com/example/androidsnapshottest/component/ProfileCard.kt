package com.example.androidsnapshottest.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.androidsnapshottest.R

@Composable
fun ProfileCard(
    modifier: Modifier = Modifier,
    name: String,
    country: String,
    profileImageUrl: String? = null,
    icon: @Composable (() -> Unit)? = null,
    onMoreIconClick: () -> Unit,
    onProfileClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .clickable { onProfileClick.invoke() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = profileImageUrl ?: R.drawable.baseline_person_outline,
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.ic_launcher_background)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = country,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
            }
            if (icon != null) {
                IconButton(
                    onClick = onMoreIconClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                ) {
                    icon()
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewProfileCardV1() {
    ProfileCard(
        name = "John Doe",
        country = "United States",
        modifier = Modifier.fillMaxWidth(),
        profileImageUrl = "https://example.com/profile.jpg",
        icon = null,
        onProfileClick = {},
        onMoreIconClick = {}
    )
}

@Preview
@Composable
fun PreviewProfileCardV2() {
    ProfileCard(
        name = "John Doe",
        country = "United States",
        modifier = Modifier.fillMaxWidth(),
        profileImageUrl = "https://example.com/profile.jpg",
        icon = {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "More Options"
            )
        },
        onProfileClick = {},
        onMoreIconClick = {}
    )
}