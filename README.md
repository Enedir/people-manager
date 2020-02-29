# Gerenciador de Pessoas

Esse projeto tem como objetivo criar uma aplicação de gerenciamento de pessoas, que possa se integrar com outros sistemas via uma WEB API e se comunicar com uma aplicação web e mobile via a mesma WEB API.

## Começando

Para pode executar a aplicação é possível realizar por duas vias, primeira via é executar um docker-compose e contruir todo o ambiente automatizado da aplicação do zero, essa opção demanda um pouco mais de tempo até todo o ambiente ser contruido **A latencia da internet interfere no tempo de contrução do ambiente**. Segunda via é executar cada parte da aplicação de maneira separada (Banco de Dados, Backend e Frontend). 

### Pré-requisitos

Para poder executar o projeto é necessario ter instalado na máquina:

```
Docker
Node 10.15.3
Java 11
Maven 3.6.3
PostMan
Postgres
```

### Passos para executar o ambiente automatizado

O primeiro passo a ser feito quando for executar o ambiente indivialmente é ir no projeto **people-manager-server** é verificar se no arquivo **application.properties** a propriedade **spring.datasource.url** esta recebendo o valor do container que no banco local na maquina. Use o valor o container:

```
#Using in docker
spring.datasource.url=jdbc:postgresql://sajadv-postgres:5432/sajadv
```

O segundo passo é executar esse comando na pasta onde se encontra do arquivo docker-compose.

```
#Using in docker
docker-compose up --build  
```

Após esse comando o aplicação será iniciada automaticamente e esta na porta http://localhost:8081/ 


### Passos para executar as aplicações individualmente

Siga os passos na mesma ordem que é mostrada abaixo para executar a aplicação:

#### Backend
O primeiro passo a ser feito é ir no projeto **people-manager-server** é verificar se no arquivo **application.properties** a propriedade **spring.datasource.url** esta recebendo o valor da instancia do banco local na maquina.

```
#Using in localhost
spring.datasource.url=jdbc:postgresql://localhost:5432/{nome-banco}
```
O segundo passo é criar o banco de dados manualmente no Postgres. Assim concluindo essa etapa já é possível executar o projeto do backend na ide de seu preferência.

Obs -> Existe um arquivo de workspace do PostMan na pasta raiz do projeto do backend. Onde é realizados os testes dos endpoint da Web API.

#### Frontend

Para executar o projeto de frontend é necessario ter o angular CLI instaldo.

```
npm install -g @angular/cli

```

Depois é necessario executar os comandos abaixo na pasta raiz do frontend para instalar as dependências no projeto e executar o servidor de teste:

```
npm install
ng serve
```
