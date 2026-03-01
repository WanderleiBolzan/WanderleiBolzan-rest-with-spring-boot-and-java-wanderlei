package br.com.wanderlei.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table (name = "book")
public class Book  implements Serializable {

  private static final long serialVersion = 1L;

  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "author" , nullable = false, length = 80)
  private String author;
  @Column(name = "launch_date" , nullable = false)
  @Temporal (TemporalType.DATE)
  private Date launch_date;
  @Column(name = "price" , nullable = false)
  private Double price;
  @Column(name = "title" , nullable = false, length = 100)
  private String title;

    public Book() {
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
        if (!(o instanceof Book book)) return false;
        return Objects.equals (getId ( ), book.getId ( )) && Objects.equals (getAuthor ( ), book.getAuthor ( )) && Objects.equals (getLaunch_date ( ), book.getLaunch_date ( )) && Objects.equals (getPrice ( ), book.getPrice ( )) && Objects.equals (getTitle ( ), book.getTitle ( ));
    }

    @Override
    public int hashCode() {
        return Objects.hash (getId ( ), getAuthor ( ), getLaunch_date ( ), getPrice ( ), getTitle ( ));
    }
}
