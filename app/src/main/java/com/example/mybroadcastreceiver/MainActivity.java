package com.example.mybroadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mybroadcastreceiver.Receiver.MyReceiverLlamadas;

public class MainActivity extends AppCompatActivity {

    MyReceiverLlamadas myReceiverLlamadas;
    Button btn;
    EditText editTextMensaje, editTextNumero;
    static String mensaje, numeroRegistrado;
    private static final String TAG = "Llamar";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btnGuardar);
        editTextMensaje = findViewById(R.id.txtMensaje);
        editTextNumero = findViewById(R.id.txtnumero);

        IntentFilter iFilter = new IntentFilter();
        myReceiverLlamadas = new MyReceiverLlamadas();
        registerReceiver(myReceiverLlamadas,iFilter);

        btn.setOnClickListener(v ->
        {
            Toast.makeText(getApplicationContext(), "Mensaje y Número guardados correctamente", Toast.LENGTH_LONG).show();
            mensaje = editTextMensaje.getText().toString();
            numeroRegistrado = editTextNumero.getText().toString();
        });

    }
    private void enviarMensaje(String numero, String mensaje){
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(numero, null, mensaje,null ,null);


    }

    public void enviarMensajeLlamada(String numero){
        Log.d(TAG, "sendSMSAnswer:" + numeroRegistrado);
        Log.d(TAG, "sendSMSAnswer: " + numero);
        Log.d(TAG, "sendSMSAnswer: " + mensaje);
        if (numero.equals("+52"+numeroRegistrado)||numero.equals(numeroRegistrado)){
            enviarMensaje(numero,mensaje);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiverLlamadas);
    }
}