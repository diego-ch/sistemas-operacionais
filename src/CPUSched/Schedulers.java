package CPUSched;

import java.util.LinkedList;
import java.util.ListIterator;

public class Schedulers {

	// FCFS Implementation - First Come, First Served
	@SuppressWarnings("unused")
	public static void runFCFS(LinkedList<Process> processlist){

		int timer=0, count=0;
		double fcfs_turnaround = 0, fcfs_reply = 0, fcfs_awaiting = 0;

		LinkedList<Process> inputlist = (LinkedList) processlist.clone();
		ListIterator<Process> iter    = inputlist.listIterator();
		
		Process process = iter.next();
		
		while(!inputlist.isEmpty()) {

			int timerAux;

			// espera o timer alcançar o primeiro processo da lista
			if (process.getSubmissionTime() > timer) {
				if (Main.verbose > 1) {
					System.out.println("cpusched: aguardando entrada de processos");
				}
				timer++;
				continue;
			}
			
			// simula execução do primeiro processo
			process.setReplyTime(timer);
			timerAux = timer + process.getBurstTime();

			if (Main.verbose > -1) {
				System.out.println("Rodando Processo [" + process.getPID() + "] de [" + timer + "] até [" + timerAux + "]");
			}

			timer += process.getBurstTime();

			// atribui os valores de retorno/resposta/espera do processo para gerar media do algoritmo posteriormente
			fcfs_turnaround += timer - process.getSubmissionTime();
			fcfs_reply      += process.getReplyTime() - process.getSubmissionTime();
			fcfs_awaiting   += process.getReplyTime() - process.getSubmissionTime();

			if (Main.verbose > 1) {
				System.out.println("cpusched: removendo processo[" + process.getPID() + "] da lista...");
			}

			// recebe proximo processo da lista e remove o atual
			if (iter.hasNext()) {
				process = iter.next();
				iter.remove();
			} else {
				inputlist.removeFirst();
			}
			
			count++;
		}

		System.out.println("FCFS " + fcfs_turnaround/count + " " + fcfs_reply/count + " " + fcfs_awaiting/count);
	}

}
