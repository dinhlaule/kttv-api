package com.neo.kttvapi.repository;

import com.neo.kttvapi.entity.MessageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LogMessageRepository extends JpaRepository<MessageLog, Integer> {
    @Query(value = "SELECT MAX(ID) FROM MESSAGE", nativeQuery = true)
    Integer getMaxID();
}
