package view;

import controller.ProcessosControler;

public class Principal {

	public static void main(String[] args) {
		
		ProcessosControler processosController = new ProcessosControler();
//		String os = processosController.os();
//		System.out.println(os);
		
//		String process = "C:\\Windows\\write.exe"; //Abre um vínculo do WP com o java, ele é filho. Mas no Windows não tem processo pai e filho
//		processosController.callProcess(process); //No linux tem esse processo pai e filho
//		String process = "TASKLIST /FO TABLE"; //Funciona igualmente ao meu cmd
//		processosController.readProcess(process);
		
		String param = "36692";
		processosController.killProcess(param);
	}
	

}
