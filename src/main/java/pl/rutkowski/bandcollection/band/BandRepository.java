package pl.rutkowski.bandcollection.band;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {

    List<Band> findAllByOrderByName();

    List<Band> findAllByOrderByGenre();

    List<Band> findAllByOrderByYearOfCreation();

}
