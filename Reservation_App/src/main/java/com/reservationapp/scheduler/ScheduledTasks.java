package com.reservationapp.scheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.Bid;
import com.reservationapp.model.Message;
import com.reservationapp.model.Requisite;
import com.reservationapp.service.impl.BidServiceImpl;
import com.reservationapp.service.impl.MessageServiceImpl;
import com.reservationapp.service.impl.RequisiteServiceImpl;

@Transactional(readOnly = true)
@Component
public class ScheduledTasks {
	
	@Autowired
	private RequisiteServiceImpl reqService;
	
	@Autowired
	private BidServiceImpl bidService;
	
	@Autowired
	private MessageServiceImpl messageService;
//  prvi je na svaki minut drugi je svaki dan u 00:01
//	@Scheduled(cron = "* */1 * * * *")
	@Scheduled(cron = "0 1 0 * * ?")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void dateCheck() {
		Date date = new Date();
		List<Requisite> list= reqService.findByEndDateLessThanAndIsActiveTrue(date);
		if(list!=null) {
			for(Requisite re : list) {
				
				Bid newBid =bidService.findHeighestBid(re.getId());
				if(newBid != null) {
					Message message = new Message();
					message.setReciverID(newBid.getBiddersID());
					message.setSenderID(re.getCreator());
					message.setRead(false);
					message.setText("Your bid for "+re.getName()+" is selected as best, please proceed to make arrangements with seller. ");
					messageService.save(message);
					
					Message message1 = new Message();
					message1.setReciverID(re.getCreator());
					message1.setSenderID(newBid.getBiddersID());
					message1.setRead(false);
					message1.setText("Please proceed to make arrangements with buyer.(item: "+ re.getName() +")");
					messageService.save(message1);
					
					List<Bid> losers = bidService.findByItemsid(re.getId());
					if(losers!=null) {
						List<Long> l2 = new ArrayList<Long>();
						for(Bid bi : losers) {
							if(!bi.getBiddersID().equals(re.getCreator()) && !bi.getBiddersID().equals(newBid.getBiddersID()) ) {
								if(!l2.contains(bi.getBiddersID())) {
									Message message3 = new Message();
									message3.setReciverID(bi.getBiddersID());
									message3.setSenderID(re.getCreator());
									message3.setRead(false);
									message3.setText("Your bid for item "+re.getName()+" was not accepted not accepted. ");
									messageService.save(message3);
									l2.add(bi.getBiddersID());
								}
							}
						}
					}
				}else {
					Message message1 = new Message();
					message1.setReciverID(re.getCreator());
					message1.setSenderID(re.getCreator());
					message1.setRead(false);
					message1.setText("Your add for "+ re.getName() +" is expired, no one placed a bid. Burrrn LOL");
					messageService.save(message1);
				}
				re.setActive(false);
				reqService.update(re);
			}
		}
	}
}
