package pl.rutkowski.bandcollection.recording;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordingService {

    private final RecordingRepository recordingRepository;

    public RecordingService(RecordingRepository recordingRepository) {
        this.recordingRepository = recordingRepository;
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
}
