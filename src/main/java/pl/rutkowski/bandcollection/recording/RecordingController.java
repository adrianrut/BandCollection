package pl.rutkowski.bandcollection.recording;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.rutkowski.bandcollection.band.Band;
import pl.rutkowski.bandcollection.band.BandRepository;

import java.util.List;

@Controller
public class RecordingController {

    private final RecordingRepository recordingRepository;
    private final BandRepository bandRepository;

    public RecordingController(RecordingRepository recordingRepository, BandRepository bandRepository) {
        this.recordingRepository = recordingRepository;
        this.bandRepository = bandRepository;
    }

    @GetMapping("/recording")
    public String home(Model model) {
        List<Recording> recordingList = recordingRepository.findAll();
        model.addAttribute("recording", recordingList);
        return "recording";
    }

    @GetMapping("/add_recording")
    public String addBand(Model model) {
        Recording recording = new Recording();
        List<Band> bandList = bandRepository.findAll();
        model.addAttribute("recording", recording);
        model.addAttribute("band", bandList);
        return "addRecording";
    }

    @PostMapping("/add_recording")
    public String addBand(Recording recording) {
        recordingRepository.save(recording);
        return "redirect:/";
    }

    @GetMapping("/recording/{id}")
    public String band(@PathVariable Long id, Model model) {
        Recording recording = recordingRepository.findById(id).orElseThrow();
        model.addAttribute("recording", recording);
        return "recording";
    }
}
