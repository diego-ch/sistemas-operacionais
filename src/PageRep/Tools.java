package PageRep;

import java.util.ArrayList;
import java.util.Scanner;

public class Tools {

	
	// info de uso
	public static void printUsage() {
		System.out.println("\nUso:\tjava PageRep.Main arquivo.txt\n\tjava PageRep.Main < arquivo.txt");
	}
	
	
	// método leitor de entrada
	// copia os dados do STDIN ou de um Arquivo e retorna para o programa um ArrayList com os dados
	public static ArrayList<String> getInput(Scanner input){
		
		ArrayList<String> list = new ArrayList<String> ();

		if (input.hasNextLine()) {
	        while (input.hasNext()) {
	            list.add(Integer.toString(input.nextInt()));
	        }
		} else {
			System.err.println("pagerep: erro: lista de processos vazia");
			return null;
		}
		return list;
	}
	
	
}
