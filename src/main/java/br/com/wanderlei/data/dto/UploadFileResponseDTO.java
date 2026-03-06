package br.com.wanderlei.data.dto;

import java.io.Serializable;
import java.util.Objects;

public class UploadFileResponseDTO  implements Serializable {

    private static final long serialVersion = 1L;

    private String fileName;
    private String fileDownLoadUri;
    private String fileType;
    private Long size;

    public UploadFileResponseDTO() {
    }

    public UploadFileResponseDTO(String fileName, String fileDownLoadUri, String fileType, Long size) {
        this.fileName = fileName;
        this.fileDownLoadUri = fileDownLoadUri;
        this.fileType = fileType;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownLoadUri() {
        return fileDownLoadUri;
    }

    public void setFileDownLoadUri(String fileDownLoadUri) {
        this.fileDownLoadUri = fileDownLoadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UploadFileResponseDTO that)) return false;
        return Objects.equals (getFileName ( ), that.getFileName ( )) && Objects.equals (getFileDownLoadUri ( ), that.getFileDownLoadUri ( )) && Objects.equals (getFileType ( ), that.getFileType ( )) && Objects.equals (getSize ( ), that.getSize ( ));
    }

    @Override
    public int hashCode() {
        return Objects.hash (getFileName ( ), getFileDownLoadUri ( ), getFileType ( ), getSize ( ));
    }
}
