package pl.rutkowski.bandcollection.musician;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicianRepository extends JpaRepository<Musician, Long> {

    List<Musician> findAllByOrderByName();

    List<Musician> findAllByOrderByBands();

    List<Musician> findAllByOrderByRole();

}
