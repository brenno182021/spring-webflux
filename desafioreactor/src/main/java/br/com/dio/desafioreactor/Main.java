package br.com.dio.desafioreactor;

import com.github.javafaker.Faker;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;
import java.util.Locale;

public class Main {

    private static final Faker faker = new Faker(new Locale("pt", "BR"));
    
    public static void main(String[] args) {
        var question1 = new Question1();
        var question2 = new Question2();
        var question3 = new Question3();

        System.out.println("====== Questão 1 ============");
        question1.inc(List.of(1L,2L,3L))
                .doOnNext(System.out::println)
                .subscribe();

        System.out.println("=============================");
        System.out.println("====== Questão 2 ============");
        var noAdminUsers = generateUsers((long) faker.number().randomDigitNotZero(), false);
        var adminUsers = generateUsers((long) faker.number().randomDigitNotZero(), true);
        question2.countAdmins(Stream.concat(noAdminUsers.stream(),adminUsers.stream()).toList())
                .doOnNext(System.out::println)
                .subscribe();

        System.out.println("=============================");

        System.out.println("====== Questão 3 ============");

        question3.userIsValid(new User(1L, faker.name().name(), faker.internet().emailAddress(), faker.lorem().characters(0,8), faker.bool().bool()))
                .doOnNext(System.out::println)
                .subscribe();

        question3.userIsValid(new User(1L, faker.name().name(), faker.internet().emailAddress(), faker.lorem().characters(8,225), faker.bool().bool()))
                .doOnNext(System.out::println)
                .doOnSuccess(unused -> System.out.println("Usuario Valido"))
                .subscribe();

        System.out.println("=============================");
    }
    
    
    public static List<User> generateUsers(final Long limit, final boolean isAdmin){
        var idGen = new AtomicLong(1L);
        return Stream.generate(() -> 
                new User(idGen.getAndIncrement(), faker.name().name(), faker.internet().emailAddress(), faker.lorem().word(), isAdmin))
                .limit(limit)
                .toList();

    }
}
