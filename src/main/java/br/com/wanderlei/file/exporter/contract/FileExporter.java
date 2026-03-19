package br.com.wanderlei.file.exporter.contract;

import br.com.wanderlei.data.dto.PersonDTO;
import org.springframework.core.io.Resource;
import java.util.List;

/**
 * Interface que define o contrato para os exportadores de ficheiros.
 * Qualquer nova implementação (PDF, JSON, etc.) deve implementar este método.
 */
public interface FileExporter {

    /**
     * Exporta uma lista de objetos PersonDTO para um recurso de ficheiro.
     * * @param people A lista de dados a ser exportada.
     * @return Um objeto Resource (ByteArrayResource) contendo os bytes do ficheiro gerado.
     * @throws Exception Caso ocorra algum erro durante a escrita ou processamento do fluxo de dados.
     */
    Resource exportFile(List<PersonDTO> people) throws Exception;
}