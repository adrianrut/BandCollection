package pl.rutkowski.bandcollection.band;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BandController {

    private final BandRepository bandRepository;

    public BandController(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Band> bandList = bandRepository.findAll();
        model.addAttribute("band", bandList);
        return "index";
    }

    @GetMapping("/add_band")
    public String addBand(Model model) {
        Band band = new Band();
        model.addAttribute("band", band);
        return "addBand";
    }

    @PostMapping("/add_band")
    public String addBand(Band band) {
        bandRepository.save(band);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String band(@PathVariable Long id, Model model) {
        Band band = bandRepository.findById(id).orElseThrow();
        model.addAttribute("band", band);
        return "band";
    }
}
