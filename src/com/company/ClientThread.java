package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

//Essa classe cria uma thread para o cliente usando Socket
public class ClientThread extends Thread {
    private Socket socket;
    BufferedReader entrada;

    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
        entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while(true){
                //Obter a resposta do servidor e exibir
                String resposta = entrada.readLine();
                System.out.println(resposta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //finalizar aqui
        }
    }
}
