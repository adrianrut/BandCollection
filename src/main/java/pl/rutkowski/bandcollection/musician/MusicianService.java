package pl.rutkowski.bandcollection.musician;

import org.springframework.stereotype.Service;
import pl.rutkowski.bandcollection.band.Band;
import pl.rutkowski.bandcollection.band.BandRepository;

import java.util.List;

@Service
public class MusicianService {

    private final MusicianRepository musicianRepository;
    private final BandRepository bandRepository;

    public MusicianService(MusicianRepository musicianRepository, BandRepository bandRepository) {
        this.musicianRepository = musicianRepository;
        this.bandRepository = bandRepository;
    }

    public List<Musician> findAllSorted(String sort) {
        if ("name".equals(sort)) {
            return musicianRepository.findAllByOrderByName();
        } else if ("band".equals(sort)) {
            return musicianRepository.findAllByOrderByBands();
        } else if ("role".equals(sort)) {
            return musicianRepository.findAllByOrderByRole();
        }
        return musicianRepository.findAll();
    }

    public void addMusician(MusicianDto musicianDto) {
        Musician musician = new Musician();
        musician.setName(musicianDto.getName());
        List<Band> bandList = List.of(bandRepository.findById(musicianDto.getBandId()).orElseThrow());
        musician.setBands(bandList);
        musician.setRole(musicianDto.getRole());
        musicianRepository.save(musician);
    }
}
