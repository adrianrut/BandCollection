package pl.rutkowski.bandcollection.musician;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicianRepository extends JpaRepository<Musician, Long> {
}
