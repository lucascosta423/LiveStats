package com.LiveStats.LiveStats.controlles;

import com.LiveStats.LiveStats.modulos.ixc.dto.ResponseTotal;
import com.LiveStats.LiveStats.modulos.ixc.service.IxcService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class IxcControllers {
    private final IxcService ixcService;

    public IxcControllers(IxcService ixcService) {
        this.ixcService = ixcService;
    }

    @GetMapping("/clientes")
    public ResponseEntity<ResponseTotal> getTotal(){
        return  ResponseEntity.ok().body(ixcService.getAllActiveCustomers());
    }


}
