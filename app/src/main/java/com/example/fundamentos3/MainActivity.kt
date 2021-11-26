package com.example.fundamentos3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fundamentos3.ui.theme.Fundamentos3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Fundamentos3Theme {
                // A surface container using the 'background' color from the theme
                //De la forma que esta configurado la funcion, no se puede acceder a la variable
                MyApp()
            }
        }
    }
}
//Creamos una nueva funcion llamada Greetings y pasaremos el contenido de Myapp a esta

@Composable
fun MyApp() {
    /*
    if(shouldShowOnboarding){
        OnboardingScreen()
    }else{
        Greetings()
    }*/
    //Creamos el state y lo compartimos entre los componentes
    var shouldShowOnboarding by remember{ mutableStateOf(true)}
    if(shouldShowOnboarding){
        OnboardingScreen(onContinueClicked = {shouldShowOnboarding=false})
    }else{
        Greetings()
    }
}

@Composable
private fun Greetings(names:List<String> = listOf("World", "Compose")) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        for (name in names) {
            Greeting(name = name)
        }
    }}

@Composable
fun Greeting(name: String) {
    //Por cada llamada a esta función se crea un estado independiente, similar a las variables de clases
    val expanded = remember { mutableStateOf(false) }
    val extrapadding = if (expanded.value) 48.dp else 0.dp
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        //Para colocar un elemento al final de una fila usaremos pesos, por lo que fillMaxWidth()
        //en el modifier sobra.
        //El peso hará que sea flexible y tienda a usar todo el espacio
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extrapadding)
            ) {
                Text("Hello, ")
                Text(name)
            }
            OutlinedButton(onClick = { expanded.value = !expanded.value }) {
                Text(if (expanded.value) "Show less" else "Show more")
            }
        }
    }
}

//State hoisting
@Composable
fun OnboardingScreen(onContinueClicked:()->Unit) {
    //Haremos state hoisting
    //var shouldShowOnboarding by remember { mutableStateOf(true) }
    Surface() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Bienvenido a fundamentos de Jetpack Compose")
            Button(
                onClick = onContinueClicked,
                modifier = Modifier.padding(vertical = 24.dp)
            ) {
                Text(text = "Continuar")
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    Fundamentos3Theme {
        MyApp()
    }
}