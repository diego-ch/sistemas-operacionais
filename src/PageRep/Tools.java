package PageRep;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Tools {

	
	// info de uso
	public static void printUsage() {
		System.out.println("\nUso:\tjava PageRep.Main arquivo.txt\n\tjava PageRep.Main < arquivo.txt");
	}
	
	
	
	// leitor de entrada
	public static ArrayList<String> getInput(Scanner input){
		
		ArrayList<String> list = new ArrayList<String> ();

		// passando arquivo/stdin para a memória		
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
