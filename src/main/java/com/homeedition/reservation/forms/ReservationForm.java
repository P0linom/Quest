package com.homeedition.reservation.forms;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;


@Data
public class ReservationForm {

    private Long roomId;
    private String reservationDescription;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull(message = "Not Empty")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime timeFrom;

    @NotNull(message = "Not Empty")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime timeTo;
}
