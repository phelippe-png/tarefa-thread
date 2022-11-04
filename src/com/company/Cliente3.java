package com.company;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente3 {
    public final static int PORTA = 8347;
    //Deixamos fora porque várias threads vão usar o Socket
    public static Socket socket = null;

    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", PORTA);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter saida = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            Scanner scan = new Scanner(System.in);
            String mensagem = "mensagem";
            String nomeCliente = null;

            //Cria uma thread para o cliente
            ClientThread clientThread = new ClientThread(socket);

            //Iniciar a thread
            clientThread.start();

            //Ler entrada do usuário enquanto ele está conectado
//            while(){
//
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
