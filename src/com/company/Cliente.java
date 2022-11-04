package com.company;
import java.io.*;
import java.net.Socket;

public class Cliente {
    // Definimos uma constante com a porta para comunicação
    public static final int PORTA = 8347;

    public static void main(String[] args) {
        Socket s = null; // Variável que armazena o socket
        BufferedReader entrada; // Armazenar o que vem do servidor
        PrintWriter saida; // Armazenar o que será enviado ao servidor

        try {
            // Cria o socket no IP localhost (mesma máquina) e porta pré-definida
            // Estabelecemos a tupla IP:porta como localhost:8347
            s = new Socket("localhost",PORTA);

            // Cria streams de entrada e saída
            // Configuramos o BufferedReader para os dados vindos pelo socket
            // estabelecido
            entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
            // Configuramos o PrintWriter para armazenar os dados que enviaremos
            // ao servidor via socket
            saida = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));

            // Informar o status da conexão (no formato da tupla ip:porta)
            System.out.println("Conectado ao servidor "+s.getInetAddress()+"" + ":"+s.getPort());

            //Escrever uma mensagem para o servidor
            saida.println("E aí bro, servidor!");
            saida.flush();

            //Ler a resposta do servidor
            String resposta = entrada.readLine();
            System.out.println("Servidor: " + resposta);

            entrada.close();
            saida.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //fecha o socket
            try {
                if (s != null){
                    s.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}