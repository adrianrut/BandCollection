package pl.rutkowski.bandcollection.band;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BandService {

    private final BandRepository bandRepository;

    public BandService(BandRepository bandRepository) {
        this.bandRepository = bandRepository;
    }

    public List<Band> findAllSorted(String sort) {
        if ("name".equals(sort)) {
            return bandRepository.findAllByOrderByName();
        } else if ("genre".equals(sort)) {
            return bandRepository.findAllByOrderByGenre();
        } else if ("yearOfCreation".equals(sort)) {
            return bandRepository.findAllByOrderByYearOfCreation();
        }
        return bandRepository.findAll();
    }
}
