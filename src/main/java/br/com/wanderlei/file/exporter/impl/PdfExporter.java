package br.com.wanderlei.file.exporter.impl;

import br.com.wanderlei.data.dto.PersonDTO;
import br.com.wanderlei.file.exporter.contract.FileExporter;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class PdfExporter implements FileExporter {
    @Override
    public Resource exportFile(List<PersonDTO> people) throws Exception {
        return null;
    }
}
