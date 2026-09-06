# Estudos de Spring WebFlux e Project Reactor

Repositório com exemplos de programação reativa, desafios de Reactor e uma API de flashcards. Os três módulos usam Java 17 e possuem projetos Maven independentes.

## Módulos

| Pasta | Conteúdo |
| --- | --- |
| [sample-reactor](sample-reactor) | Exemplo de transformação de dados com Flux |
| [desafioreactor](desafioreactor) | Exercícios de transformação, contagem e validação de usuários |
| [reactive-flashcards](reactive-flashcards) | API reativa de baralhos e sessões de estudo, com MongoDB e integração de e-mail |

O `sample-reactor` declara Spring Boot 3.5.5; os outros módulos declaram 3.5.6. Não há build Maven agregador na raiz.

## Requisitos

- JDK 17.
- IDE Java para executar os exemplos de console.
- Docker e Docker Compose para o ambiente de flashcards.
- Acesso à internet para resolver as dependências Maven.

## Obter o projeto

```bash
git clone https://github.com/brenno182021/spring-webflux.git
cd spring-webflux
```

## Executar os exemplos de Reactor

Importe cada `pom.xml` na IDE e execute:

- `sample-reactor`: `com.example.demo.SampleReactorApplication`.
- `desafioreactor`: `br.com.dio.desafioreactor.Main`.

O exemplo atual de `sample-reactor` imprime o resultado de um fluxo no console; a chamada que inicia o servidor Spring está comentada.

Para compilar um módulo no Windows:

```powershell
cd sample-reactor
.\mvnw.cmd compile
```

No Linux/macOS, use `sh mvnw compile`. Repita dentro de `desafioreactor` quando necessário.

## API de flashcards

A API utiliza WebFlux, MongoDB reativo, Bean Validation, Lombok, MapStruct, Spring Mail e Thymeleaf.

A partir da raiz:

```bash
cd reactive-flashcards
docker compose up --build
```

O Compose define a aplicação, MongoDB e Mailcatcher. O script `start.sh` empacota a aplicação com os testes desabilitados e executa o JAR.

| Recurso | Endereço configurado |
| --- | --- |
| API | `http://localhost:8081/reactive-flashcards` |
| Interface do Mailcatcher | `http://localhost:1080` |
| MongoDB exposto no host | `localhost:27017` |

### Dependência externa de baralhos

O arquivo [application.yml](reactive-flashcards/src/main/resources/application.yml) configura `deck-api.base-url` como `external:3000`, com recursos `auth` e `decks`. O Compose deste repositório não define o serviço `external`.

Para usar os fluxos que consultam essa API, disponibilize o serviço correspondente e configure uma URL HTTP completa acessível à aplicação, por exemplo por meio da variável `DECK_API_BASE_URL`. A configuração atual, sozinha, não fornece essa integração.

Para executar a aplicação fora do Compose, ajuste também a conexão MongoDB e o host/porta SMTP: os nomes `db` e `mailcatcher` da configuração pertencem à rede Docker.

## Testes e estado

Cada módulo contém sua própria pasta `src/test`. Execute os testes dentro do módulo escolhido:

```powershell
.\mvnw.cmd test
```

No Linux/macOS, use `sh mvnw test`. Os testes que carregam a aplicação podem exigir os serviços configurados.

Este é um repositório de estudos, incluindo desafios identificados como DIO. As instruções foram elaboradas a partir do código e das configurações versionadas; o build e o ambiente Docker não foram executados nesta atualização de documentação.

## Licença

[MIT](LICENSE), conforme a licença já presente no repositório.
