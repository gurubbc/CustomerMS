package com.ofss;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CustomerController {
	
	
	@Autowired
	RestTemplate rt;
	
	Address a1=new Address(5,"Golden Globe Apt","Bangalore",560102);
	int[] stockList1= {1,2};
	Customer c1=new Customer(1, "Guru", a1, stockList1);

	Address a2=new Address(23,"Vaswani Techno","Bangalore",560155L);
	int[] stockList2= {2,3};
	Customer c2=new Customer(2, "Peter", a2, stockList2);

	Address a3=new Address(14,"SRH Building","Mumbai",234552L);
	int[] stockList3= {3,4,5};
	Customer c3=new Customer(3, "Mukul", a3, stockList3);

	Address a4=new Address(500,"Mantri Apt","Bangalore",560105L);
	int[] stockList4= {4,5};
	Customer c4=new Customer(4, "Deepak", a4, stockList4);

	Address a5=new Address(104,"Shobha Apt","Bangalore",560155L);
	int[] stockList5= {1,2,4,5};
	Customer c5=new Customer(5, "Pradeep", a5, stockList5);
	
	ArrayList<Customer> allCustomers=new ArrayList(Arrays.asList(c1,c2,c3,c4,c5));
	
	@RequestMapping(value="/customers", method=RequestMethod.GET)
	public ArrayList<Customer> getCustomerList() {
		return allCustomers;
	}
	
	@RequestMapping(value="/customers/{custId}", method=RequestMethod.GET)
	public Customer getACustomer(@PathVariable("custId") int cid) {
		for (Customer c:allCustomers) {
			if(c.getCustomerId()==cid)
				return c;
		}
		return null;
	}
	
	@RequestMapping(value="/customers/{custId}/stocks", method=RequestMethod.GET)
	public ArrayList<Stock> getStockDetails(@PathVariable("custId") int cid) {
		ArrayList<Stock> allStocks=new ArrayList<>();
		int stockIds[]=null;
		
		// check if the id exists, if not, return null staright away
		// fetch a list of stock ids for this customer id: returns int array
		// use a for loop to iterte this array
		// 		Take a stock id one by one and call the 2nd microservice's end point
		// 		If this stock id is present, the 2nd ms will return stock object
		//		Add this stock obect to an ArrayList
		//		return ArrayList contains stock objects
		

		for (Customer c:allCustomers) {
			if (c.getCustomerId()==cid) {
				stockIds=c.getStockIds();
				break;
				
			}
		}
		
		Stock stockTemp=null;
		if (stockIds!=null) {
			for (int i=0;i<stockIds.length;i++) {
				
				// Call Stock microservices end point: /stocks/{stockId}
				stockTemp=getAStockObject(stockIds[i]);
				allStocks.add(stockTemp);
				
			}
		}
		

		return allStocks;
		
	}
	
	public Stock getAStockObject(int stockId) {
		// we make a remote method call from here using restTemplate
		String serviceURL="http://STOCKMS/stocks/{stockId}";
		return rt.getForObject(serviceURL, Stock.class, stockId);
	}
	
}
