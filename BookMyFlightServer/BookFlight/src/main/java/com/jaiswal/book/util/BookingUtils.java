package com.jaiswal.book.util;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.jaiswal.book.config.MQConfig;
import com.jaiswal.book.exception.InvalidFlightClassException;
import com.jaiswal.book.model.DatabaseSequence;
import com.jaiswal.book.model.FlightCapacity;
import com.jaiswal.book.model.dto.PaymentDTO;

@Component
public class BookingUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(BookingUtils.class);
	
	public long generateSequence(String sequenceName, MongoOperations mongoOperations) {
		DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(sequenceName)),
                new Update().inc("sequence",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSequence() : 1000L;
	}
	
	public FlightCapacity updateSeats(String flightClass, List<String> seats, FlightCapacity flightCapacity) throws InvalidFlightClassException {
		try {
			boolean[][] classCapacity = flightCapacity.getClassCapacity(flightClass);
			for(String seat: seats) {
				if('A' == seat.charAt(0)) {
					classCapacity[0][Integer.parseInt(seat.charAt(1)+"")-1] = true;
				} else if('B' == seat.charAt(0)) {
					classCapacity[1][Integer.parseInt(seat.charAt(1)+"")-1] = true;
				} else if('C' == seat.charAt(0)) {
					classCapacity[2][Integer.parseInt(seat.charAt(1)+"")-1] = true;
				} else if('D' == seat.charAt(0)) {
					classCapacity[3][Integer.parseInt(seat.charAt(1)+"")-1] = true;
				} else {
					logger.warn("Row Doesn't Exist!");
				}
			}
			flightCapacity.setClassCapacity(flightClass, classCapacity);
			return flightCapacity;
		} catch (InvalidFlightClassException exception) {
			logger.warn("Invalid Flight Class!");
	        throw exception;
	    }
	}
	
	public String publishMessage(RabbitTemplate template, PaymentDTO payment) {
		template.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, payment);
		return "Payment Published!";
	}

}
