package pl.rutkowski.bandcollection.musician;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicianService {

    private final MusicianRepository musicianRepository;

    public MusicianService(MusicianRepository musicianRepository) {
        this.musicianRepository = musicianRepository;
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
}
