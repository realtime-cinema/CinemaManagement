package org.example.cinemamanagement.repository;

import org.example.cinemamanagement.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface BusinessRepository extends JpaRepository<Payment, UUID> {
    @Query(value = "SELECT p "
            + "FROM payment p "
            + "JOIN (SELECT cm.cinema_id"
            + "       FROM cinema_manager cm "
            + "       JOIN users u ON cm.user_id = u.id "
            + "       WHERE cm.user_id= ?1) as tmp ON p.cinema_id = tmp.cinema_id "
            + "WHERE DATE(p.dateCreate) BETWEEN DATE(?2) AND DATE(?3) "
            + "GROUP BY tmp.cinema_id", nativeQuery = true)
    public List<Object[]> getTotalAmountOfCinemaInMonth(UUID managerId, LocalDate thirtyDaysAgo, LocalDate today);

}
