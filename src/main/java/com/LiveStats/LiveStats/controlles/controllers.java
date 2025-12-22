package com.LiveStats.LiveStats.controlles;

import com.LiveStats.LiveStats.dto.DashboardResponse;
import com.LiveStats.LiveStats.dto.ResponseLoginsFinal;
import com.LiveStats.LiveStats.modulos.ixc.enums.Status;
import com.LiveStats.LiveStats.service.ServiceAplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class controllers {
    private final ServiceAplication serviceAplication;

    public controllers(ServiceAplication serviceAplication) {
        this.serviceAplication = serviceAplication;
    }

    @GetMapping("/logins")
    public ResponseEntity<DashboardResponse> getLoginsOnline(){
        return  ResponseEntity.ok().body(serviceAplication.getDashboard());
    }


}
