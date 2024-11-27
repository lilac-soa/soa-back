package com.lilac.soaback.tourist.service.impl;

import com.lilac.soaback.brewery.exception.NotFoundBreweryException;
import com.lilac.soaback.brewery.repository.BreweryRepository;
import com.lilac.soaback.entity.Brewery;
import com.lilac.soaback.entity.BreweryTourist;
import com.lilac.soaback.entity.BreweryTouristImage;
import com.lilac.soaback.tourist.dto.response.TouristResponse;
import com.lilac.soaback.tourist.exception.NotFoundTouristException;
import com.lilac.soaback.tourist.repository.TouristRepository;
import com.lilac.soaback.tourist.service.TouristService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TouristServiceImpl implements TouristService {
    private final TouristRepository touristRepository;
    private final BreweryRepository breweryRepository;

    @Transactional(readOnly = true)
    @Override
    public TouristResponse getTourist(String id) {
        UUID uuid = UUID.fromString(id);
        BreweryTourist breweryTourist = touristRepository.findById(uuid).orElseThrow(() -> new NotFoundTouristException(id +"의 여행지를 발견하지 못하였습니다."));

        List<String> imageList = new ArrayList<>();
        for(BreweryTouristImage  breweryTouristImage : breweryTourist.getBreweryTouristImageList()){
            imageList.add(breweryTouristImage.getImage().getUrl());
        }

        return TouristResponse.builder()
                .id(breweryTourist.getId())
                .touristName(breweryTourist.getTouristName())
                .type(breweryTourist.getType())
                .address(breweryTourist.getAddress())
                .content(breweryTourist.getContent())
                .imageList(imageList)
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public List<BreweryTourist> getTourists(String breweryId) {
        Brewery brewery = breweryRepository
                .findById(UUID.fromString(breweryId))
                .orElseThrow(()-> new NotFoundBreweryException("brewery not found :" + breweryId));

        return touristRepository.findBreweryTouristsByBrewery(brewery);
    }


}
