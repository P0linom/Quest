package com.homeedition.reservation.services;

import com.homeedition.reservation.exceptions.ReservationException;
import com.homeedition.reservation.forms.ReservationForm;
import com.homeedition.reservation.models.Reservation;
import com.homeedition.reservation.models.Room;
import com.homeedition.reservation.repositories.ReservationRepository;
import com.homeedition.reservation.repositories.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReservationService {

    private final RoomRepository roomRepository;

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }

    public List<Reservation> findReservationInWeek(ArrayList<LocalDate> dates) {
        return this.reservationRepository
            .findAllByDateIn(
                dates);
    }

    public Room getRoomById(Long id) {
        return this.roomRepository
                .findOneById(id);
    }

    /**
     * Check date and save Reservation
     */
    public Reservation reserve(Reservation reservation) {
        boolean overlap = this.reservationRepository
                .findAllByDate(reservation.getDate())
                .stream().anyMatch(x -> x.overlap(reservation));
        if (overlap) {
            throw new ReservationException.AlreadyReserved("Already reserved. Overlap.");
        }
        return reservationRepository.save(reservation);
    }

    public List<Room> getAllRoom() {
        return this.roomRepository
                .findAll();
    }

    public Room getFirstRoom() {
        return this.roomRepository
                .findFirstByOrderByIdAsc();
    }

    /**
     * Date validation
     */
    public boolean checkDateTime(ReservationForm reservationForm) throws ReservationException {
        if (reservationForm.getDate().isBefore(LocalDate.now())) {
            throw new ReservationException.DateOrTimeIncorrect("Date Or Time Incorrect. Time travel is forbidden in our reality.");
        }
        if (reservationForm.getDate().equals(LocalDate.now()) && reservationForm.getTimeFrom().plusMinutes(10).isBefore(LocalTime.now())) {
            throw new ReservationException.DateOrTimeIncorrect("Date Or Time Incorrect. Time travel is forbidden in our reality.");
        }
        if (reservationForm.getTimeFrom().equals(reservationForm.getTimeTo())
                || reservationForm.getTimeFrom().isAfter(reservationForm.getTimeTo())) {
            throw new ReservationException.DateOrTimeIncorrect("Date Or Time Incorrect");
        }
        return true;
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.getOne(id);
    }

}
