package com.java.techhub.corona.tracker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeathStats {

	private String state;
	private String country;
	private int latestTotalCases;
	private int diffFromPrevDay;

}