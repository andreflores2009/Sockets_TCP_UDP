/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex4_inverte_string_socket_udp;

import java.net.*;
import java.util.Scanner;
/**
 *
 * @author Andre
 */
/*4) Utilizando Sockets UDP, desenvolva um aplicação Servidora que receba uma String de um cliente, inverta a mesma, e devolva o resultado para o cliente.
O servidor deve estar sempre pronto para receber novas mensagens. O servidor precisará descobrir, através do pacote enviado pelo cliente, 
o seu endereço ip + porta, para que seja possível retornar o resultado para o cliente.*/

public class Servidor {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            // Cria um socket UDP na porta 12345
            socket = new DatagramSocket(12345);

            System.out.println("Servidor UDP pronto para receber mensagens...");

            while (true) {
                byte[] receiveData = new byte[1024];

                // Cria um pacote para receber dados
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Recebe o pacote do cliente
                socket.receive(receivePacket);

                // Obtém os dados do pacote
                String mensagemCliente = new String(receivePacket.getData(), 0, receivePacket.getLength());

                // Obtém o endereço IP e a porta do cliente
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Inverte a mensagem
                String mensagemInvertida = new StringBuilder(mensagemCliente).reverse().toString();

                // Converte a mensagem invertida para bytes
                byte[] sendData = mensagemInvertida.getBytes();

                // Cria um pacote para enviar dados de volta para o cliente
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);

                // Envia a mensagem invertida de volta para o cliente
                socket.send(sendPacket);

                System.out.println("Mensagem recebida do cliente: " + mensagemCliente);
                System.out.println("Mensagem invertida enviada para o cliente: " + mensagemInvertida);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
