 CPUSched
==========
 
 Este pacote contem a implementação dos seguintes algoritmos de escalonamento de CPU:
 
  - FCFS:  First Come, First Serve;
  - SJF:   Shortest Job First;
  - RR:    Round-Robin (com quantum = 2);
  
 O programa deve iniciar lendo uma lista de processos, contendo o tempo de chegada e o tempo de execução, a partir da entrada padrão
 e imprimir na saida padrão uma tabela com as seguintes métricas:

```

Tempo de Retorno
Tempo de Resposta
Tempo de Espera

```
 
 Exemplo de Entrada:
 
```
0 20
0 10
4 6
4 8
```
 
 Exemplo de Saída:

```
FCFS 30,5 19,5 19,5
SJF 21,5 10,5 10,5
RR 31,5 2,0 20,5
```
