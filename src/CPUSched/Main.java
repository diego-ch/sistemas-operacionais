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

	public static final boolean verbose = true;
	
	public static void  printUsage() {
		System.out.println("\n\nUso:\tjava CPUSched.Main arquivo.txt\n\tjava CPUSched.Main < arquivo.txt");
	}
	
	
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
				
				if (verbose){
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
			
			if (verbose){
				System.out.println("cpusched: entrada padrão selecionada");
			}
			inputlist = new Scanner(System.in);
			
		}
		

		Process process;
		int pid=0, burstTime, submissionTime;
		LinkedList<Process> process_list = new LinkedList();
		
		// passando arquivo/stdin para a memória		
		if (inputlist.hasNextLine()) {
			if (verbose){
				System.out.println("cpusched: lendo lista de processos...");
			}
	        while (inputlist.hasNext()) {
	            submissionTime = inputlist.nextInt();
	            burstTime = inputlist.nextInt();
	            process = new Process(pid, submissionTime, burstTime);
	            process_list.add(process);
	            pid++;
	        }
	        
		} else {
			System.err.println("cpusched: erro: lista de processos vazia");
			return;
		}
		
		inputlist.close();
		
		if (verbose) {
			for (int i = 0; i < process_list.size(); i++)
				System.out.println("\t"+process_list.get(i).toString());
		}
		
		
		if (verbose){
			System.out.println("cpusched: finalizado");
		}
		return;
		

	}

}
