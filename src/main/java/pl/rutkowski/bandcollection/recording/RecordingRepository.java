package pl.rutkowski.bandcollection.recording;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordingRepository extends JpaRepository<Recording, Long> {

    List<Recording> findAllByOrderByName();

    List<Recording> findAllByOrderByBand();

    List<Recording> findAllByOrderByYearOfCreation();

}
