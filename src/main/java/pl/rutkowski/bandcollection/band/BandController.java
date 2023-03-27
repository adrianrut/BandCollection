package pl.rutkowski.bandcollection.band;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.rutkowski.bandcollection.user.UserService;

import java.util.List;

@Controller
@RequestMapping("/band")
public class BandController {

    private final BandRepository bandRepository;
    private final BandService bandService;
    private final UserService userService;

    public BandController(BandRepository bandRepository, BandService bandService, UserService userService) {
        this.bandRepository = bandRepository;
        this.bandService = bandService;
        this.userService = userService;
    }

    @GetMapping("/homepage")
    public String home(Model model) {
        List<Band> bandList = bandRepository.findAll();
        Long userId = userService.findUserId();
        model.addAttribute("band", bandList);
        model.addAttribute("userId", userId);
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

    @GetMapping("/{id}")
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

    @GetMapping("")
    public String bands(Model model, @RequestParam(required = false) String sort) {
        List<Band> bandList;
        if (sort != null) {
            bandList = bandService.findAllSorted(sort);
        } else {
            return "redirect:/band/homepage";
        }
        model.addAttribute("bandSort", bandList);
        return "bandSorted";
    }
}
