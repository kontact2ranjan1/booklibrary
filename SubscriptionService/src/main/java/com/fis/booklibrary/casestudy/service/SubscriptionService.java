package com.fis.booklibrary.casestudy.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fis.booklibrary.casestudy.model.Book;
import com.fis.booklibrary.casestudy.model.Subscription;
import com.fis.booklibrary.casestudy.repository.SubscriptionRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class SubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private RestTemplate restTemplate;

	public List<Subscription> getSubscriptions() {
		return subscriptionRepository.findAll();
	}

	@Transactional
	public Subscription addSubscription(Subscription subscription) {
		return subscriptionRepository.save(subscription);
	}

	public int getAvailableCopies(Subscription subscription) {
		Book book = (Book) restTemplate.getForObject("http://api-gateway-service/book-service/books/" + subscription.getBookId(), Book.class);
		return book.getCopiesAvailable();
	}

	@SuppressWarnings("unused")
	private ResponseEntity<String>  callBookServiceAndGetData_Fallback(Subscription subscription) {
        System.out.println("CIRCUIT BREAKER ENABLED!!! No Response From Book Service at this moment. " +
                " Service will be back shortly - " + new Date());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("CIRCUIT BREAKER ENABLED!!! No Response From Book Service at this moment. " +
                " Service will be back shortly - " + new Date());
        
    }

	@Transactional
	@HystrixCommand(fallbackMethod = "callBookServiceAndGetData_Fallback", commandProperties = {  
			  @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
			  @HystrixProperty(name ="circuitBreaker.requestVolumeThreshold", value="3"),
			  @HystrixProperty(name ="circuitBreaker.sleepWindowInMilliseconds", value="10000"),
			  @HystrixProperty(name ="circuitBreaker.errorThresholdPercentage", value="50"),
			  @HystrixProperty(name ="metrics.rollingStats.timeInMilliseconds", value="10000")
			  }) 
	public ResponseEntity<String> updateAvailableCopies(Subscription subscription) {
		int remainingCopies = getAvailableCopies(subscription);
		int updatedremainingcopies = 0;
		if(remainingCopies <= 0 && subscription.getDateReturned() == null ) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Book copies not available for subscription") ;
		}
		
		if (remainingCopies >= 0) {
			if (subscription.getDateReturned() == null) {
				updatedremainingcopies = remainingCopies - 1;
			} else {
				updatedremainingcopies = remainingCopies + 1;
			}
			restTemplate.put("http://api-gateway-service/book-service/books/"+subscription.getBookId(), updatedremainingcopies);
			return ResponseEntity.ok(subscription.toString());
			
		}
		
		return ResponseEntity.ok(null);
	}
}
