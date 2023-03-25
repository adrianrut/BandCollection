package pl.rutkowski.bandcollection.band;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BandController {

    private final BandRepository bandRepository;
    private final BandService bandService;

    public BandController(BandRepository bandRepository, BandService bandService) {
        this.bandRepository = bandRepository;
        this.bandService = bandService;
    }

    @GetMapping("/band/homepage")
    public String home(Model model) {
        List<Band> bandList = bandRepository.findAll();
        model.addAttribute("band", bandList);
        return "bandHomepage";
    }

    @GetMapping("/band/add")
    public String addBand(Model model) {
        BandDto bandDto = new BandDto();
        model.addAttribute("bandDto", bandDto);
        return "addBand";
    }

    @PostMapping("/band/add")
    public String addBand(BandDto bandDto) {
        bandService.addBand(bandDto);
        return "redirect:/";
    }

    @GetMapping("/band/{id}")
    public String band(@PathVariable Long id, Model model) {
        Band band = bandRepository.findById(id).orElseThrow();
        model.addAttribute("band", band);
        return "band";
    }

    @GetMapping("/band/{id}/delete")
    public String delete(@PathVariable Long id) {
        bandRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/band")
    public String bands(Model model, @RequestParam(required = false) String sort) {
        List<Band> bandList;
        if (sort != null) {
            bandList = bandService.findAllSorted(sort);
        } else {
            return "redirect:/";
        }
        model.addAttribute("bandSort", bandList);
        return "bandSorted";
    }
}
