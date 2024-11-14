package com.example.kotlinwithsqllite
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlinwithsqllite.ui.theme.KotlinWithSQLliteTheme
import com.example.kotlinwithsqllite.database.UserDbHelper
import com.example.kotlinwithsqllite.database.UserController

class MainActivity : AppCompatActivity() {
    private lateinit var userController: UserController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userController = UserController(this)

        // Example usage
        userController.createUser("Alice", 22)
        userController.updateUser(1, "Alice Johnson", 23)
        userController.getUser(1)
        userController.deleteUser(1)
    }
}
/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinWithSQLliteTheme {
        Greeting("Android")
    }
}*/