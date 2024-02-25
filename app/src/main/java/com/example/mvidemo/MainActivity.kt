package com.example.mvidemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.mvidemo.ui.page.login.LoginActivity
import com.example.mvidemo.ui.theme.MVIDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVIDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MainView("Android")
                }
            }
        }
    }
}

@Composable
fun MainView(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column {
        Text(
            text = "Hello $name!", modifier = modifier
        )
        Button(onClick = {
            context.startActivity(Intent(context,LoginActivity::class.java))
        }) {
            Text(text = "跳转原生页面")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MainViewPreview() {
    MVIDemoTheme {
        MainView("Android Preview")
    }
}