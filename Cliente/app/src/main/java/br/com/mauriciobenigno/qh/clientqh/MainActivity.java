package br.com.mauriciobenigno.qh.clientqh;

import android.content.DialogInterface;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {


    // -- [ BOTÕES ] --
    Button btConectar;

    // -- [ ENTRADA ] --
    EditText campoIP;
    EditText campoPORTA;

    // -- [ TEXTOS ] --
    TextView textoStatus;
    TextView textoLog;

    // -- [ OBJETO ] --
    QuebraHash quebraHash;

    // -- [ ALERTA ] --
    private AlertDialog.Builder aviso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // -- [ LINK ENTRADA DE DADOS ] --
        campoIP = (EditText)findViewById(R.id.campoIP);
        campoPORTA = (EditText)findViewById(R.id.campoPorta);

        // -- [ LINK BOTÕES ] --
        btConectar = (Button)findViewById(R.id.btConectar);

        // -- [ TEXTOS EDITAVEIS ] --
        //textoStatus = (TextView)findViewById(R.id.txtStatus);
        textoLog = (TextView)findViewById(R.id.txtLog);

        // -- [ OBJETO ] --
        quebraHash = new QuebraHash(getApplicationContext());


        // Programando Botão
        btConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    quebraHash.conectaServidor(campoIP.getText().toString(),Integer.parseInt(campoPORTA.getText().toString()));

                    textoLog.setText("Arquivo recebido, iniciando\nverificação!" +
                                    "\nHash: "+quebraHash.hashOriginal);

                    String result = "\n"+quebraHash.verificaArquivoTextos();
                    textoLog.setText(textoLog.getText().toString()+result);
                    if(quebraHash.palavraEncontrada)
                    {
                        aviso = new AlertDialog.Builder(MainActivity.this);
                        aviso.setTitle("SENHA ENCONTRADA");
                        aviso.setMessage(result);

                        aviso.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),"Descontado do servidor",Toast.LENGTH_SHORT).show();
                                try {
                                    quebraHash.conexaoServidor.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                finishAffinity();
                            }
                        });

                        aviso.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                btConectar.setEnabled(false);
                            }
                        });

                        aviso.create();
                        aviso.show();
                    }
            }
        });
    }
}
