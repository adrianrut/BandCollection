package pl.rutkowski.bandcollection.recording;

import org.springframework.stereotype.Service;
import pl.rutkowski.bandcollection.band.Band;
import pl.rutkowski.bandcollection.band.BandRepository;

import java.util.List;

@Service
public class RecordingService {

    private final RecordingRepository recordingRepository;
    private final BandRepository bandRepository;

    public RecordingService(RecordingRepository recordingRepository, BandRepository bandRepository) {
        this.recordingRepository = recordingRepository;
        this.bandRepository = bandRepository;
    }

    public List<Recording> findAllSorted(String sort) {
        if ("name".equals(sort)) {
            return recordingRepository.findAllByOrderByName();
        } else if ("band".equals(sort)) {
            return recordingRepository.findAllByOrderByBand();
        } else if ("yearOfCreation".equals(sort)) {
            return recordingRepository.findAllByOrderByYearOfCreation();
        }
        return recordingRepository.findAll();
    }

    public void addRecording(RecordingDto recordingDto) {
        Recording recording = new Recording();
        recording.setName(recordingDto.getName());
        Band band = bandRepository.findById(recordingDto.getBandId()).orElseThrow();
        recording.setBand(band);
        recording.setYearOfCreation(recordingDto.getYearOfCreation());
        recordingRepository.save(recording);
    }
}
