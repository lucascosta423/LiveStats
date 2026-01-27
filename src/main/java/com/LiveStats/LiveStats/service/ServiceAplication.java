package com.LiveStats.LiveStats.service;

import com.LiveStats.LiveStats.dto.DashboardResponse;
import com.LiveStats.LiveStats.dto.LoginsStats;
import com.LiveStats.LiveStats.dto.ResponseLoginsFinal;
import com.LiveStats.LiveStats.modulos.ixc.dto.Cliente;
import com.LiveStats.LiveStats.modulos.ixc.dto.Login;
import com.LiveStats.LiveStats.modulos.ixc.dto.ResponseLogins;
import com.LiveStats.LiveStats.modulos.ixc.enums.Status;
import com.LiveStats.LiveStats.modulos.ixc.service.IxcService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ServiceAplication {

    private final IxcService ixcService;

    public ServiceAplication(IxcService ixcService) {
        this.ixcService = ixcService;
    }


    public DashboardResponse getDashboard() {

        List<Login> todosLogins =
                ixcService.getAllLogins().registros();

        List<Cliente> clientes =
                ixcService.getAllClientsByStatus(Status.S).registros();

        Map<String, Cliente> clientesPorId =
                clientes.stream()
                        .collect(Collectors.toMap(Cliente::id, Function.identity()));

        Map<Status, List<Login>> porStatus =
                todosLogins.stream()
                        .collect(Collectors.groupingBy(
                                login -> Status.from(login.online())
                        ));

        ResponseLoginsFinal online =
                montarResponse(porStatus.get(Status.S), clientesPorId,
                        Login::ultima_conexao_inicial);

        ResponseLoginsFinal offline =
                montarResponse(porStatus.get(Status.N), clientesPorId,
                        Login::ultima_conexao_final);

        ResponseLoginsFinal semStatus =
                montarResponse(porStatus.get(Status.SS), clientesPorId, null);

        return new DashboardResponse(online, offline, semStatus);
    }

    private ResponseLoginsFinal montarResponse(
            List<Login> logins,
            Map<String, Cliente> clientes,
            Function<Login, String> campoOrdenacao
    ) {
        if (logins == null) logins = List.of();

        if (campoOrdenacao != null) {
            logins = ordenarPorConexao(logins, campoOrdenacao);
        }

        List<LoginsStats> resultado = logins.stream()
                .limit(500)
                .map(login -> {
                    Cliente cliente = clientes.get(login.id_cliente());
                    if (cliente == null) return null;
                    return new LoginsStats(
                            login.online(),
                            login.login(),
                            cliente.bairro(),
                            login.id_cliente(),
                            login.ultima_conexao_inicial(),
                            login.ultima_conexao_final()
                    );
                })
                .filter(Objects::nonNull)
                .toList();

        return new ResponseLoginsFinal(logins.size(), resultado);
    }



    private List<Login> ordenarPorConexao(
            List<Login> logins,
            Function<Login, String> campoData
    ) {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return logins.stream()
                .sorted(
                        Comparator.comparing(
                                (Login login) -> {
                                    String data = campoData.apply(login);
                                    if (data == null || data.equals("0000-00-00 00:00:00")) {
                                        return null;
                                    }
                                    return LocalDateTime.parse(data, formatter);
                                },
                                Comparator.nullsLast(Comparator.naturalOrder())
                        ).reversed()
                )
                .toList();
    }






}
