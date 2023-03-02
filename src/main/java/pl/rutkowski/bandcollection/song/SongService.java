package pl.rutkowski.bandcollection.song;

import org.springframework.stereotype.Service;
import pl.rutkowski.bandcollection.recording.Recording;
import pl.rutkowski.bandcollection.recording.RecordingRepository;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final RecordingRepository recordingRepository;

    public SongService(SongRepository songRepository, RecordingRepository recordingRepository) {
        this.songRepository = songRepository;
        this.recordingRepository = recordingRepository;
    }

    public void addSong(SongDto songDto) {
        Song song = new Song();
        Recording recording = recordingRepository.findById(songDto.getRecordingId()).orElseThrow();
        song.setRecording(recording);
        song.setName(songDto.getName());
        songRepository.save(song);
    }
}
