 PageRep
==========
 
 Este pacote contem a implementação dos seguintes algoritmos de paginação de Memória:
 
  - FIFO:  First In, First Out;
  - OTM:   Algorítmo Ótimo;
  - LRU:   Least Recently Used;
  
 O programa deverá ler da entrada padrão um conjunto de número inteiros onde o primeiro número representa a quantidade de quadros de memória disponíveis na RAM e os demais representam a sequência de referências às páginas, sempre um número por linha.


 Exemplo de Entrada:
 
```
0 20
0 10
4 6
4 8
```

A saída é composta por linhas contendo a sigla de cada um dos três algoritmos e a quantidade de faltas de página obtidas com a utilização de cada um deles.

 
 Exemplo de Saída:

```
FIFO 10
OTM 6
LRU 8
```
