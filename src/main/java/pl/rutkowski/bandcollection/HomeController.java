package pl.rutkowski.bandcollection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.rutkowski.bandcollection.band.Band;
import pl.rutkowski.bandcollection.band.BandRepository;

import java.util.List;

@Controller
public class HomeController {

    private final BandRepository bandRepository;

    public HomeController(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Band> bandList = bandRepository.findAll();
        model.addAttribute("band", bandList);
        return "index";
    }
}
