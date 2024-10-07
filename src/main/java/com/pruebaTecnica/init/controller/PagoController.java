package com.pruebaTecnica.init.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebaTecnica.init.entities.PagoRequest;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/procesar")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<?> procesarPagos(@RequestBody PagoRequest pagoRequest) {
        jdbcTemplate.update("CALL CobrarPagosPendientes(?, ?, ?)", 
                            pagoRequest.getFechaActual(), 
                            pagoRequest.getTasaInteres(), 
                            pagoRequest.getDiasAnioComercial());

        List<Map<String, Object>> pagos = jdbcTemplate.queryForList("SELECT * FROM loans WHERE Status = 'Pagado'");
        List<Map<String, Object>> cuentas = jdbcTemplate.queryForList("SELECT Client, Amount FROM Accounts WHERE Status = 'Activa'");

        Map<String, Object> response = new HashMap<>();
        response.put("Pagos", pagos);
        response.put("Cuentas", cuentas);

        return ResponseEntity.ok(response);
    }
}
