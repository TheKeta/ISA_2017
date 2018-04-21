package com.reservationapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationapp.model.History;
import com.reservationapp.model.User;

public interface HistoryRepository extends JpaRepository<History, Long>{

	History findOneByUser(User user);
}
