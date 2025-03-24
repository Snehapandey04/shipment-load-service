package com.shipment.facility.service;



import com.shipment.facility.dto.FacilityRequest;
import com.shipment.facility.dto.LoadRequest;
import com.shipment.facility.dto.LoadResponse;
import com.shipment.facility.model.Facility;
import com.shipment.facility.model.Load;
import com.shipment.facility.repository.LoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LoadService {

    @Autowired
    private LoadRepository loadRepository;

    public LoadResponse createLoad(LoadRequest request) {
        Load load = new Load();
        load.setId(UUID.randomUUID());
        load.setProductType(request.getProductType());
        load.setTruckType(request.getTruckType());
        load.setNoOfTrucks(request.getNoOfTrucks());
        load.setWeight(request.getWeight());
        load.setComment(request.getComment());
        load.setShipperId(request.getShipperId());
        load.setDate(request.getDate());

        Facility facility = new Facility();
        facility.setLoadingPoint(request.getFacility().getLoadingPoint());
        facility.setUnloadingPoint(request.getFacility().getUnloadingPoint());
        facility.setLoadingDate(request.getFacility().getLoadingDate());
        facility.setUnloadingDate(request.getFacility().getUnloadingDate());

        load.setFacility(facility);
        facility.setLoad(load);

        Load savedLoad = loadRepository.save(load);
        return mapToResponse(savedLoad);
    }

    public List<LoadResponse> getLoadsByShipper(UUID shipperId) {
        return loadRepository.findByShipperId(shipperId)
                .stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public LoadResponse getLoadById(UUID loadId) {
        Load load = loadRepository.findById(loadId).orElseThrow(() -> new RuntimeException("Load not found"));
        return mapToResponse(load);
    }

    public LoadResponse updateLoad(UUID loadId, LoadRequest request) {
        Load existingLoad = loadRepository.findById(loadId).orElseThrow(() -> new RuntimeException("Load not found"));
        existingLoad.setProductType(request.getProductType());
        existingLoad.setTruckType(request.getTruckType());
        existingLoad.setNoOfTrucks(request.getNoOfTrucks());
        existingLoad.setWeight(request.getWeight());
        existingLoad.setComment(request.getComment());
        existingLoad.setShipperId(request.getShipperId());
        existingLoad.setDate(request.getDate());

        Facility facility = existingLoad.getFacility();
        facility.setLoadingPoint(request.getFacility().getLoadingPoint());
        facility.setUnloadingPoint(request.getFacility().getUnloadingPoint());
        facility.setLoadingDate(request.getFacility().getLoadingDate());
        facility.setUnloadingDate(request.getFacility().getUnloadingDate());

        Load updatedLoad = loadRepository.save(existingLoad);
        return mapToResponse(updatedLoad);
    }

    public void deleteLoad(UUID loadId) {
        loadRepository.deleteById(loadId);
    }

    private LoadResponse mapToResponse(Load load) {
        Facility facility = load.getFacility();
        return new LoadResponse(
                load.getId(),
                new FacilityRequest(facility.getLoadingPoint(), facility.getUnloadingPoint(),
                        facility.getLoadingDate(), facility.getUnloadingDate()),
                load.getProductType(), load.getTruckType(), load.getNoOfTrucks(),
                load.getWeight(), load.getComment(), load.getShipperId(), load.getDate());
    }
}

