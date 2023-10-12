# Prjt_Lojinha

Lojinha da C3C :)

## Funcionalidades

- Controlar o Estoque da Lojinha
    * Controle de Produtos
        * Inserção
        * Atualização (inclui reposição)
        * Consulta
        * Retirada
    * Controle de Quantidade
        * Quant Maxima, Atual e Mínima
    * Controle de Categoria
    * Controle de Valores
        * Total no Estoque
        * Total por Categoria
    * Controle de Reposição
        * Baseada nas Quantidades

- Controle de Pessoal
    * Vendedores
    * Gerente
    * Login e Senha (hash md5)

- Controle de Vendas
    * Carrinho
    * Histórico
    * Favoritos

- Controle dos Clientes

## Organização da lógica

- Classe Gerente com a Estoque e Produtos:
    * cada produto vai pertencer ao estoque e será administrado pelos funcionários, por isso o campo “setor” em Funcionário, que vai se relacionar com as categorias dos produtos
    * o estoque vai ser administrado pelo seu respectivo Gerente, no caso para operações de reposição, consultas do orçamento, etc. Um Gerente também vai ter uma lista de funcionários responsáveis por controlar os produtos do respectivo Estoque, de acordo com as categorias.
    * logo Estoque também vai precisar de uma lista de Categorias para servir de auxílio para os funcionários.
    
- Classe Funcionário e Cliente:
    * Como podem haver pessoas já registradas no banco sem necessáriamente estarem associadas à Funcionário ou à Cliente, nos métodos insert vai ter um sistema que checa se a pessoa já existe, evitando a criação de mais uma instância. Pra esse sistema foi usado o método findById().

## Arquivo do Banco

- Baixar o arquivo lojinha.sql
- Abrir o terminal na pasta e digitar o comando: 
    * mysql -u [seu usuario] -p [banco pra carregar] < lojinha.sql
    * digite a senha do mysql

## Arquivo properties

- É o arquivo com as informações do seu mysql.
- Fora da pasta dos códigos, crie um arquivo chamado dbConn.properties:
    1. user="[seu usuario]"
    2. password="[sua senha do mysql]"
    3. dburl=jdbc:mysql://localhost:3306/[nome do banco que foi carregado o arquivo]
