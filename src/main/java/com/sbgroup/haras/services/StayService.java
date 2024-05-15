package com.sbgroup.haras.services;

import com.sbgroup.haras.dtos.StayDTO;
import com.sbgroup.haras.models.Animal;
import com.sbgroup.haras.models.Stay;
import com.sbgroup.haras.models.User;
import com.sbgroup.haras.repositories.AnimalRepository;
import com.sbgroup.haras.repositories.StayRepository;
import com.sbgroup.haras.utils.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StayService {

    @Autowired
    private StayRepository stayRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Transactional
    public Stay createStay(StayDTO stayDTO, User authUser, UUID animalId) {
        var newStay = new Stay();
        BeanUtils.copyProperties(stayDTO, newStay);
        newStay.setCreatedAt(TimeUtil.getCurrentTimestamp());
        newStay.setResponsibleName(authUser);

        Optional<Animal> animal = animalRepository.findById(animalId);
        newStay.setAnimal(animal.get());

        return stayRepository.save(newStay);
    }

    @Transactional
    public Optional<Stay> updateStayById(StayDTO stayDTO, UUID stayId, User authUser, UUID animalId) {
        var stay = stayRepository.findById(stayId);

        if (stay.isEmpty()) {
            return Optional.empty();
        }

        var updatedStay = stay.get();
        BeanUtils.copyProperties(stayDTO, updatedStay);
        updatedStay.setUpdateAt(TimeUtil.getCurrentTimestamp());
        updatedStay.setUpdateBy(authUser);

        Optional<Animal> animal = animalRepository.findById(animalId);
        updatedStay.setAnimal(animal.get());

        return Optional.of(stayRepository.save(updatedStay));
    }

    @Transactional
    public Optional<Stay> deleteStayById(UUID stayId) {
        var stay = stayRepository.findById(stayId);

        if (stay.isEmpty()) {
            return Optional.empty();
        }

        stayRepository.delete(stay.get());
        return stay;
    }

    public List<Stay> getAllStays(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Stay> staysPage = stayRepository.findAll(pageable);

        return staysPage.getContent();
    }

    public long getStaysTotalElements() {
        return stayRepository.count();
    }

    public int getStaysTotalPages(int size) {
        Pageable pageable = PageRequest.of(0, size);
        long totalElements = getStaysTotalElements();

        return (int) Math.ceil((double) totalElements / (double) size);
    }

    public Optional<Stay> getStayById(UUID stayId) {
        return stayRepository.findById(stayId);
    }
}
