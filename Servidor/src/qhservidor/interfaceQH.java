/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qhservidor;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MauricioBenigno
 */

public class interfaceQH extends javax.swing.JFrame {

    /**
     * Creates new form interfaceQH
     */
    
    // -- [ ARQUIVO ] --
    
    //String allSenhas;
    StringBuilder allSenhas;
    //LinkedList todasSenhas;  
    Vector todasSenhas;
    
    // -- [ DIVISÃO ] --
    int contador;
    int numDivisao;
    int quantPalavrasDiv;
    int clienteID;
    
    // -- [ SENHA ] --
    String hashSenha;
    
    // -- [ ENVIO ARQUIVO ] --
    
    
    
    public interfaceQH() {
        initComponents();
        
        // -- [ Inicializando variáveis ] --
        allSenhas = new StringBuilder();
        contador = 0;
        //todasSenhas = new LinkedList();
        todasSenhas = new Vector();
        txtStatus.setText("Status: Offline");
        clienteID = 0;
        
        
        try {
            txtIP.setText("IP: "+InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException ex) {
            Logger.getLogger(interfaceQH.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        campoPorta = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        campoLogServidor = new javax.swing.JTextArea();
        btArquivo = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 140), new java.awt.Dimension(0, 140), new java.awt.Dimension(32767, 140));
        btEscolheSenha = new javax.swing.JButton();
        btDividir = new javax.swing.JButton();
        campoNumDivisao = new javax.swing.JTextField();
        txtIP = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        campoLogConfiguracoes = new javax.swing.JTextArea();
        btIniciarServidor = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JLabel();
        txtSenha = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        campoPorta.setText("Porta");

        campoLogServidor.setColumns(20);
        campoLogServidor.setRows(5);
        jScrollPane1.setViewportView(campoLogServidor);

        btArquivo.setText("Abrir arquivo");
        btArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btArquivoActionPerformed(evt);
            }
        });

        btEscolheSenha.setText("Sortear Senha");
        btEscolheSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEscolheSenhaActionPerformed(evt);
            }
        });

        btDividir.setText("Dividir Arquivo");
        btDividir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDividirActionPerformed(evt);
            }
        });

        campoNumDivisao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNumDivisaoActionPerformed(evt);
            }
        });

        txtIP.setText("IP: 127.0.0.1: ");

        campoLogConfiguracoes.setColumns(20);
        campoLogConfiguracoes.setRows(5);
        jScrollPane2.setViewportView(campoLogConfiguracoes);

        btIniciarServidor.setText("Iniciar Servidor");
        btIniciarServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIniciarServidorActionPerformed(evt);
            }
        });

        jLabel5.setText("Log configurações");

        jLabel6.setText("Log servidor");

        txtStatus.setText("Status:  Online ");

        txtSenha.setText("Senha: Ainda não encontrada");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(31, 31, 31)
                        .addComponent(btArquivo)
                        .addGap(26, 26, 26)
                        .addComponent(btEscolheSenha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(campoNumDivisao, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btDividir))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(23, 23, 23)
                                .addComponent(txtStatus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSenha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(campoPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btIniciarServidor))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(campoNumDivisao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btDividir)
                            .addComponent(btArquivo)
                            .addComponent(btEscolheSenha))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btIniciarServidor)
                            .addComponent(txtIP)
                            .addComponent(txtStatus)
                            .addComponent(txtSenha)
                            .addComponent(jLabel6))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoNumDivisaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNumDivisaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNumDivisaoActionPerformed

    private void btIniciarServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIniciarServidorActionPerformed

        try {
            // TODO add your handling code here:
            
            ServerSocket serv = new ServerSocket(Integer.parseInt(campoPorta.getText()));
            txtStatus.setText("Status: Online");
            //while (true) 
            //while (contador<numDivisao) 
            while (true)     
            { 
                Socket novaConexao = serv.accept();
                campoLogServidor.setText(campoLogServidor.getText()+"Servidor iniciado.\n");
                ThreadCliente novaThread;
                try {
                    novaThread = new ThreadCliente(novaConexao,hashSenha,clienteID);
                    novaThread.start();
                    clienteID++;
                    campoLogServidor.setText(campoLogServidor.getText()+"Cliente conectado.\n");
                    txtStatus.setText("Status: Online");
                } catch (Exception ex) {
                    Logger.getLogger(interfaceQH.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        } catch (IOException ex) {
            txtStatus.setText("Status: Offline");
            Logger.getLogger(interfaceQH.class.getName()).log(Level.SEVERE, null, ex);
        }
        //servidor.run();
        

    }//GEN-LAST:event_btIniciarServidorActionPerformed

    private void btArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btArquivoActionPerformed
        // TODO add your handling code here:
         try {
        
            //LEITURA DO ARQUIVO
            BufferedReader rd = new BufferedReader(new FileReader("palavras.txt"));
                
            String linha;
            while ((linha = rd.readLine()) != null) {
                allSenhas.append(linha+"\n");
                todasSenhas.add(linha);
                contador++;
            }
            
            
            //campoLogConfiguracoes.setText(allSenhas.toString());
            
            campoLogConfiguracoes.setText("Quantidade de palavras: "+Integer.toString(contador));
            
            
            
        }
        catch (IOException ex) {
                Logger.getLogger(interfaceQH.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_btArquivoActionPerformed

    private void btDividirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDividirActionPerformed
        // TODO add your handling code here:
        
        numDivisao = Integer.parseInt(campoNumDivisao.getText());
        quantPalavrasDiv = contador/numDivisao;
        campoLogConfiguracoes.setText(campoLogConfiguracoes.getText()+"\nDividido em "+numDivisao+" arquivos\ncada um com "+quantPalavrasDiv+" palavras;");
        
        try {
            /* Criação dos arquivos */
            int k = 0;
            for(int i = 0; i < numDivisao; i++)
            {
                File arquivo = new File("part"+Integer.toString(i)+".txt");
                if(!arquivo.exists())
                    arquivo.createNewFile();

                //substitue o conteúdo do arquivo
                FileWriter fw = new FileWriter( arquivo );
                // variavel para manipulação do arquivo
                BufferedWriter bw = new BufferedWriter( fw );

                // adiciona conteúdo ao arquivo
                for(int j = 0; j< quantPalavrasDiv;j++)
                {
                    bw.write(todasSenhas.get(j+k).toString()+"\r\n");
                    //bw.newLine();
                } 
                k+=quantPalavrasDiv;
            }
        }
        catch (IOException ex) {
                Logger.getLogger(interfaceQH.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btDividirActionPerformed

    private void btEscolheSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEscolheSenhaActionPerformed
        try {
            // TODO add your handling code here:
            
            Random numero = new Random();
            int num = numero.nextInt(contador)-1;
            
            String palavraEscolhida = todasSenhas.get(num).toString();
            //String palavraEscolhida = todasSenhas.get(num).toString();
            
            MessageDigest m = MessageDigest.getInstance("SHA1");
            m.update(palavraEscolhida.getBytes(),0 ,palavraEscolhida.length());
            hashSenha = new BigInteger(1, m.digest()).toString(16);
            campoLogConfiguracoes.setText(campoLogConfiguracoes.getText()+"\nPalavra escolhida: "+palavraEscolhida+
                    "\nHash (SHA1): "+hashSenha);
                    //"\nHash (SHA1): "+new BigInteger(1, m.digest()).toString(6));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(interfaceQH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btEscolheSenhaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(interfaceQH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(interfaceQH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(interfaceQH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(interfaceQH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new interfaceQH().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btArquivo;
    private javax.swing.JButton btDividir;
    private javax.swing.JButton btEscolheSenha;
    private javax.swing.JButton btIniciarServidor;
    private javax.swing.JTextArea campoLogConfiguracoes;
    private javax.swing.JTextArea campoLogServidor;
    private javax.swing.JTextField campoNumDivisao;
    private javax.swing.JTextField campoPorta;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel txtIP;
    private javax.swing.JLabel txtSenha;
    private javax.swing.JLabel txtStatus;
    // End of variables declaration//GEN-END:variables
}
