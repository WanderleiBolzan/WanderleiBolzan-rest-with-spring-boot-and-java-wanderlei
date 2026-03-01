package br.com.wanderlei.controlers.docs;

import br.com.wanderlei.data.dto.BookDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BookControllerDocs {
    @Operation (
            summary = "Finds a Book",
            description = "Find a Specific Book by your Id",
            tags = {"Book"},
            responses = {
                    @ApiResponse (
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content ( mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema ( implementation = BookDTO.class )
                                    )
                            } ),
                    @ApiResponse ( description = "No Content", responseCode = "204", content = @Content ),
                    @ApiResponse ( description = "Bad Request", responseCode = "400", content = @Content ),
                    @ApiResponse ( description = "Unauthorized", responseCode = "401", content = @Content ),
                    @ApiResponse ( description = "Not Found", responseCode = "404", content = @Content ),
                    @ApiResponse ( description = "Internal Server Error", responseCode = "500", content = @Content )
            }
    )
    BookDTO findById(@PathVariable ( "id" ) Long id);

    @Operation (
            summary = "Find all Books",
            description = "Find all Book",
            tags = {"Book"},
            responses = {
                    @ApiResponse (
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content (
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema ( schema = @Schema ( implementation = BookDTO.class ) )
                                    )
                            } ),
                    @ApiResponse ( description = "No Content", responseCode = "204", content = @Content ),
                    @ApiResponse ( description = "Bad Request", responseCode = "400", content = @Content ),
                    @ApiResponse ( description = "Unauthorized", responseCode = "401", content = @Content ),
                    @ApiResponse ( description = "Not Found", responseCode = "404", content = @Content ),
                    @ApiResponse ( description = "Internal Server Error", responseCode = "500", content = @Content )
            }
    )
    List<BookDTO> findAll();

    @Operation (
            summary = "Update a Book",
            description = "Upadate a Specific Book your Id",
            tags = {"Book"},
            responses = {
                    @ApiResponse (
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content (
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema ( schema = @Schema ( implementation = BookDTO.class ) )
                                    )
                            } ),
                    @ApiResponse ( description = "No Content", responseCode = "204", content = @Content ),
                    @ApiResponse ( description = "Bad Request", responseCode = "400", content = @Content ),
                    @ApiResponse ( description = "Unauthorized", responseCode = "401", content = @Content ),
                    @ApiResponse ( description = "Not Found", responseCode = "404", content = @Content ),
                    @ApiResponse ( description = "Internal Server Error", responseCode = "500", content = @Content )
            }
    )
    BookDTO update(@RequestBody BookDTO book);

    ResponseEntity<?> delete(@PathVariable ( "id" ) Long id);

    @Operation (
            summary = "Delete a Book",
            description = "Delete a Specific Book Your Id",
            tags = {"Book"},
            responses = {
                    @ApiResponse (
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content (
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema ( schema = @Schema ( implementation = BookDTO.class ) )
                                    )
                            } ),
                    @ApiResponse ( description = "No Content", responseCode = "204", content = @Content ),
                    @ApiResponse ( description = "Bad Request", responseCode = "400", content = @Content ),
                    @ApiResponse ( description = "Unauthorized", responseCode = "401", content = @Content ),
                    @ApiResponse ( description = "Not Found", responseCode = "404", content = @Content ),
                    @ApiResponse ( description = "Internal Server Error", responseCode = "500", content = @Content )
            }
    )
    BookDTO create(@RequestBody BookDTO book);
}
