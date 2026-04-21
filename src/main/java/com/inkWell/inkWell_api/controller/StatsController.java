package com.inkWell.inkWell_api.controller;

import com.inkWell.inkWell_api.dto.StatsResponseDTO;
import com.inkWell.inkWell_api.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
@CrossOrigin(origins = "*")
public class StatsController {

    @Autowired
    private StatsService service;

    @GetMapping
    public StatsResponseDTO getAllStats(){
        return service.getAllStats();
    }
}
