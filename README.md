# Gerenciador de Pessoas

Esse projeto tem como objetivo criar uma aplicação de gerenciamento de pessoas, que possa se integrar com outros sistemas via uma WEB API e também se comunição com uma aplicação web e mobile via a mesma WEB API.

## Começando

Para pode executar a aplicação é possível realizar por duas vias a primeia é executar um docker-compose e contruir todo o ambiente da aplicação do zero, essa opção demanda um pouco de tempo até todo o ambiente ser contruido **A latencia da internet interfere no tempo de contrução do ambiente**. O segundo e executar cada parte da aplicação em separado(Banco de Dados, Backend e Frontend). 

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

O primeiro passo a ser feito quando for executar o ambiente indivialmente é ir no projeto **people-manager-server** é verificar o arquivo **application.properties** a propriedade **spring.datasource.url** esta recebendo o valor do container que no banco local na maquina. Use o valor o container:

```
#Using in docker
spring.datasource.url=jdbc:postgresql://sajadv-postgres:5432/sajadv
```

Depois é executar esse comando na pasta raiz dos projetos

```
#Using in docker
docker-compose up --build  
```

Após esse comando o aplicação será iniciada automaticamente e esta na porta http://localhost:8081/ 


### Passos para executar as aplicações individualmente

Para poder executar a aplica i

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

