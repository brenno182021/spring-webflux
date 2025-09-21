package br.com.dio.desafioreactor;

import reactor.core.publisher.Mono;

public class Question3 {

    /*
    Verifica se o usuário passado é válido, caso seja retorna void, caso contrário deve disparar uma exception
    (para essse desafio vamos considerar que o usuario é valido quando ele tem uma senha com mais de 8 caracteres)
     */
    public Mono<Void> userIsValid(final User user){
        if (user.password()!=null && user.password().length() > 8) {
            return Mono.empty();
        }else{
            return Mono.error(new IllegalArgumentException("Senha inválida: deve ter mais de 8 caracteres"));
        }
    }
}
