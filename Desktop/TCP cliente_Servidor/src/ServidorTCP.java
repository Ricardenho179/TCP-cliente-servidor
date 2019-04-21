
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.plaf.synth.SynthSeparatorUI;

/**
 *
 * @author ramao
 */
public class ServidorTCP {

	public static void main(String args[]) {
		ServerSocket server = null;
		try {
			// Criando o serversocket que ficará escutando a porta 12345
			// Startando Servidor.
			server = new ServerSocket(12345);
			System.out.println("Servidor iniciado na porta 12345");

			// Espera na porta por uma conexão com o cliente
			while (true) {
				Socket cliente = server.accept();
				// vai receber os clientes independete do servidor
				new GerenciadorDeClientes(cliente);
				// Gera um socket que representa a conexão com o cliente
				// System.out.println("Cliente conectado do IP " +
				// cliente.getInetAddress().getHostAddress());

				// Recebendo mensagem do cliente a partir da "entrada" criada
				// String mensagemRecebida = entrada.readLine();
				// }
				// encerrando conexões
				// entrada.close();
				// saida.close();
				// cliente.close();
				// server.close();
			}
		} catch (IOException ex) {
			Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);

			// comunica em vermelho
			System.err.println("A porta ta ocupada ou o serv foi fechado");
			try {
				if (server != null) {
					server.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			ex.printStackTrace();

		}

	}
}
