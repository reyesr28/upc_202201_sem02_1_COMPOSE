package com.example.compose2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            principal()
        }
    }
}

@Composable
fun AppBar(){
    TopAppBar(title = { Text(text = "UPC - Ejercicio 2")},
        backgroundColor = Color.Blue,
        contentColor = Color.White
    )
}

@Preview
@Composable
fun principal(){

    var txtProducto by remember { mutableStateOf("") }
    var txtPrecio by remember { mutableStateOf("") }
    var txtCantidad by remember { mutableStateOf("") }
    var Subtotal by remember { mutableStateOf("0.0") }
    var Igv by remember { mutableStateOf("0.0") }
    var Total by remember { mutableStateOf("0.0") }

    //variable para el toast
    var context= LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment =  Alignment.CenterHorizontally
    ) {

        AppBar()

        Text(
            text = "Venta de Productos",
            fontSize = 32.sp,
            color= Color.Blue,
            textAlign = TextAlign.Center
        )

        OutlinedTextField(
            value=txtProducto,
            modifier= Modifier.padding(15.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label={ Text(text = "Ingrese Producto")},
            onValueChange ={
                txtProducto=it
            } )
        OutlinedTextField(
            value=txtPrecio,
            modifier= Modifier.padding(5.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label={ Text(text = "Ingrese Precio")},
            onValueChange ={
                txtPrecio=it
            })
        OutlinedTextField(
            value=txtCantidad,
            modifier= Modifier.padding(15.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label={ Text(text = "Ingrese Cantidad")},
            onValueChange ={
                txtCantidad=it
            } )
        Button(
            colors= ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
            modifier= Modifier
                .padding(20.dp)
                .width(250.dp),
            onClick = {
                Subtotal= (txtCantidad.toDouble()*txtPrecio.toDouble()).toString()
                Igv= (Subtotal.toDouble() *0.18).toString()
                Total=Subtotal+Igv

                Toast.makeText(context,"USe realizó calculo correctamente",
                        Toast.LENGTH_LONG).show()
            }
        ){
            Text(text = "Calcular Venta",color=Color.White)
        }
        Text(text = "Subtotal: ${Subtotal}",
            fontSize = 18.sp,
            modifier= Modifier
            .padding(10.dp)
            .width(250.dp),)
        Text(text = "Igv 18%: ${Igv}",
            fontSize = 18.sp,
            modifier= Modifier
                .padding(10.dp)
                .width(250.dp),)
        Text(text = "Total a Pagar: ${Total}",
            fontSize = 18.sp,
            modifier= Modifier
                .padding(10.dp)
                .width(250.dp),)
    }
}
