package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProcessosControler {
	
	public ProcessosControler() {
		
		super();
		
	}
	
	//Retorna o SO que está em execução na máquina
	public String os() {
		String os = System.getProperty("os.name");
		String arch = System.getProperty("os.arch");
		String version = System.getProperty("os.version");
		return os + " - vers " + version + " - arch " + arch ;
	}
	
	public void callProcess(String process) {
		//Processo Java p/ chamar outro processo, classe Runtime
		try {
			Runtime.getRuntime().exec(process);
		} catch (Exception e) {  
//			e.printStackTrace(); //Coloca o print do erro e encerra
			String msgErro = e.getMessage();
//			System.err.println(msgError); 
			if (msgErro.contains("740")) {
				//cmd /c caminho_do_processo
				StringBuffer buffer = new StringBuffer();
				buffer.append("cmd /c");
				buffer.append(" ");
				buffer.append(process);
				try {
					Runtime.getRuntime().exec(buffer.toString());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				System.err.println(msgErro); //SE a msg não for = 740
			}
		}
	}
	
	public void readProcess(String process) {
		try {
			Process p = Runtime.getRuntime().exec(process);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo); //Converte pra string
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void killProcess(String param) {
		String cmdPid = "TASKKILL /PID";
		String cmdNome = "TASKKILL /IM";
		int pid = 0;
		StringBuffer buffer = new StringBuffer();
		
		//NumberFormatException -> Exception
		try {
		//TASKKILL /PID XXXX
		pid = Integer.parseInt(param);
		buffer.append(cmdPid);
		buffer.append(" ");
		buffer.append(pid);
		
	} catch (NumberFormatException e) {
		//TASKKILL /IM nomeprocesso.exe
		buffer.append(cmdNome);
		buffer.append(" ");
        buffer.append(param);
	}
		callProcess(buffer.toString());
		
	}
}
