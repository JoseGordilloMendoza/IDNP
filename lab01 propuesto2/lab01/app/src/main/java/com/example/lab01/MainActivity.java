package com.example.lab01;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText editNombre, editApellido, editCorreo, editTelefono, editGrupoSanguineo;
    private Button btnGuardar, btnLeer;
    private static final String FILE_NAME = "asistentes.txt";
    private static final String TAG = "ConferenciaApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNombre = findViewById(R.id.editNombre);
        editApellido = findViewById(R.id.editApellido);
        editCorreo = findViewById(R.id.editCorreo);
        editTelefono = findViewById(R.id.editTelefono);
        editGrupoSanguineo = findViewById(R.id.editGrupoSanguineo);

        btnGuardar = findViewById(R.id.btnGuardar);
        btnLeer = findViewById(R.id.btnLeer);

        // Guardar datos en archivo
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = editNombre.getText().toString() + "," +
                        editApellido.getText().toString() + "," +
                        editCorreo.getText().toString() + "," +
                        editTelefono.getText().toString() + "," +
                        editGrupoSanguineo.getText().toString() + "\n";

                try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_APPEND)) {
                    fos.write(data.getBytes());
                    Toast.makeText(MainActivity.this, "Datos guardados", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Error al guardar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Leer datos del archivo
        btnLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try (FileInputStream fis = openFileInput(FILE_NAME)) {
                    int ch;
                    StringBuilder sb = new StringBuilder();
                    while ((ch = fis.read()) != -1) {
                        sb.append((char) ch);
                    }
                    String[] registros = sb.toString().split("\n");
                    for (String r : registros) {
                        Log.d(TAG, "Asistente: " + r);
                    }
                    Toast.makeText(MainActivity.this, "Datos mostrados en Logcat", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "No hay datos registrados", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
