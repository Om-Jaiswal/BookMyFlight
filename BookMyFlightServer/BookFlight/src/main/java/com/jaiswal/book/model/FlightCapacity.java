package com.jaiswal.book.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.jaiswal.book.exception.FlightClassException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection="flightCapacities")
public class FlightCapacity {
	
	@Id
	private String flightNumber;
	
	private boolean[][] standard;
	
	private boolean[][] economy;
	
	private boolean[][] business;
	
	public boolean[][] getClassCapacity(String className) throws FlightClassException {
		if("Standard".equals(className)) {
			return getStandard();
		} else if("Economy".equals(className)) {
			return getEconomy();
		} else if("Business".equals(className)) {
			return getBusiness();
		} else {
			throw new FlightClassException("Class Name Doesn't Exist!");
		}
	}
	
	public void setClassCapacity(String className, boolean[][] updatedCapacity) throws FlightClassException {
		if("Standard".equals(className)) {
			setStandard(updatedCapacity);
		} else if("Economy".equals(className)) {
			setEconomy(updatedCapacity);
		} else if("Business".equals(className)) {
			setBusiness(updatedCapacity);
		} else {
			throw new FlightClassException("Class Name Doesn't Exist!");
		}
	}
	
}
