package com.example.androidinvaders

import android.R.attr.text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidinvaders.ui.theme.AndroidInvadersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidInvadersTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    gameOver(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun androidInvaders(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Painel superior (Score e Lives)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "SCORE: 0050",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Row(){
                Text(
                    text = "LIVES: ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                repeat(3){
                    AndroidEnemy(
                        color = Color.Green,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        }

        //Área Central (Inimigo Android)
        val enemyColors = listOf(Color.Green, Color.Red, Color.Blue, Color.Yellow, Color.Green)  //Lista de cores dos enemies
        Box(
            modifier = Modifier.weight(1f), // Ocupa o espaço disponível no centro
            contentAlignment = Alignment.TopCenter
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                enemyColors.forEach { color ->   //Laco de repeticao para correr dentro da variavel e fazer o implemento da cor a cada volta
                    AndroidEnemy(
                        color = color,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
        }

        //Botão Inferior (Press Start)
        pressStart()
    }
}

@Composable
fun pressStart(
        modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(bottom = 32.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(Color.Gray)
            .padding(horizontal = 24.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "PRESS START",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            color = Color.White,
            textAlign = TextAlign.Center,
            letterSpacing = 4.sp
        )
    }
}

@Composable
fun AndroidEnemy(
    color: Color,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_launcher_foreground),
        colorFilter = ColorFilter.tint(color),
        contentDescription = "Android Enemy"
    )
}

@Composable
fun gameOver(modifier: Modifier = Modifier){
    val enemyColors = listOf(Color.Green, Color.Red, Color.Blue, Color.Yellow)  //Lista de cores dos enemies
    Box(
        modifier = modifier.fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
        ) {
        //Inimigos
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            enemyColors.forEach { color ->   //Laco de repeticao para correr dentro da variavel e fazer o implemento da cor a cada volta
                AndroidEnemy(
                    color = color,
                    modifier = Modifier.size(50.dp)
                )
            }
        }
        //Perdeu
        Text(
            text = "Game over",
            color = Color(0xFFFFFFFFF),
            fontSize = 60.sp,
            fontWeight = FontWeight.W700
        )
    }
}