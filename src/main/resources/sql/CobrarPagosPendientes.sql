DELIMITER $$

CREATE PROCEDURE CobrarPagosPendientes(fecha_actual DATE, tasa_interes DECIMAL(5, 2), dias_anio_comercial INT)
BEGIN
    DECLARE fin INT DEFAULT 0;
    DECLARE cliente_id CHAR(36);
    DECLARE cuenta_saldo DECIMAL(10, 2);
    DECLARE prestamo_id INT;
    DECLARE prestamo_monto DECIMAL(10, 2);
    DECLARE plazo INT;
    DECLARE interes DECIMAL(10, 2);
    DECLARE iva DECIMAL(10, 2);
    DECLARE pago DECIMAL(10, 2);
    DECLARE sucursal_iva DECIMAL(5, 2);

    DECLARE cur CURSOR FOR
        SELECT l.Client, l.Id, l.Amount, DATEDIFF(fecha_actual, l.Date_Loan) AS Plazo, s.IVA, a.Amount AS Cuenta_Saldo
        FROM loans l
        JOIN Accounts a ON l.Client = a.Client
        JOIN sucursales s ON l.IdSucursal = s.ID
        WHERE l.Status = 'Pendiente' AND a.Status = 'Activa'
        ORDER BY l.Date_Loan ASC;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;

    OPEN cur;
    REPEAT
        FETCH cur INTO cliente_id, prestamo_id, prestamo_monto, plazo, sucursal_iva, cuenta_saldo;
        IF NOT fin THEN
            SET interes = ROUND((prestamo_monto * plazo * tasa_interes) / dias_anio_comercial, 2);
            SET iva = ROUND(interes * (sucursal_iva / 100), 2);
            SET pago = prestamo_monto + interes + iva;

            IF cuenta_saldo >= pago THEN
                -- Actualizar estado del préstamo
                UPDATE loans SET Status = 'Pagado' WHERE Id = prestamo_id;

                -- Descontar saldo en la cuenta
                UPDATE Accounts SET Amount = Amount - pago WHERE Client = cliente_id;
            END IF;
        END IF;
    UNTIL fin END REPEAT;

    CLOSE cur;
END$$

DELIMITER ;
