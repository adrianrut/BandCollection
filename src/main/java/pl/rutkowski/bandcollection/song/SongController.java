package pl.rutkowski.bandcollection.song;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("recording/{id}/song")
    public String home(Model model, @PathVariable Long id) {
        List<Song> songList = songRepository.findAll();
        Recording recording = recordingRepository.findById(id).orElseThrow();
        model.addAttribute("recording", recording);
        model.addAttribute("song", songList);
        return "song";
    }

    @GetMapping("recording/{id}/add-song")
    public String addBand(Model model, @PathVariable Long id) {
        Song song = new Song();
        Recording recording = recordingRepository.findById(id).orElseThrow();
        model.addAttribute("recording", recording);
        model.addAttribute("song", song);
        return "addSong";
    }

    @PostMapping("/add-song")
    public String addSong(Song song) {
        songRepository.save(song);
        return "redirect:/";
    }

}
