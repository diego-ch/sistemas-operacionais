package PageRep;

import java.util.ArrayList;
import java.util.Arrays;

public class OTM {

	public static void run(ArrayList<String> list){
		
		// clonando a lista
		@SuppressWarnings("unchecked")
		ArrayList<String> listOTM = (ArrayList<String>) list.clone();
		
		// o primeiro elemento da lista é o numero de quadros de memória disponíveis na RAM
		// removemos ele da lista e deixamos somente as referências as páginas.
		int quadros = Integer.parseInt(listOTM.remove(0));
		
		
		System.out.println("OTM "  + quadros + " " + Arrays.toString(listOTM.toArray()));
		
	}
	
}
