package pl.rutkowski.bandcollection.song;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.rutkowski.bandcollection.band.BandRepository;
import pl.rutkowski.bandcollection.recording.Recording;
import pl.rutkowski.bandcollection.recording.RecordingRepository;

import java.util.List;

@Controller
public class SongController {

    private final SongRepository songRepository;
    private final RecordingRepository recordingRepository;

    public SongController(SongRepository songRepository, RecordingRepository recordingRepository) {
        this.songRepository = songRepository;
        this.recordingRepository = recordingRepository;
    }

    @GetMapping("/song")
    public String home(Model model) {
        List<Song> songList = songRepository.findAll();
        model.addAttribute("song", songList);
        return "song";
    }

    @GetMapping("/add_song")
    public String addBand(Model model) {
        Song song = new Song();
        List<Recording> recordingList = recordingRepository.findAll();
        model.addAttribute("recording", recordingList);
        model.addAttribute("song", song);
        return "addSong";
    }

    @PostMapping("/add_song")
    public String addSong(Song song) {
        songRepository.save(song);
        return "redirect:/";
    }

    @GetMapping("/song/{id}")
    public String song(@PathVariable Long id, Model model) {
        Song song = songRepository.findById(id).orElseThrow();
        model.addAttribute("song", song);
        return "song";
    }
}
