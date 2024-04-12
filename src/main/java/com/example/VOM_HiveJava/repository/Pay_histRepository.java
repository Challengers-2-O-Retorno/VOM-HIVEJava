package com.example.VOM_HiveJava.repository;

import com.example.VOM_HiveJava.entity.Pay_hist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Pay_histRepository extends JpaRepository<Pay_hist, Long> {
}
