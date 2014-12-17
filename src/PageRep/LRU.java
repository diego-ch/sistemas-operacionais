package PageRep;

import java.util.ArrayList;
import java.util.Arrays;

public class LRU {

	public static void run(ArrayList<String> list){
		
		// clonando a lista
		@SuppressWarnings("unchecked")
		ArrayList<String> listLRU = (ArrayList<String>) list.clone();
		
		// o primeiro elemento da lista é o numero de quadros de memória disponíveis na RAM
		// removemos ele da lista e deixamos somente as referências as páginas.
		int quadros = Integer.parseInt(listLRU.remove(0));
		
		
		System.out.println("LRU "  + quadros + " " + Arrays.toString(listLRU.toArray()));
	}
	
}
