package br.com.wanderlei.controllers.docs;

import br.com.wanderlei.data.dto.UploadFileResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "File Endpoint", description = "Endpoints para upload e download de arquivos")
public interface FileControllerDocs {

    @Operation(summary = "Faz o upload de um único arquivo")
    @ApiResponse(responseCode = "200", description = "Upload realizado com sucesso")
    UploadFileResponseDTO uploadFile(
            @Parameter(
                    description = "Arquivo a ser enviado",
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(type = "string", format = "binary"))
            ) MultipartFile file
    );

    @Operation(summary = "Faz o upload de múltiplos arquivos")
    @ApiResponse(responseCode = "200", description = "Uploads realizados com sucesso")
    List<UploadFileResponseDTO> uploadMultipleFiles(
            @Parameter(
                    description = "Arquivos a serem enviados",
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            // Removido o aninhamento complexo de 'items' que causava erro
                            schema = @Schema(
                                    type = "array",
                                    description = "Array de arquivos binários"
                                    // Para arrays de MultipartFile, o Swagger geralmente
                                    // deduz o item a partir do tipo do parâmetro no Java
                            )
                    )
            ) MultipartFile[] files
    );

    @Operation(summary = "Faz o download de um arquivo")
    ResponseEntity<Resource> downloadFile(
            @Parameter(description = "Nome do arquivo com extensão", example = "imagem.jpg") String fileName,
            HttpServletRequest request);
}