package com.example.calc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etNumero;
    private TextView tvResultado;
    private Button btnSumar, btnRestar, btnMultiplicar, btnDividir, btnIgual, btnC;

    private double numero1 = 0;
    private double numero2 = 0;
    private String operacion = "";

    private boolean calculado = true; //En caso de que ya haya un calculo pasa a true

    ArrayList<String> listaResultado = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vincular elementos de la UI
        etNumero = findViewById(R.id.etNumero);
        tvResultado = findViewById(R.id.tvResultado);
        btnSumar = findViewById(R.id.btnSumar);
        btnRestar = findViewById(R.id.btnRestar);
        btnMultiplicar = findViewById(R.id.btnMultiplicar);
        btnDividir = findViewById(R.id.btnDividir);
        btnIgual = findViewById(R.id.btnIgual);
        btnC = findViewById(R.id.btnC);

        // Configurar botones de operaciones
        btnSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarNumeroYOperacion("+");
            }
        });

        btnRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarNumeroYOperacion("-");
            }
        });

        btnMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarNumeroYOperacion("*");
            }
        });

        btnDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarNumeroYOperacion("/");
            }
        });

        // Configurar botón de igual
        btnIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularResultado();
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BorrarLista();
            }
        });

    }

    private void BorrarLista(){
        listaResultado.clear(); //Limpia lista
        numero1 = 0; //Reinicia primer numero a 0
        calculado = false;// reiniciar calculo a false
        tvResultado.setText("Resultado: vacio"); //Reinicia resultado

    }

    private void guardarNumeroYOperacion(String op) {
        String numeroIngresado = etNumero.getText().toString();

        if (!numeroIngresado.isEmpty() || calculado) {
            if (!calculado){
                numero1 = Double.parseDouble(numeroIngresado);
            }
            operacion = op;
            etNumero.setText(""); // Limpiar el EditText para el segundo número
            calculado = false;
        } else {
            Toast.makeText(this, "Por favor ingrese un número", Toast.LENGTH_SHORT).show();
        }
    }



    private void calcularResultado() {
        String numeroIngresado = etNumero.getText().toString();
        if (!numeroIngresado.isEmpty() || calculado) {
            if (!calculado){
                numero2 = Double.parseDouble(numeroIngresado);
            }
            double resultado = 0;

            switch (operacion) {
                case "+":
                    resultado = numero1 + numero2;
                    break;
                case "-":
                    resultado = numero1 - numero2;
                    break;
                case "*":
                    resultado = numero1 * numero2;
                    break;
                case "/":
                    if (numero2 != 0) {
                        resultado = numero1 / numero2;
                    } else {
                        Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    break;

            }
            numero1 = resultado;
            calculado = true;
            listaResultado.add(String.valueOf(resultado));
            tvResultado.setText("Resultado: " + new DecimalFormat("#.##").format(resultado));
            etNumero.setText(""); // Limpiar el EditText para nuevas operaciones
        } else {
            Toast.makeText(this, "Por favor ingrese un número", Toast.LENGTH_SHORT).show();
        }
    }


}
