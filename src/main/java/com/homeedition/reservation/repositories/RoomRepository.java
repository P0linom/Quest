package com.homeedition.reservation.repositories;

import com.homeedition.reservation.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findOneById(Long id);

    Room findFirstByOrderByIdAsc();
}