package pl.rutkowski.bandcollection.recording;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordingRepository extends JpaRepository<Recording, Long> {
}
