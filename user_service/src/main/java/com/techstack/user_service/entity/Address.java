package com.techstack.user_service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "addresses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String houseNumber;
    private String street;
    private String city;
    
    @NonNull
    private String country;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return id.equals(address.id) && Objects.equals(houseNumber, address.houseNumber)
                && Objects.equals(street, address.street) && Objects.equals(city, address.city) && country.equals(address.country);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, houseNumber, street, city, country);
    }
}
