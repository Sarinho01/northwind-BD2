# northwind-BD2
Trabalho de Banco de Dados 2. Implementar uma interface gráfica para fazer as operações básicas do SQL utilizando um framework qualquer, utilizando o banco de dados SQL SERVER.

## Objetivos:
- Realizar operações no Customer: Visualização, Inserção, Remoção e atualização;
- Visualizar Orders e Orders Details;
- Inserir Orders (pedidos) com vários Order Details (produtos);
- Criar algum Trigger que se relacione com a inserção da Order (pontuado no item anterior);
- Criar um Procedure que faça algum relatório.

## Como rodar o projeto no seu computador:

Abra o projeto na pasta `src/main/resources/NorthWind/`, abra o arquivo ‘instnwnd.sql’ e execute ele no SQL SERVER.

Note que nesse arquivo está incluso o banco de dados Northwind com o Trigger e PROCEDURE. 

Após abrir o projeto, vá para o path ‘src/main/resources/application.properties’. No arquivo ‘application.properties’.

Na linha 1: 
‘spring.datasource.url=jdbc:sqlserver://localhost;databaseName=NorthwindSaro;encrypt=true;trustServerCertificate=true’
no ‘databaseName=NorthwindSaro’, verifique se o banco de dados criado com a execução do arquivo SQL, anteriormente citado, tem o nome "NorthwindSaro".

Na linha 2 e 3:
‘spring.datasource.username=sa’
‘spring.datasource.password=12345’

Crie um perfil no SQL SERVER com o nome "sa" e senha "12345". Caso não queira criar um novo perfil, substitua apenas o "sa" do "username=sa"
para o username que você quer, a mesma coisa com a senha.

Se você encontrar um erro relacionado ao TCP ao executar o aplicativo Spring Boot, certifique-se de que a conexão TCP/IP no SQL Server 2022 Configuration Manager esteja habilitada.


Para executar o projeto ao final de toda a configuração do ambiente, vá no arquivo localizado no path ‘src/main/java/com/saroswork/nothwindexample/NothwindexampleApplication.java’ e execute o método Main.
A interface visual do projeto foi feita com Vaadin. Ao executar o projeto pela primeira vez será baixado os arquivos do frontend, semelhante ao NPM INSTALL do NodeJs. 
OBS: Nós tentamos baixar o projeto em sala, mas a internet barrou a instalação do "NPM" do Vaadin.

Ao abrir o projeto, entre no endereço ‘http://localhost:8080/’. Logo na página inicial, temos o Procedure pedido, que mostra os 10 clientes com mais pedidos. A esquerda da tela, 
tem uma NAVBAR, nela você pode ir às seguintes páginas:
"Home", que é a inicial; 
“Customers”, que é a página para visualizar, inserir, deletar e atualizar os Customers;
“Orders”, onde você pode visualizar as Orders e os Orders Details de cada respectiva ordem.
“Create Order”, onde você pode adicionar uma nova Order (pedido) e pode adicionar ao pedido diversos Order Details (produtos).

Note que a tabela não está completamente visível na tela. Para visualizar as colunas à direita e acessar algumas opções, como atualizar as informações do cliente na janela "Customer" ou ver os detalhes dos pedidos na janela "Orders", pode ser necessário arrastar a tabela um pouco para o lado.

OBS: Para excluir um cliente, é necessário clicar no botão "Update" correspondente na tabela e, em seguida, selecionar a opção "Delete" na janela que será aberta ao lado.

OBS: Para concluir um pedido na tela "Create Order", o botão correspondente está localizado na parte inferior da tela. Dependendo da resolução do seu monitor, pode ser que o botão não esteja visível na tela inicial, sendo necessário rolar a página para baixo para visualizá-lo.

O nosso Trigger é acionado após a inserção de um order detail, ele atualiza o valor do freight caso exista 3 produtos diferentes associados àquele pedido.
O nosso Procedure mostra um relatório dos top 10 Customers com mais Orders.
Ambos estão adicionados no final do arquivo .SQL dito anteriormente.
