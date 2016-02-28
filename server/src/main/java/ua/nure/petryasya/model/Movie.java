package ua.nure.petryasya.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@Entity
@Table(name = "movie", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @XmlElement(required = true)
    private Integer id;

    @Column(name = "name", nullable = false)
    @XmlElement(required = true)
    private String name;

    @Column(name = "description")
    @XmlElement(required = true)
    private String description;

    @Column(name = "year")
    @XmlElement(required = true)
    private Integer year;

    @Column(name = "viewed")
    @XmlElement(required = true)
    private boolean viewed;

    public Movie() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", viewed=" + viewed +
                '}';
    }
}
