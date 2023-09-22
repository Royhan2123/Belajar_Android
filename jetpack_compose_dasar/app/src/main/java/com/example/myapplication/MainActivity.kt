package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                Spacer(modifier = Modifier.padding(10.dp))
                Row {
                    Text(text = "Hello World1", modifier = Modifier.padding(horizontal = 10.dp), style = MaterialTheme.typography.labelLarge)
                    Text(text = "Hello World2", modifier = Modifier.padding(horizontal = 5.dp))
                    Text(text = "Hello World3", modifier = Modifier.padding(horizontal = 5.dp))
                }
            }

            Column {
                Ininame(name = "Royhan")
            }
        }
    }
}

@Composable
fun Lambda(greeting:String){
    Text(text = "Name is $greeting")
}
@Composable
fun Ininame(name:String){
    Text(text = "nama saya adalah $name", color = Color.Red, modifier = Modifier.padding(vertical = 40.dp, horizontal = 10.dp))
}

@Preview
@Composable
fun Strings(age:Int = 12){
    Text(text = "Your age is : $age")
}

@Preview("Roy",showBackground = true)
@Composable
fun InitStrings(){
    Column()     {
        Lambda(greeting = "Royhan")
        Strings(age = 20)
    }

}