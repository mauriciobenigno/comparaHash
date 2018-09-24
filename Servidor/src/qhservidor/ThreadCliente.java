/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qhservidor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MauricioBenigno
 */
public class ThreadCliente extends Thread{
    Socket meuSocket;
    DataInputStream entrada;
    //SocketInputStream entrada;
    DataOutputStream saida;
    String hashSenha;
    int conectID;
    byte[] bytesArquivo;
    
    ThreadCliente(Socket novaConexao, String hashSenha, int conectID) throws Exception {
        meuSocket = novaConexao;
        //entrada = (DataInputStream) meuSocket.getInputStream();
        //saida = (DataOutputStream) meuSocket.getOutputStream();
        InputStream is = meuSocket.getInputStream();
        OutputStream os = meuSocket.getOutputStream();
        entrada = new DataInputStream(new BufferedInputStream(is));
        saida =  new DataOutputStream(new BufferedOutputStream(os));
        // controle de Arquivo
        this.hashSenha=hashSenha;
        this.conectID=conectID;
        String textoConectado = entrada.readUTF();
        System.out.println(textoConectado);
    }

    /*ThreadCliente(Socket novaConexao) {
        throw new UnsupportedOperationException("Not supported yet TESTE."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
    @Override
    public void run() {
       
       try {
           
            // -- [ Enviar Hash Original ] --
            
            saida.writeUTF(hashSenha);
            
            // -- [ Enviar ID ] --
            saida.writeInt(conectID);
            
            // -- [ Enviar Arquivo de Hashes ] --
            
            File arquivo = new File("part"+Integer.toString(conectID)+".txt");
            int tamanhoArquivo = (int) arquivo.length();
            System.out.println("Enviado arquivo de (Bytes): " + tamanhoArquivo);
            FileInputStream arquivoS = new FileInputStream(arquivo);
            byte[] bytesArquivo = new byte[tamanhoArquivo];
            arquivoS.read(bytesArquivo);
            arquivoS.close();
            
            //Protocolo: Servidor envia o tamanho do arquivo
            saida.writeInt(bytesArquivo.length);
            //Servidor envia os bytes do arquivo
            saida.write(bytesArquivo);
            
            // -- [ Receber Resposta ] --
            String resposta = entrada.readUTF();
            String resposta2 = entrada.readUTF();
            System.out.println(resposta+"\n"+resposta2);
            int resul = entrada.readInt();
            
            meuSocket.close();
            

        } 
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
