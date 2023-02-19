package pl.rutkowski.bandcollection.musician;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.rutkowski.bandcollection.band.Band;
import pl.rutkowski.bandcollection.band.BandRepository;

import java.util.List;

@Controller
public class MusicianController {

    private final MusicianRepository musicianRepository;
    private final BandRepository bandRepository;

    public MusicianController(MusicianRepository musicianRepository, BandRepository bandRepository) {
        this.musicianRepository = musicianRepository;
        this.bandRepository = bandRepository;
    }

    @GetMapping("/musician")
    public String home(Model model) {
        List<Musician> musicianList = musicianRepository.findAll();
        model.addAttribute("musicians", musicianList);
        return "musician";
    }

    @GetMapping("/add_musician")
    public String addBand(Model model) {
        Musician musician = new Musician();
        List<Band> bandList = bandRepository.findAll();
        model.addAttribute("musician", musician);
        model.addAttribute("band", bandList);
        return "addMusician";
    }

    @PostMapping("/add_musician")
    public String addBand(Musician musician) {
        musicianRepository.save(musician);
        return "redirect:/";
    }

}
