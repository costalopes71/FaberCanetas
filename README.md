# FaberCanetas
Projeto WEB do 1 (primeiro) ano de faculdade de Análise e Desenvolvimento de Sistema na faculdade FIAP. 
Foi desenvolvido um projeto com o tema de Supply Chain. 
Basicamente, foi implementado um portal de vendas e dois sistemas de dashboard, sendo o primeiro um dashboad gerencial e o segundo um dashboard operacional.

# Quais TECNOLOGIAS você irá encontrar no projeto
Front-end : 
* Html5
* css3 
* bootstrap 3.3.7, jquery, javascript

Backend : 
* Java 8
* JDBC
* Servlet
* JSP
* tags libs, jstl
* JEE
* SQL (Oracle)

# Arquitetura
* Model-View-Controller (MVC) com camada de apresentação, camada de persistência (DAO) e camada de negócio (BO) usando para a comunicação entre camadas os objetos de transferência (TO ou DTO).
* Client-Server pattern

# Design Patterns
* Singleton 
* DAO
*Factory

# Web container
* Tomcat9

# Quais tópicos principais posso encontrar no projeto?
Usando as tecnologias listadas acima, você poderá ver como implementamos:
- persitencia de dados: usando JDBC em um BD remoto Oracle
- guardar sessão de usuário
- carrinho de compras
- interceptadores de requisição (Interceptors)
- exibição de gráficos na tela em tempo real (atualização a cada X segundos)
- modais de confirmação usando bootstrap
- requisições assíncronas (usando AJAX)
- fowards e redirects
- CRUD completo
- lógica de programação, principalmente no método que controla o farol de estoque dos armazéns
- Login e Logout do sistema fazendo validação num banco de dados
- Requisições POST e GET (e a utilização da boa prática de "always redirect after post")
- entre outros

# Ambientes e Telas de visualização do usuário
3 ambientes principais:
  - o primeiro ambiente é um portal de vendas, que pode ser acessado através do formulario de login, que está conectado a um banco de dados.
  - o segundo ambiente é acessado através do botao área restrita na tela de login, o qual abre um modal de login de funcionarió. Se o funcionario que logar tiver a função de GERENTE, será direcionado para um dashboard gerencial, onde os gráficos apresenta-dos são gráficos gerenciais.
  - o terceiro ambiente é tambem acessado atraves do botao área restrita na tela de login, e não sendo o funcionario GERENTE ele será direcionado para um dashboard operacional, onde poderá verificar se existem pedidos de compra ou venda pendentes, o estoque dos armazéns e etc.
  Foi implementado tambem, no dashboard operacional, um sistema de faróis individuais e geral do sistema, o qual avisa de forma intuitiva se o supply-chain necessita de alguma atencão. Sendo VERDE - tudo OK, AMARELO - ATENÇÃO para algo (ex: pedido de venda pen-
dente) e VERMELHO - PERIGO (ex: pedido de venda atrasado).

# Como rodar?
Clonar o projeto e abrir ele na sua IDE de preferência. Rodar o projeto usando um servidor TOMCAT 9.
A parte mais trabalhosa será criar o banco de dados, pois como ele usa JDBC as tabelas não são criadas automaticamente como quando usamos JPA/Hibernate. E infelizmente não tinha o costume ainda de fazer o criaBanco.sql e o apagaBanco.sql ainda! XD rs
