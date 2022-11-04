package com.company;

import java.io.*;
import java.net.Socket;

public class Cliente2 {
    public final static int PORTA = 8347;

    public static void main(String[] args){
        Socket s = null;
        BufferedReader entrada;
        PrintWriter saida;
        BufferedReader teclado; //Armazena o que o cliente digitar
        String mensagem;
        Boolean fim = false;

        try {
            s = new Socket("localhost", PORTA);

            entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
            saida = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
            teclado = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Conectado ao servidor: " + s.getInetAddress() + ":" + s.getPort());

            //Lê a primeira resposta do servidor
            String resposta = entrada.readLine();
            System.out.println("Servidor> " + resposta);

            //Envia mensagens para o servidor até digitar 'Fim'

            while(true) {
                System.out.println("Cliente> ");
                System.out.flush();

                mensagem = teclado.readLine();

                if (mensagem.equals("Fim")){
                    fim = true;
                }

                //Envia a mensagem ao servidor
                saida.println(mensagem);
                saida.flush();

                //Lê a resposta do servidor
                //Caso seja vazia, a conexão foi encerrada
                //Caso contrario, imprime a mensagem enviada
                mensagem = entrada.readLine();

                if (mensagem == null) {
                    System.out.println("Conexão encerrada pelo servidor!");
                    break;
                }

                if (fim) {
                    break;
                }

                System.out.println("Servidor> " + mensagem);
            }

            entrada.close();
            saida.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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