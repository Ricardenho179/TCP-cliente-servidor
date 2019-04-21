
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ramao
 */
public class ClienteTCP {

	public static void main(String args[]) {

		try {
			// Criando socket com endere√ßo e porta do processo servidor
			final Socket cliente = new Socket("127.0.0.1", 12345);

			new Thread() {
				public void run() {
					try {
						// Criando bufferedreader para possibilitar a leitura de dados a partir do
						// cliente
						BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
					
						while(true){
							String msg = entrada.readLine();
							System.out.println("Servidor:" + msg);
						}
						
						
						
					
					} catch (IOException e) {
						System.out.println("N„o da pra ler a mensagem do servidor");
						e.printStackTrace();
					}
				};
			}.start();
			
			// Criando printwriter para possibilitar o envio de dados para o servidor
			PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true); // Passa o OutputStream do
																					// socket para o
																					// PrintWriter.
			
			BufferedReader leitorTerminal = new BufferedReader(new InputStreamReader(System.in));
			//Para que n„o de null na resposta final do servidor.
			String mensagemTerminal = "";
			while(true) {
				mensagemTerminal = leitorTerminal.readLine();
				if(mensagemTerminal == null || mensagemTerminal.trim().length() == 0) {
					continue;
				}
				// Enviando o dado para o servidor a partir da saida criado.
				saida.println(mensagemTerminal);
				if(mensagemTerminal.equals("/sair")) {
					System.exit(0);
				}
					
				
			}
			
			
			
			
			
			
			
		
			
			
			// encerrando conex√µes
//			entrada.close();
//			saida.close();
//			cliente.close();
		
		} catch (IOException ex) {
			Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("EndereÁo pode estar inv·lido ou");
			System.out.println("O servidor pode estar fora do ar.");
		}
	}

}
