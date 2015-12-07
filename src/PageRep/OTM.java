package PageRep;

import java.util.ArrayList;
import java.util.Arrays;

public class OTM {

	public static void run(int frame_size, ArrayList<Integer> list_otm){

		int hit		= 0;
		int fault 	= 0;
		int timer	= 0;
		

		Frame frames[]	= new Frame[frame_size];
				
		// inicializa os frames com valor nulo;
		for (int i=0; i < frames.length; i++)
			frames[i] = new Frame(-1,-1);
		
		// percorre a lista de entrada
		for (int item : list_otm) {

			timer++;

			Frame page = new Frame(item, timer);
			boolean insert = true;

			// compara o elemento a ser inserido com os que estão no quadro de memória
			// se o elemento já estiver na lista (hit), saltar para o próximo elemento da entrada
			for (int i = 0; i < frames.length; i++) {
				if (page.id == frames[i].id) {
					System.out.print("hit\t");
					frames[i].age = timer;
					insert = false;
					hit++;
					break;
				}
			}	

			// caso o elemento não se encontre no quadro de memória (fault)
			// devemos inseri-lo na proxima posição vazia
			if (insert) {

				for (int i = 0; i < frames.length; i++) {
					if (frames[i].id == -1) {
						System.out.print("empty\t");
						frames[i] = page;
						insert = false;
						fault++;
						break;
					}
				}
			}

			// caso todos os frames estejam ocupados (fault)
			// devemos substituir o elemento que vai demorar mais tempo a ser utilizado
			if (insert) {

				int count = 0;
				int	selected_frame  = 0;

				System.out.print("full");


				for (int entry : list_otm) {

					if (count >= timer && count < list_otm.size()) {


						System.out.println("\t\tpage="				+ page.id
											+ "  selected_frame="	+ selected_frame 
											+ "  value="			+ list_otm.get(count)
											+ "  count="			+ count);

						for (int i = 0; i < frames.length; i++) {
							
							if (frames[i].id == list_otm.get(count)) {
									//System.out.println("\t\t\tframes["+ i +"]=" + frames[i].id
									//						+ " list=" + list_otm.get(count));
									frames[i].found = true;
									selected_frame = i;
								}

						}

					}

					count++;
				}

				System.out.print("\t\tpage=" + page.id
								+ "\tlfu=" + frames[selected_frame] + "\tpos=" + selected_frame + "\n\t");

				if (timer == list_otm.size()) {
					for (int i = 0; i < frames.length; i++) {
						if (frames[i].age < frames[selected_frame].age)
							selected_frame = i;
					}
				}

				frames[selected_frame] = page;
				fault++;
			}

			// exibir saida a cada iteração
			System.out.println("" + timer + "\t" + Arrays.toString(frames) + "");
		}
		
		System.out.println("OTM " + fault);

	}	
}
