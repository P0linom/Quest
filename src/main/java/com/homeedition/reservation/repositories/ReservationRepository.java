package com.homeedition.reservation.repositories;

import com.homeedition.reservation.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    List<Reservation> findAllByDateIn(ArrayList<LocalDate> date);

    @Lock(LockModeType.PESSIMISTIC_READ)
    List<Reservation> findAllByDate(LocalDate date);


}