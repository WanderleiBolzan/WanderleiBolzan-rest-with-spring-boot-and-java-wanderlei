package br.com.wanderlei.controlers.docs;

import br.com.wanderlei.data.dto.PersonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PersonControllerDocs {
    @Operation (
            summary = "Finds a Person",
            description = "Find a Specific Person by your Id",
            tags = {"People"},
            responses = {
                    @ApiResponse (
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content ( mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema ( implementation = PersonDTO.class )
                                    )
                            } ),
                    @ApiResponse ( description = "No Content", responseCode = "204", content = @Content ),
                    @ApiResponse ( description = "Bad Request", responseCode = "400", content = @Content ),
                    @ApiResponse ( description = "Unauthorized", responseCode = "401", content = @Content ),
                    @ApiResponse ( description = "Not Found", responseCode = "404", content = @Content ),
                    @ApiResponse ( description = "Internal Server Error", responseCode = "500", content = @Content )
            }
    )
    ResponseEntity<PersonDTO> findById(@PathVariable ( "id" ) Long id);

    @Operation (
            summary = "Find all People",
            description = "Find all People",
            tags = {"People"},
            responses = {
                    @ApiResponse (
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content (
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema ( schema = @Schema ( implementation = PersonDTO.class ) )
                                    )
                            } ),
                    @ApiResponse ( description = "No Content", responseCode = "204", content = @Content ),
                    @ApiResponse ( description = "Bad Request", responseCode = "400", content = @Content ),
                    @ApiResponse ( description = "Unauthorized", responseCode = "401", content = @Content ),
                    @ApiResponse ( description = "Not Found", responseCode = "404", content = @Content ),
                    @ApiResponse ( description = "Internal Server Error", responseCode = "500", content = @Content )
            }
    )
    ResponseEntity<PagedModel<EntityModel<PersonDTO>>> findAll(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="size", defaultValue="12") Integer size,
            @RequestParam(value="direction", defaultValue="asc") String direction
    );
    @Operation(
            summary = "Massive People Creation",
            description = "Massive People Creation with Upload of CSV",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "201", // Alterado para 201 Created para refletir o HttpStatus
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class)))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<List<PersonDTO>> massCreation(@RequestParam("file") MultipartFile file);
    @Operation(summary = "Find People by FirstName",
            description = "Finds People by their First Names",
            tags = {"People"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))
                                    )
                            }),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<PagedModel<EntityModel<PersonDTO>>> findByName(
            @PathVariable("firstName") String firstName,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    );

    @Operation (
            summary = "Update a People",
            description = "Upadate a Specific People your Id",
            tags = {"People"},
            responses = {
                    @ApiResponse (
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content (
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema ( schema = @Schema ( implementation = PersonDTO.class ) )
                                    )
                            } ),
                    @ApiResponse ( description = "No Content", responseCode = "204", content = @Content ),
                    @ApiResponse ( description = "Bad Request", responseCode = "400", content = @Content ),
                    @ApiResponse ( description = "Unauthorized", responseCode = "401", content = @Content ),
                    @ApiResponse ( description = "Not Found", responseCode = "404", content = @Content ),
                    @ApiResponse ( description = "Internal Server Error", responseCode = "500", content = @Content )
            }
    )
    ResponseEntity<PersonDTO> update(@RequestBody PersonDTO person);


    @Operation (
            summary = "Disable a Person",
            description = "Disable a Specific Person by your Id",
            tags = {"People"},
            responses = {
                    @ApiResponse (
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content ( mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema ( implementation = PersonDTO.class )
                                    )
                            } ),
                    @ApiResponse ( description = "No Content", responseCode = "204", content = @Content ),
                    @ApiResponse ( description = "Bad Request", responseCode = "400", content = @Content ),
                    @ApiResponse ( description = "Unauthorized", responseCode = "401", content = @Content ),
                    @ApiResponse ( description = "Not Found", responseCode = "404", content = @Content ),
                    @ApiResponse ( description = "Internal Server Error", responseCode = "500", content = @Content )
            }
    )
    ResponseEntity<PersonDTO> disablePerson(@PathVariable ( "id" ) Long id);


    ResponseEntity<?> delete(@PathVariable ( "id" ) Long id);

    @Operation (
            summary = "Delete a People",
            description = "Delete a Specific People Your Id",
            tags = {"People"},
            responses = {
                    @ApiResponse (
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content (
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema ( schema = @Schema ( implementation = PersonDTO.class ) )
                                    )
                            } ),
                    @ApiResponse ( description = "No Content", responseCode = "204", content = @Content ),
                    @ApiResponse ( description = "Bad Request", responseCode = "400", content = @Content ),
                    @ApiResponse ( description = "Unauthorized", responseCode = "401", content = @Content ),
                    @ApiResponse ( description = "Not Found", responseCode = "404", content = @Content ),
                    @ApiResponse ( description = "Internal Server Error", responseCode = "500", content = @Content )
            }
    )
    ResponseEntity<PersonDTO> create(@RequestBody PersonDTO person);
}
