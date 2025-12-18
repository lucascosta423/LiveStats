package com.LiveStats.LiveStats.controlles;

import com.LiveStats.LiveStats.dto.LoginsStats;
import com.LiveStats.LiveStats.dto.ResponseLoginsFinal;
import com.LiveStats.LiveStats.modulos.ixc.dto.ResponseLogins;
import com.LiveStats.LiveStats.modulos.ixc.enums.Status;
import com.LiveStats.LiveStats.service.ServiceAplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class controllers {
    private final ServiceAplication serviceAplication;

    public controllers(ServiceAplication serviceAplication) {
        this.serviceAplication = serviceAplication;
    }

    @GetMapping("/logins")
    public ResponseEntity<ResponseLoginsFinal> getLogins(@RequestParam(required = false) String status){
        return  ResponseEntity.ok().body(serviceAplication.getLogins(Status.valueOf(status)));
    }

}
