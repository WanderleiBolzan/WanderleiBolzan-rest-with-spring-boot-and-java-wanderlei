package br.com.wanderlei.file.exporter.impl;

import br.com.wanderlei.data.dto.PersonDTO;
import br.com.wanderlei.file.exporter.contract.FileExporter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class CsvExporter implements FileExporter {

    @Override
    public Resource exportFile(List<PersonDTO> people) throws Exception {
        // Usamos try-with-resources para garantir que o Stream e o Writer sejam fechados
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             OutputStreamWriter writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {

            CSVFormat csvFormat = CSVFormat.Builder.create()
                    .setHeader("ID", "First Name", "Last Name", "Address", "Gender", "Enabled")
                    .setSkipHeaderRecord(false)
                    .build();

            try (CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat)) {
                for (PersonDTO person : people) {
                    csvPrinter.printRecord(
                            person.getId().toString(),
                            person.getFirstName(),
                            person.getLastName(),
                            person.getAddress(), // Campo adicionado conforme seu DTO
                            person.getGender(),
                            person.getEnabled() != null && person.getEnabled() ? "Yes" : "No"
                    );
                }
                // É CRÍTICO dar flush aqui antes de converter para array de bytes
                csvPrinter.flush();
            }
            writer.flush();
            
            return new ByteArrayResource(outputStream.toByteArray());
        }
    }
}