package br.com.wanderlei.file.exporter.factory;

import br.com.wanderlei.exception.BadRequestException;
import br.com.wanderlei.file.exporter.MediaTypes;
import br.com.wanderlei.file.exporter.contract.FileExporter;
import br.com.wanderlei.file.exporter.impl.CsvExporter;
import br.com.wanderlei.file.exporter.impl.XlsxExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class FileExporterFactory {

    @Autowired
    private ApplicationContext context;

    /**
     * Retorna a implementação correta de FileExporter baseada no Accept Header.
     * Note que a Factory não deve implementar FileExporter, ela apenas gerencia a criação.
     * * @param acceptHeader O tipo de mídia solicitado (CSV ou XLSX).
     * @return Uma instância de FileExporter (CsvExporter ou XlsxExporter).
     */
    public FileExporter getExporter(String acceptHeader) {
        if (acceptHeader == null) {
            throw new BadRequestException("Accept header is required!");
        }

        if (acceptHeader.equalsIgnoreCase(MediaTypes.APPLICATION_XLSX_VALUE)) {
            return context.getBean(XlsxExporter.class);
        } else if (acceptHeader.equalsIgnoreCase(MediaTypes.APPLICATION_CSV_VALUE)) {
            return context.getBean(CsvExporter.class);
        } else {
            throw new BadRequestException("Invalid File Format: " + acceptHeader);
        }
    }
}