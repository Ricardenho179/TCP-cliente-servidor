import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class GerenciadorDeClientes extends Thread {

	private Socket cliente;
	private String nomeCliente;
	private BufferedReader leitor;
	private PrintWriter saida;
	// chave valor, atributo imutável
	private static final Map<String, GerenciadorDeClientes> clientes = 
			new HashMap<String, GerenciadorDeClientes>();

	public GerenciadorDeClientes(Socket cliente) {
		this.cliente = cliente;
		start();
	}

	@Override
	public void run() {
		try {
			// Criando bufferedreader para possibilitar a leitura de dados a partir do
			// servidor
			leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			// Criando printwriter para possibilitar o envio de dados para o cliente
			saida = new PrintWriter(cliente.getOutputStream(), true);
			saida.println("Nickname:");
			String msg = leitor.readLine();
			this.nomeCliente = msg;
			saida.println("Seja bem-vindo " + this.nomeCliente);
			clientes.put(this.nomeCliente, this);
			
			while (true) {
				msg = leitor.readLine();
				// comando sair
				if (msg.equals("/sair")) {
					this.cliente.close();
				}  else if(msg.startsWith("/msg")) { 
				 String Nomedestinataraio = msg.substring(4, msg.length());
				 System.out.println("");
					GerenciadorDeClientes destinatario = clientes.get(Nomedestinataraio);
					if (destinatario == null) {
						saida.println("O cliente informado não existe");
					} else {
						saida.println(destinatario.getNomeCliente());
						destinatario.getSaida().println("<" + this.nomeCliente + "> " + leitor.readLine());
					}
				}
				else {
					saida.println("<" + this.nomeCliente + "> " + msg);
					
				}
			}

		} catch (

		IOException e) {
			System.out.println("o cliente encerrou a conexão...");
			e.printStackTrace();
		}
		// possivel gerador de erros
		// try cach nesse bobo
		super.run();
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public PrintWriter getSaida() {
		return saida;
	}
//	public BufferedReader getLeitor() {
//		return leitor;
//	}
}
