package br.com.wanderlei.data.dto.v2;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonPropertyOrder({"id", "firstName", "lastName","burthDate","address","gender"})
public class PersonDTOV2 implements Serializable {

    private static final long serialVersion = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private Date burthDate;
    private String address;
    private String gender;

    public PersonDTOV2() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBurthDate() {
        return burthDate;
    }

    public void setBurthDate(Date burthDate) {
        this.burthDate = burthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonDTOV2 that)) return false;
        return Objects.equals (getId ( ), that.getId ( )) && Objects.equals (getFirstName ( ), that.getFirstName ( )) && Objects.equals (getLastName ( ), that.getLastName ( )) && Objects.equals (getBurthDate ( ), that.getBurthDate ( )) && Objects.equals (getAddress ( ), that.getAddress ( )) && Objects.equals (getGender ( ), that.getGender ( ));
    }

    @Override
    public int hashCode() {
        return Objects.hash (getId ( ), getFirstName ( ), getLastName ( ), getBurthDate ( ), getAddress ( ), getGender ( ));
    }
}
