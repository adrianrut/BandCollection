package pl.rutkowski.bandcollection.band;

import jakarta.persistence.*;
import pl.rutkowski.bandcollection.Genre;
import pl.rutkowski.bandcollection.musician.Musician;
import pl.rutkowski.bandcollection.recording.Recording;

import java.util.List;

@Entity
public class Band {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @ManyToMany(mappedBy = "bands", cascade = CascadeType.REMOVE)
    private List<Musician> musicians;
    @OneToMany(mappedBy = "band", cascade = CascadeType.REMOVE)
    private List<Recording> recordings;
    private Integer yearOfCreation;
    private Integer endDate;
    private Boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Musician> getMusicians() {
        return musicians;
    }

    public void setMusicians(List<Musician> musicians) {
        this.musicians = musicians;
    }

    public List<Recording> getRecordings() {
        return recordings;
    }

    public void setRecordings(List<Recording> recordings) {
        this.recordings = recordings;
    }

    public Integer getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(Integer yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }

    public Integer getEndDate() {
        return endDate;
    }

    public void setEndDate(Integer endDate) {
        this.endDate = endDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return name;
    }
}


