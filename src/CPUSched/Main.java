/*
 * Diego Porto
 * Universidade Federal da Paraíba
 * 
 */

package CPUSched;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static final int verbose = 0;
	
	public static void  printUsage() {
		System.out.println("\n\nUso:\tjava CPUSched.Main arquivo.txt\n\tjava CPUSched.Main < arquivo.txt");
	}
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		Scanner inputlist = null;

		// finaliza se mais de um argumento for passado
		if (args.length > 1) {
			System.err.println("cpusched: erro: mais de um argumento definido.");
			printUsage();
			return;
		}
		
		// ler arquivo passado como argumento 
		if (args.length > 0 && args.length == 1) {
			try {
			
				
				File inputfile = new File(args[0]);
				
				if (verbose > 1){
					System.out.println("cpusched: arquivo '" + inputfile.getName() + "' selecionado");
				}
				
				inputlist = new Scanner(inputfile);
			
			} catch (FileNotFoundException e) {
				System.err.println("cpusched: erro: arquivo não encontrado");
				return;
			}
		}
		
		// ler da entrada padrão se não forem passados argumentos
		if (args.length == 0) {
			
			if (verbose > 1){
				System.out.println("cpusched: entrada padrão selecionada");
			}
			inputlist = new Scanner(System.in);
			
		}
		
		int pid=0, burstTime, submissionTime;
		LinkedList<Process> process_list = new LinkedList();
		
		// passando arquivo/stdin para a memória		
		if (inputlist.hasNextLine()) {
			if (verbose > 1){
				System.out.println("cpusched: lendo lista de processos...");
			}
	        while (inputlist.hasNext()) {
	            submissionTime = inputlist.nextInt();
	            burstTime = inputlist.nextInt();
	            process_list.add(new Process(pid, submissionTime, burstTime));
	            pid++;
	        }
	        
		} else {
			System.err.println("cpusched: erro: lista de processos vazia");
			return;
		}
		
		inputlist.close();
		
		// exibe a lista de processos dada como entrada
		if (verbose > 1) {
			for (int i = 0; i < process_list.size(); i++)
				System.out.println("\t"+process_list.get(i).toString());
		}
		
		
		// executa os métodos de escalonamento
		Schedulers.runFCFS(process_list);
		
		
		if (verbose > 1){
			System.out.println("cpusched: finalizado");
		}
		return;
		

	}

}
