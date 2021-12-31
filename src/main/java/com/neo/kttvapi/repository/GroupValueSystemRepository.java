package com.neo.kttvapi.repository;

import com.neo.kttvapi.entity.GroupValueSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupValueSystemRepository extends JpaRepository<GroupValueSystem, Integer> {
    List<GroupValueSystem> findByGroupName(String groupName);
}
