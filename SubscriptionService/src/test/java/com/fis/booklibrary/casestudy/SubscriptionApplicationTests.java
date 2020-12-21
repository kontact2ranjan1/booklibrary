package com.fis.booklibrary.casestudy;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fis.booklibrary.casestudy.model.Subscription;
import com.fis.booklibrary.casestudy.repository.SubscriptionRepository;
import com.fis.booklibrary.casestudy.service.SubscriptionService;

@SpringBootTest
class SubscriptionApplicationTests {

	@Autowired
	private SubscriptionService subscriptionService;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void getSubscription() {
		Subscription sub = new Subscription((long) 1, "Sailesh", "17-AUG-2020", null, "B1313");
		subscriptionRepository.save(sub);
		
		List<Subscription> subscriptions = subscriptionService.getSubscriptions();
		
		assertThat(!subscriptions.isEmpty());
		
		assertThat(subscriptions.get(0).getSubscriberName()).isEqualTo("Sailesh");
		
	}
	
	@Test
	public void addSubscription() {
		Subscription sub = new Subscription((long) 1, "Sailesh", "17-AUG-2020", null, "B1313");
		subscriptionService.addSubscription(sub);
		
		assertThat(!subscriptionRepository.findAll().isEmpty()) ;
		
	}
	
	@Test
	public void addSubscriptionNotFound() {
		Subscription sub1 = new Subscription((long) 1, "Sailesh", "17-AUG-2020", null, "B1313");
		subscriptionService.addSubscription(sub1);
	
		Subscription sub2 = new Subscription((long) 2, "Sailesh", "17-AUG-2020", null, "B1313");
		subscriptionService.addSubscription(sub2);
		
		Subscription sub3 = new Subscription((long) 3, "Sailesh", "17-AUG-2020", null, "B1313");
		subscriptionService.addSubscription(sub3);
		
		Subscription sub4 = new Subscription((long) 4, "Sailesh", "17-AUG-2020", null, "B1313");
		subscriptionService.addSubscription(sub4);
		
		Subscription sub5 = new Subscription((long) 5, "Sailesh", "17-AUG-2020", null, "B1313");
		subscriptionService.addSubscription(sub5);
		
		Subscription sub6 = new Subscription((long) 6, "Sailesh", "17-AUG-2020", null, "B1313");
		subscriptionService.addSubscription(sub6);
		
		//assertThat(!subscriptionRepository.findAll().isEmpty()) ;
		
	}
}
