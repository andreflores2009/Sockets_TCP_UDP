/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex4_inverte_string_socket_udp;

import java.net.*;
import java.util.Scanner;
/**
 *
 *  
 */

/*4) Utilizando Sockets UDP, desenvolva um aplicação Servidora que receba uma String de um cliente, inverta a mesma, e devolva o 
resultado para o cliente. O servidor deve estar sempre pronto para receber novas mensagens. O servidor precisará descobrir, através 
do pacote enviado pelo cliente, o seu endereço ip + porta, para que seja possível retornar o resultado para o cliente.*/

public class Cliente {
        public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            // Cria um socket UDP
            socket = new DatagramSocket();

            // Endereço IP e porta do servidor
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345;  // Porta do servidor

            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite uma mensagem para o servidor: ");
            String mensagem = scanner.nextLine();

            // Converte a mensagem para bytes
            byte[] sendData = mensagem.getBytes();

            // Cria um pacote para enviar dados para o servidor
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);

            // Envia a mensagem para o servidor
            socket.send(sendPacket);

            // Prepara o pacote para receber a resposta do servidor
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Recebe a resposta do servidor
            socket.receive(receivePacket);

            // Converte a resposta para string
            String respostaServidor = new String(receivePacket.getData(), 0, receivePacket.getLength());

            System.out.println("Resposta do servidor: " + respostaServidor);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}

