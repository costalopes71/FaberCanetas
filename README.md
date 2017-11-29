# FaberCanetas
Projeto do 1 ano de faculdade de Analise e Desenvolvimento de Sistemas onde foi desenvolvido um projeto de Supply Chain para a empresa T-Systems.

Este projeto foi destinado ao primeiro ano da faculdade. Nele voce poderá encontrar 3 telas principais:
  - o primeiro ambiente é um portal de vendas, que pode ser acessado através do formulario de login, logicamente ele esta conectado
  a um banco de dados.
  - o segundo ambiente é acessado através do botao area restrita na tela de login, o qual abre um modal de login de funcionario,
  se o funcionario que logar tiver a funcao de GERENTE ele sera direcionado para um dashboard gerencial, onde os graficos apresenta-
  dos sao graficos gerenciais.
  - o terceiro ambiente é tambem acessado atraves do botao area restrita na tela de login, e NAO tendo o funcionario a funcao de GE-
  RENTE ele sera direcionado para um dashboard operacional, onde o funcionario podera ver se existem pedidos de compra ou venda pen-
  dentes, o estoque dos armazem e etc.
  
Foi implementado tambem, no dashboard operacional, um sistema de farois individuais e geral do sistema, o qual avisa de forma in-
tuitiva se o supply-chain necessita de alguma atencao. Sendo VERDE - tudo OK, AMARELO - ATENCAO para algo (ex: pedido de venda pen-
dente) e VERMELHO - PERIGO (ex: pedido de venda atrasado).
