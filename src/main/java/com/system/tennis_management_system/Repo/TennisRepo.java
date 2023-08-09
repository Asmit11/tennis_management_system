package com.system.tennis_management_system.Repo;

import com.system.tennis_management_system.entity.Tennis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TennisRepo extends JpaRepository <Tennis, Integer>{
}
