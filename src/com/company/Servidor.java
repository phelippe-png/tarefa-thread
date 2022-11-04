package com.company;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    // Declarar constante com o valor da porta
    public final static int PORTA = 8347;

    public static void main(String[] args) {
        PrintWriter saida; // Armazena dados que vão para o cliente
        BufferedReader entrada; // Armazena dados que vem do cliente
        Socket cliente = null; // Armazena o cliente (quando ele se conectar!)

        try {
            // Estabelece a comunicação
            System.out.println("Ouvindo a porta "+PORTA+"...");
            // tenta abrir o socket na porta esbelecida
            ServerSocket servidor = new ServerSocket(PORTA);
            // Aceitar a conexão do cliente
            cliente = servidor.accept();
            System.out.println("Conexão estabelecida com "+ cliente.getInetAddress());

            // Prepara as streams de entrada e saída
            // Cria um BufferedReader baseado na informação que chegou
            // do cliente (cliente.getInputStream())
            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            // Cria um PrintWriter baseado nos dados a serem enviados ao
            // cliente (cliente.getOutputStream())
            saida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()));

            // Lê uma mensagem do cliente
            // Método readLine() serve para ler uma linha do que está
            // armazenado no BufferedReader
            String mensagem = entrada.readLine();
            System.out.println("Cliente> "+mensagem);

            // Responde ao cliente
            // Configura a mensagem que será enviada
            saida.println("Você está conectado ao servidor mais simples possível, bro.");
            // Envia a mensagem
            saida.flush();

            // Encerrar as variáveis (liberar espaço)
            entrada.close();
            saida.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Encerra a conexão (fecha o socket)
            System.out.println("Encerrando a conexão...");
            // Só encerra a conexão caso um cliente tenha se conectado
            try {
                if (cliente != null) {
                    cliente.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Conexão encerrada com sucesso.");
        }
    }
}