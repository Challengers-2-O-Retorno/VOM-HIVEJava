package com.example.VOM_HiveJava.repository;

import com.example.VOM_HiveJava.entity.Pay_hist;
import com.example.VOM_HiveJava.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
