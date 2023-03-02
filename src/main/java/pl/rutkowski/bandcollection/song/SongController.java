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

    private final SongService songService;

    public SongController(SongRepository songRepository, RecordingRepository recordingRepository, SongService songService) {
        this.songRepository = songRepository;
        this.recordingRepository = recordingRepository;
        this.songService = songService;
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
    public String addSong(Model model, @PathVariable Long id) {
        SongDto songDto = new SongDto();
        Recording recording = recordingRepository.findById(id).orElseThrow();
        songDto.setRecordingId(recording.getId());
        model.addAttribute("recording", recording);
        model.addAttribute("songDto", songDto);
        return "addSong";
    }

    @PostMapping("/add-song")
    public String addSong(SongDto songDto) {
        songService.addSong(songDto);
        return "redirect:/band/" + songDto.getRecordingId() + "/recording";
    }

}
