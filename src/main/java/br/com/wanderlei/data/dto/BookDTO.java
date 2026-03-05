package br.com.wanderlei.data.dto;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
@Relation (collectionRelation = "books")
public class BookDTO extends RepresentationModel<BookDTO> implements Serializable {
    private Long id;
    private String author;
    private Date launchDate;
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

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookDTO dto)) return false;
        if (!super.equals (o)) return false;
        return Objects.equals (getId ( ), dto.getId ( )) && Objects.equals (getAuthor ( ), dto.getAuthor ( )) && Objects.equals (getLaunchDate ( ), dto.getLaunchDate ( )) && Objects.equals (getPrice ( ), dto.getPrice ( )) && Objects.equals (getTitle ( ), dto.getTitle ( ));
    }

    @Override
    public int hashCode() {
        return Objects.hash (super.hashCode ( ), getId ( ), getAuthor ( ), getLaunchDate ( ), getPrice ( ), getTitle ( ));
    }
}
