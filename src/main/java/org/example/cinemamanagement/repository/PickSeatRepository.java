package org.example.cinemamanagement.repository;

import org.example.cinemamanagement.model.PickSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PickSeatRepository extends JpaRepository<PickSeat, UUID> {
    @Query("SELECT p FROM PickSeat p WHERE p.user.id= ?1")
    List<PickSeat> findByUserId(UUID userID);

    @Query("SELECT p FROM PickSeat p WHERE p.perform.id= ?1 and p.x= ?2 and p.y= ?3")
    Optional<PickSeat> findByPerformIdAndXAndY(UUID performId, Integer x, Integer y);

    List<PickSeat> findByPerformId(UUID performId);
}
