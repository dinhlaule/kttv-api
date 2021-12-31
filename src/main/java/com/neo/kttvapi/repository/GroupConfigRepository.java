package com.neo.kttvapi.repository;

import com.neo.kttvapi.entity.GroupConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupConfigRepository extends JpaRepository<GroupConfig , Integer> {

}
