# README.md

## Descrição do Projeto

Este projeto é uma API RESTful Quarkus Imperativo que permite a leitura da lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards.

## Como Rodar o Projeto

1. Clone o repositório para sua máquina local.
2. Navegue até a pasta do projeto. `cd ./code-with-quarkus`
3. Execute o comando `quarkus dev` para iniciar o projeto.

## Como Rodar os Testes de Integração

Os testes de integração podem ser executados com o comando `mvn test`.

## Especificações da API

A API possui um endpoint que retorna o produtor com o maior e o menor intervalo entre dois prêmios.

## Formato da API

A API retorna um objeto JSON no seguinte formato:

```json
{
  "min": [
    {
      "producer": "Producer 1",
      "interval": 1,
      "previousWin": 2008,
      "followingWin": 2009
    },
    {
      "producer": "Producer 2",
      "interval": 1,
      "previousWin": 2018,
      "followingWin": 2019
    }
  ],
  "max": [
    {
      "producer": "Producer 1",
      "interval": 99,
      "previousWin": 1900,
      "followingWin": 1999
    },
    {
      "producer": "Producer 2",
      "interval": 99,
      "previousWin": 2000,
      "followingWin": 2099
    }
  ]
}
```

## Requisitos do Sistema

O sistema lê um arquivo CSV de filmes e insere os dados em um banco de dados em memória ao iniciar a aplicação. O banco de dados está em memória usando um SGBD embarcado (H2). Nenhuma instalação externa é necessária.

## Requisitos Não Funcionais do Sistema

O web service RESTful é implementado com base no nível 2 de maturidade de Richardson. Apenas testes de integração são implementados. Eles garantem que os dados obtidos estão de acordo com os dados fornecidos na proposta. Hibernate é utilizado para ORM. 