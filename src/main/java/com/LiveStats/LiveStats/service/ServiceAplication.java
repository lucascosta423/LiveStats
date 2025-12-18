package com.LiveStats.LiveStats.service;

import com.LiveStats.LiveStats.dto.LoginsStats;
import com.LiveStats.LiveStats.dto.ResponseLoginsFinal;
import com.LiveStats.LiveStats.modulos.ixc.dto.Cliente;
import com.LiveStats.LiveStats.modulos.ixc.dto.Login;
import com.LiveStats.LiveStats.modulos.ixc.dto.ResponseLogins;
import com.LiveStats.LiveStats.modulos.ixc.enums.Status;
import com.LiveStats.LiveStats.modulos.ixc.service.IxcService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ServiceAplication {

    private final IxcService ixcService;

    public ServiceAplication(IxcService ixcService) {
        this.ixcService = ixcService;
    }


    public ResponseLoginsFinal getLogins(Status status) {

        List<Cliente> clientes =
                ixcService.getAllClientsByStatus(Status.S).registros();


        ResponseLogins loginsByStatus = ixcService.getLoginsByStatus(status);

        List<Login> logins = loginsByStatus.registros();


        Map<String, Cliente> clientesPorId = clientes.stream()
                .collect(Collectors.toMap(
                        Cliente::id,
                        Function.identity()
                ));

        List<LoginsStats> resultado = new ArrayList<>();

        for (Login login : logins) {
            Cliente cliente = clientesPorId.get(login.id_cliente());
            if (cliente != null) {
                resultado.add(new LoginsStats(login.online()
                        , login.login(),
                        cliente.bairro(),
                        login.id_cliente(),
                        login.ultima_conexao_inicial(),
                        login.ultima_conexao_final()));
            }
        }

        return new ResponseLoginsFinal(loginsByStatus.total(),resultado);
    }




}
