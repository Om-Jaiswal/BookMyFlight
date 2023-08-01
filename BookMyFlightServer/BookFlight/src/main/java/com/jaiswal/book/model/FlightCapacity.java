package com.jaiswal.book.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.jaiswal.book.exception.InvalidFlightClassException;

import jakarta.validation.constraints.NotBlank;
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
	@NotBlank
	private boolean[][] standard;
	@NotBlank
	private boolean[][] economy;
	@NotBlank
	private boolean[][] business;
	
	public boolean[][] getClassCapacity(String className) throws InvalidFlightClassException {
		if("Standard".equalsIgnoreCase(className)) {
			return getStandard();
		} else if("Economy".equalsIgnoreCase(className)) {
			return getEconomy();
		} else if("Business".equalsIgnoreCase(className)) {
			return getBusiness();
		} else {
			throw new InvalidFlightClassException("Class Name Doesn't Exist!");
		}
	}
	
	public void setClassCapacity(String className, boolean[][] updatedCapacity) throws InvalidFlightClassException {
		if("Standard".equalsIgnoreCase(className)) {
			setStandard(updatedCapacity);
		} else if("Economy".equalsIgnoreCase(className)) {
			setEconomy(updatedCapacity);
		} else if("Business".equalsIgnoreCase(className)) {
			setBusiness(updatedCapacity);
		} else {
			throw new InvalidFlightClassException("Class Name Doesn't Exist!");
		}
	}
	
}
