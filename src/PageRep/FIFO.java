package PageRep;

import java.util.ArrayList;
import java.util.Arrays;

public class FIFO {
	
	
	public static void run(ArrayList<String> list){
		
		// clonando a lista
		@SuppressWarnings("unchecked")
		ArrayList<String> listFIFO = (ArrayList<String>) list.clone();
		
		
		// o primeiro elemento da lista é o numero de quadros de memória disponíveis na RAM
		// removemos ele da lista e deixamos somente as referências as páginas.
		int frame_size = Integer.parseInt(listFIFO.remove(0));
		int frames[]   = new int[frame_size];

		// variaveis de apoio
		int count = 0;
		int next  = 0;
		
		// percorre a lista de entrada
		for (String s : listFIFO) {

			int     page   = Integer.parseInt(s);
			boolean insert = true;
						
			// compara o elemento a ser inserido com os que estão no quadro de memória
			// se o elemento já estiver na lista, saltar para o próximo elemento da entrada
			for (int i = 0; i < frames.length; i++) {
				if (page == frames[i]){
					insert = false;
					break;
				}
			}	
				
			// caso o elemento não se encontre no quadro de memória,
			// devemos inseri-lo na proxima posição disponivel, ou substituir o primeiro elemento
			if (insert) {
				
				frames[next] = page;
				next++;
				count++;
				
				// array circular
				if (next == frames.length)
					next = 0;
			}
		}
		
		System.out.println("\nFIFO " + count);

	}	
}
