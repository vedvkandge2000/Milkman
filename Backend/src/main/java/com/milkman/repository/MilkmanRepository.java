package com.milkman.repository;

import com.milkman.model.Milkman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MilkmanRepository extends JpaRepository<Milkman, UUID> {
}
