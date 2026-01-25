package br.com.dio.reactiveflashcards.api.controller.documentation;

import br.com.dio.reactiveflashcards.api.controller.request.UserPageRequest;
import br.com.dio.reactiveflashcards.api.controller.request.UserRequest;
import br.com.dio.reactiveflashcards.api.controller.response.UserPageResponse;
import br.com.dio.reactiveflashcards.api.controller.response.UserResponse;
import br.com.dio.reactiveflashcards.core.validation.MongoId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "User", description = "Endpoints para manipulação de usuários")
public interface UserControllerDoc {

    @Operation(summary = "Endpoint para criar um novo usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "retorna o usuário criado",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))}),
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    Mono<UserResponse> save(@Valid @RequestBody UserRequest request);

    @Operation(summary = "Endpoint para buscar todos os usuários")
    @GetMapping(produces = APPLICATION_JSON_VALUE, value = "/all")
    Flux<UserResponse> findAll();


    @Operation(summary = "Endpoint para buscar um usuário pelo seu identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "retorna o usuário correspondente ao identificador",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))}),
    })
    @GetMapping(produces = APPLICATION_JSON_VALUE, value = "{id}")
    Mono<UserResponse> findById(@Parameter(description = "Identificador do usuário")
                                @PathVariable
                                @Valid
                                @MongoId(message = "{userController.id}")
                                String id);

    @Operation(summary = "Endpoint para buscar usuários de forma paginada")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "retorna os usuários de acordo com as informações passadas na request",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserPageResponse.class))}),
    })
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    Mono<UserPageResponse> findOnDemand(@Valid UserPageRequest request);

    @Operation(summary = "Endpoint para atualizar um usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "retorna o usuário atualizado",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))}),
    })
    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, value = "{id}")
    Mono<UserResponse> update(@Parameter(description = "Identificador do usuário")
                              @PathVariable
                              @Valid
                              @MongoId(message = "{userController.id}")
                              String id,
                              @Valid
                              @RequestBody
                              UserRequest request);

    @Operation(summary = "Endpoint para excluir um usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "o usuário foi excluído"),
    })
    @DeleteMapping(value = "{id}")
    @ResponseStatus(NO_CONTENT)
    Mono<Void> delete(@Parameter(description = "Identificador do usuário")
                      @PathVariable
                      @Valid
                      @MongoId(message = "{userController.id}")
                      String id);
}
