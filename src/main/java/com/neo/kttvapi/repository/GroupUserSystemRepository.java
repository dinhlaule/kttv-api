package com.neo.kttvapi.repository;


import com.neo.kttvapi.entity.GroupUserSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface GroupUserSystemRepository extends JpaRepository<GroupUserSystem, Integer> {
    Optional<GroupUserSystem > findById(Integer id);


}
