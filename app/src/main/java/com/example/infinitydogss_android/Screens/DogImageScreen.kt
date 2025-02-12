package com.example.infinitydogss_android.Screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.infinitydogss_android.ViewModels.DogUiState
import com.example.infinitydogss_android.ViewModels.DogViewModel

@Composable
fun DogImage(modifier: Modifier = Modifier, dogViewModel: DogViewModel) {
    // Observe the UI state from the ViewModel as Compose State.
    val uiState by dogViewModel.uiState.collectAsStateWithLifecycle()
    // Get the Android Context.
    val context = LocalContext.current

    // Use a Column to layout the content vertically.
    Column(
        modifier.fillMaxSize()
            .background(Color.Black)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Content area: uses weight(1f) to take up remaining space.
        Box(
            modifier
                .weight(1f)
                .fillMaxWidth()
            ,
            contentAlignment = Alignment.Center
        ) {
            when (uiState) {
                is DogUiState.Loading -> {
                    // Loading state: show a progress indicator.
                    CircularProgressIndicator()
                }
                is DogUiState.Success -> {
                    // Success state: extract the Dog object and display its image.
                    val dog = (uiState as DogUiState.Success).dog
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(dog.imageLink) // Provide the image URL.
                            .crossfade(true)
                            .build(),
                        contentDescription = "Dog image",
                        modifier.fillMaxSize()
                    )
                }
                is DogUiState.Error -> {
                    // Error state: extract error message.
                    val errorMessage = (uiState as DogUiState.Error).message
                    // Optionally show a Toast when the error changes.
                    LaunchedEffect(errorMessage) {
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                    // Show the error message centered.
                    Text(text = errorMessage)
                }
            }
        }
        // Fixed Button area at the bottom.
        Button(
            onClick = { dogViewModel.fetchDogImage() },
            modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Next Dog")
        }
    }
}
