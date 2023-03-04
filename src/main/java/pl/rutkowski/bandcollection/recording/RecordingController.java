package pl.rutkowski.bandcollection.recording;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.rutkowski.bandcollection.band.Band;
import pl.rutkowski.bandcollection.band.BandRepository;

import java.util.List;

@Controller
public class RecordingController {

    private final RecordingRepository recordingRepository;
    private final BandRepository bandRepository;
    private final RecordingService recordingService;

    public RecordingController(RecordingRepository recordingRepository, BandRepository bandRepository, RecordingService recordingService) {
        this.recordingRepository = recordingRepository;
        this.bandRepository = bandRepository;
        this.recordingService = recordingService;
    }

    @GetMapping("/recording")
    public String recording(Model model, @RequestParam(required = false) String sort) {
        List<Recording> recordingList = recordingRepository.findAll();
        List<Band> bandList = bandRepository.findAll();
        if (sort != null) {
            recordingList = recordingService.findAllSorted(sort);
        }
        model.addAttribute("recordingSort", recordingList);
        model.addAttribute("band", bandList);
        return "recordings";
    }

    @GetMapping("/band/{bandId}/add-recording")
    public String addRecording(Model model, @PathVariable Long bandId) {
        RecordingDto recordingDto = new RecordingDto();
        Band band = bandRepository.findById(bandId).orElseThrow();
        recordingDto.setBandId(bandId);
        model.addAttribute("recordingDto", recordingDto);
        model.addAttribute("band", band);
        return "addRecording";
    }

    @PostMapping("/add-recording")
    public String addRecording(RecordingDto recordingDto) {
        recordingService.addRecording(recordingDto);
        return "redirect:/band/" + recordingDto.getBandId();
    }

    @GetMapping("/band/{bandId}/recording")
    public String recording(Model model, @PathVariable Long bandId) {
        Recording recording = recordingRepository.findById(bandId).orElseThrow();
        Band band = bandRepository.findById(bandId).orElseThrow();
        model.addAttribute("recordings", recording);
        model.addAttribute("bands", band);
        return "bandRecordings";
    }

    @GetMapping("/recording/{id}/delete")
    public String delete(@PathVariable Long id) {
        recordingRepository.deleteById(id);
        return "redirect:/";
    }

}
