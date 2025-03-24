package com.shipment.facility.controller;



import com.shipment.facility.dto.LoadRequest;
import com.shipment.facility.dto.LoadResponse;
import com.shipment.facility.service.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/load")
public class LoadController {

    @Autowired
    private LoadService loadService;

    @PostMapping
    public ResponseEntity<LoadResponse> createLoad(@RequestBody LoadRequest request) {
        return ResponseEntity.status(201).body(loadService.createLoad(request));
    }

    @GetMapping
    public ResponseEntity<List<LoadResponse>> getLoads(@RequestParam UUID shipperId) {
        return ResponseEntity.ok(loadService.getLoadsByShipper(shipperId));
    }

    @GetMapping("/{loadId}")
    public ResponseEntity<LoadResponse> getLoadById(@PathVariable UUID loadId) {
        return ResponseEntity.ok(loadService.getLoadById(loadId));
    }

    @PutMapping("/{loadId}")
    public ResponseEntity<LoadResponse> updateLoad(@PathVariable UUID loadId, @RequestBody LoadRequest request) {
        return ResponseEntity.ok(loadService.updateLoad(loadId, request));
    }

    @DeleteMapping("/{loadId}")
    public ResponseEntity<Void> deleteLoad(@PathVariable UUID loadId) {
        loadService.deleteLoad(loadId);
        return ResponseEntity.noContent().build();
    }
}

