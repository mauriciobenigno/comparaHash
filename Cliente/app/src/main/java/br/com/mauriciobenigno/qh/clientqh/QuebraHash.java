package br.com.mauriciobenigno.qh.clientqh;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class QuebraHash {


    Context context;
    String hashOriginal;

    // -- [ SOCKET ] --
    Socket conexaoServidor;
    DataInputStream entrada;
    DataOutputStream saida;
    // -- [ INFORMAÇÕES SERVIDOR ] --
    int connectID;
    boolean palavraEncontrada;

    public QuebraHash(Context context) {
        this.context=context;
        palavraEncontrada=false;
    }


    public boolean conectaServidor(String campoIP,int campoPORTA)
    {
//        int teste = Integer.getInteger(campoPORTA);
        //Toast.makeText(context,"TESTE",Toast.LENGTH_SHORT).show();
        // -- [ PREPARANDO SOCKET ] --
        try {
            conexaoServidor = new Socket(campoIP, campoPORTA);

            if(conexaoServidor.isConnected()==false)
            {
                Toast.makeText(context,"Não foi possível conectar ao servidor",Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(context,"Conectado com sucesso!",Toast.LENGTH_SHORT).show();
            entrada = new DataInputStream(conexaoServidor.getInputStream());
            saida = new DataOutputStream(conexaoServidor.getOutputStream());
            Log.d("CONNECTADO","Conectado ao servidor");

            saida.writeUTF("Dispositivo "+getDeviceName()+" conectado!");

            // -- [ Informações recebidas do Servidor ] --
            hashOriginal = entrada.readUTF();
            connectID = entrada.readInt();


            //Protocolo: Server informa primeiro o tamanho do arquivo a ser recebido
            int tamanho = entrada.readInt();

            //Cliente aloca espaço para receber o arquivo
            byte[] bytesArquivo = new byte[tamanho];

            //Cliente lê os bytes do arquivo
            entrada.readFully(bytesArquivo);

            //FileOutputStream arquivo = new FileOutputStream(new File("palavras.txt"));
            //FileOutputStream arquivo = context.openFileOutput("palavras.txt",Context.MODE_PRIVATE);
            File arq = new File(context.getCacheDir(),"palavras.txt");
            FileOutputStream arquivo = new FileOutputStream(arq);
            arquivo.write(bytesArquivo);
            arquivo.close();

            // -- [ Textos dos Logs ] --
            palavraEncontrada=false;



        }catch(IOException e)
        {
            //textoLog.setText("Não foi possivíl conectar ao servidor! ("+e.getMessage()+")");
            Log.d("ERROSV",e.getMessage());
            return false;

        }
        return true;
    }

    public String verificaArquivoTextos()
    {
        //LEITURA DO ARQUIVO

        BufferedReader rd = null;

        try {
            //rd = new BufferedReader(new FileReader("palavras.txt"));
            File arq = new File(context.getCacheDir(),"palavras.txt");
            rd = new BufferedReader(new InputStreamReader(new FileInputStream(arq)));

            String linha;
            while ((linha = rd.readLine()) != null) {
                MessageDigest m = MessageDigest.getInstance("SHA1");
                m.update(linha.getBytes(),0 ,linha.length());
                String hashSenha = new BigInteger(1, m.digest()).toString(16);
                //Log.d("TXTHASH","CID: "+connectID+" Palavra: "+linha+" Hash:"+hashSenha);
                if(hashOriginal.equals(hashSenha))
                {
                    Log.d("ENCONTRADO","As mensagens foram encontradas");
                    palavraEncontrada=true;
                    saida.writeUTF("Dispositivo "+getDeviceName()+" ENCONTROU A SENHA!");
                    saida.writeUTF("ARQUIVO: parte "+connectID+"");
                    saida.writeInt(1);

                    return "[SENHA ENCONTRADA] \npalavra: "+linha+"\nHash: "+hashSenha;
                }
            }
        } catch (FileNotFoundException e) {
            Log.d("ERROSLOAD", e.getMessage());
        } catch (IOException e) {
            Log.d("ERROSLOAD", e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            Log.d("ERROSLOAD", e.getMessage());
        }
        try {
            saida.writeUTF("Dispositivo "+getDeviceName()+" não encontrou a senha!");
            saida.writeUTF("ARQUIVO: parte "+connectID+"");
            saida.writeInt(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Senha não encontrada!";
    }


    // ---- [ CAPTAR NOME DO DISPOSITIVO ] ----
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }
        return phrase.toString();
    }

}
