package br.com.dio.desafioreactor;

import com.github.javafaker.Faker;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Locale;

public class Question2 {
    private final Faker faker = new Faker(new Locale("pt", "BR"));

    /*
    Recebe uma lista de usuarios e retorna a quantos usuários admin tem na lista
     */
    public Mono<Long> countAdmins(final List<User> users){
        return Mono.fromCallable(() -> users.stream().filter(User::isAdmin).count());
    }
}
