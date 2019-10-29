package com.homeedition.reservation.models;

import com.homeedition.reservation.forms.ReservationForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "roomId")
    private Room room;


    private String reservationDescription;
    private LocalDate date;
    private LocalTime timeFrom;
    private LocalTime timeTo;

    public static Reservation from(ReservationForm reservationForm) {
        return Reservation.builder()
                .date(reservationForm.getDate())
                .timeFrom(reservationForm.getTimeFrom())
                .timeTo(reservationForm.getTimeTo())
                .reservationDescription(reservationForm.getReservationDescription())
                .build();
    }

    /**
     * Compare time overlap
     *
     * @param target reservation with which we compare
     * @return check overlapped reservation
     */
    public boolean overlap(Reservation target) {

        if (timeFrom.equals(target.getTimeFrom()) && timeTo.equals(target.getTimeTo())) {
            return true;
        }
        if ((target.getTimeFrom().isAfter(timeFrom) || target.getTimeFrom().equals(timeFrom))
                && target.getTimeFrom().isBefore(timeTo)) {
            return true;
        }
        return target.getTimeTo().isAfter(timeFrom)
                && (target.getTimeTo().isBefore(timeTo) || target.getTimeTo().equals(timeTo));
    }

    /**
     * Index height for style
     * @return ratio duration
     */
    public long getRatioDuration() {
        long ratio = ChronoUnit.MINUTES.between(this.timeFrom, this.timeTo) / 30;
        return (ratio == 1) ? 2 : ratio;
    }

}
