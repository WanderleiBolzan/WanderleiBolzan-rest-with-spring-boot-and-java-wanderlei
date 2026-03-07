package br.com.wanderlei.services;

import br.com.wanderlei.config.FileStorageConfig;
import br.com.wanderlei.exception.FileNotFoundException;
import br.com.wanderlei.exception.FileStorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileStorageService {

    private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);
    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {
        // Converte o diretório configurado em um caminho absoluto normalizado
        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            logger.info("Verifying/Creating upload directory at: {}", this.fileStorageLocation);
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            logger.error("Could not create the directory where files will be stored!");
            throw new FileStorageException("Could not create the directory where files will be stored!", e);
        }
    }

    public String storeFile(MultipartFile file) {
        // Normaliza o nome do arquivo e lida com possíveis nulos
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            // Verificação de segurança contra ataques de Path Traversal (ex: ../../etc/passwd)
            if (fileName.contains("..")) {
                logger.error("Sorry! Filename contains an invalid path sequence: {}", fileName);
                throw new FileStorageException("Sorry! Filename contains an invalid path sequence " + fileName);
            }

            logger.info("Saving file {} to disk", fileName);
            Path targetLocation = this.fileStorageLocation.resolve(fileName);

            // Copia o arquivo para o diretório de destino (substitui se já existir)
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (Exception e) {
            logger.error("Could not store file {}. Please try again!", fileName, e);
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                logger.warn("File not found or not readable: {}", fileName);
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (Exception e) {
            logger.error("Error retrieving file: {}", fileName, e);
            throw new FileNotFoundException("File not found " + fileName, e);
        }
    }
}