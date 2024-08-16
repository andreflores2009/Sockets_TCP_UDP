package com.socket_tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Cliente {
    public static void main(String[] args) {
        
        Scanner teclado = new Scanner(System.in);
        try {
            //Cria um socket e tenta se conectar ao servidor (socket->connect)
            Socket s = new Socket("localhost", 1234);
            System.out.println("CONECTADO AO SERVIDOR!!!\n");
            
            //Cliente envia uma mensagem ao servidor
            System.out.println("Digite a sua mensagem para enviar: ");
            String mensagem = teclado.nextLine();//lê pelo teclado
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeUTF(mensagem);//envia a mensagem
            
            //Cliente aguarda a resposta do servidor
            DataInputStream dis = new DataInputStream(s.getInputStream());
            String resposta = dis.readUTF();//cliente aguarda mensagem do servidor
            System.out.println("\nMensagem recebida do Servidor: "+resposta);
            //System.out.println("\nResposta do Servidor: "+resposta);
            
            //fecha o socket
            s.close();
        } catch (IOException ex) {
            System.out.println("Servidor não encontrado");
        }
        
    }
}
