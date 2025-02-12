package com.example.infinitydogss_android

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Scaffold

import androidx.compose.ui.Modifier
import com.example.infinitydogss_android.Screens.DogImage
import com.example.infinitydogss_android.ViewModels.DogViewModel

import com.example.infinitydogss_android.ui.theme.InfinityDogss_androidTheme


class MainActivity : ComponentActivity() {

    private val dogViewModel: DogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InfinityDogss_androidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DogImage(modifier = Modifier.padding(innerPadding) , dogViewModel = dogViewModel)
                }
            }
        }
    }

}

