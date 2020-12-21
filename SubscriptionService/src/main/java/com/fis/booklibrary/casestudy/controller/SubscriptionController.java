package com.fis.booklibrary.casestudy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fis.booklibrary.casestudy.model.Subscription;
import com.fis.booklibrary.casestudy.service.SubscriptionService;

@RestController
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;

	@RequestMapping(value="/subscriptions", method = RequestMethod.GET )
	public ResponseEntity<List<Subscription>> getSubscriptions(){
		return new ResponseEntity<>(subscriptionService.getSubscriptions(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/subscriptions", method = RequestMethod.POST )
	public ResponseEntity<Subscription> postSubscription(@RequestBody Subscription subscription){
		HttpStatus str = subscriptionService.updateAvailableCopies(subscription).getStatusCode();
		//System.out.println("========str value ================"+str);
		if(str !=null && str.equals(HttpStatus.UNPROCESSABLE_ENTITY)) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<>(subscriptionService.addSubscription(subscription), HttpStatus.CREATED);
	}
}
