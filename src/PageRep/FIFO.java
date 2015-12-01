package PageRep;

import java.util.ArrayList;
import java.util.Arrays;

public class FIFO {
	
	public static void run(int frame_size, ArrayList<Integer> list_fifo){

		int timer			= 0;

		int fault 			= 0;
		int frames[]		= new int[frame_size];
		int selected_frame  = 0;
		
		

		// inicializa os frames com valor nulo;
		for (int i=0; i < frames.length; i++)
			frames[i] = -1;
		
		
		// percorre a lista de entrada
		for (int s : list_fifo) {

			timer++;

			int     page   = s;
			boolean insert = true;
						
			// compara o elemento a ser inserido com os que estão no quadro de memória
			// se o elemento já estiver na lista (hit), saltar para o próximo elemento da entrada
			for (int i = 0; i < frames.length; i++) {
				if (page == frames[i]){
					insert = false;
					break;
				}
			}	
				
			// caso o elemento não se encontre no quadro de memória (fault),
			// devemos inseri-lo na proxima posição disponivel, ou substituir o primeiro elemento
			if (insert) {

				frames[selected_frame] = page;
				selected_frame++;
				
				// array circular
				if (selected_frame == frames.length)
					selected_frame = 0;

				fault++;
			}

			// exibir saida a cada iteração
			//System.out.println(timer + "\t" + Arrays.toString(frames));

		}
		
		System.out.println("FIFO " + fault);

	}	
}
