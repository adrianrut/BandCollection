package pl.rutkowski.bandcollection.musician;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.rutkowski.bandcollection.band.Band;
import pl.rutkowski.bandcollection.band.BandRepository;

import java.util.List;

@Controller
public class MusicianController {

    private final MusicianRepository musicianRepository;
    private final BandRepository bandRepository;
    private final MusicianService musicianService;

    public MusicianController(MusicianRepository musicianRepository, BandRepository bandRepository, MusicianService musicianService) {
        this.musicianRepository = musicianRepository;
        this.bandRepository = bandRepository;
        this.musicianService = musicianService;
    }

    @GetMapping("/musician")
    public String musician(Model model, @RequestParam(required = false) String sort) {
        List<Musician> musicianList = musicianRepository.findAll();
        List<Band> bandList = bandRepository.findAll();
        if (sort != null) {
            musicianList = musicianService.findAllSorted(sort);
        }
        model.addAttribute("musicianSort", musicianList);
        model.addAttribute("band", bandList);
        return "musician";
    }

    @GetMapping("/band/{bandId}/add-musician")
    public String addMusician(Model model, @PathVariable Long bandId) {
        MusicianDto musicianDto = new MusicianDto();
        musicianDto.setBandId(bandId);
        List<Musician> musicianList = musicianRepository.findAll();
        Band band = bandRepository.findById(bandId).orElseThrow();
        model.addAttribute("musicianDto", musicianDto);
        model.addAttribute("musicianList", musicianList);
        model.addAttribute("band", band);
        return "addMusician";
    }

    @PostMapping("/add-musician")
    public String addMusician(MusicianDto musicianDto) {
        musicianService.addMusician(musicianDto);
        return "redirect:/band/" + musicianDto.getBandId();
    }

    @PostMapping("/add-new-musician")
    public String addNewMusician(MusicianDto musicianDto) {
        musicianService.addMusician(musicianDto);
        return "redirect:/band/" + musicianDto.getBandId();
    }

    @GetMapping("/musician/{id}/delete")
    public String delete(@PathVariable Long id) {
        musicianRepository.deleteById(id);
        return "redirect:/";
    }

}
