package com.fis.booklibrary.casestudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fis.booklibrary.casestudy.model.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

}
