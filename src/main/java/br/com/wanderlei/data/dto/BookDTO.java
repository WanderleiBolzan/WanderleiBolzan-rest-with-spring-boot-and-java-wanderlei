package br.com.wanderlei.data.dto;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class BookDTO extends RepresentationModel<BookDTO> implements Serializable {
    private Long id;
    private String author;
    private Date launch_date;
    private Double price;
    private String title;

    public BookDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getLaunch_date() {
        return launch_date;
    }

    public void setLaunch_date(Date launch_date) {
        this.launch_date = launch_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookDTO dto)) return false;
        if (!super.equals (o)) return false;
        return Objects.equals (getId ( ), dto.getId ( )) && Objects.equals (getAuthor ( ), dto.getAuthor ( )) && Objects.equals (getLaunch_date ( ), dto.getLaunch_date ( )) && Objects.equals (getPrice ( ), dto.getPrice ( )) && Objects.equals (getTitle ( ), dto.getTitle ( ));
    }

    @Override
    public int hashCode() {
        return Objects.hash (super.hashCode ( ), getId ( ), getAuthor ( ), getLaunch_date ( ), getPrice ( ), getTitle ( ));
    }
}
