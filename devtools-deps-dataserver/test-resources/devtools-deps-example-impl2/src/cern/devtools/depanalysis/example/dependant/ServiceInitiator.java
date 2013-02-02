/*
 * File ServiceInitiator.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 26 Jan 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
package cern.devtools.depanalysis.example.dependant;

import cern.devtools.depanalysis.example.service.NumberCrunchingService;

public class ServiceInitiator {
	public static void main(String[] args) {
		NumberCrunchingService service = NumberCrunchingService.INSTANCE;
		RenamedNumbersVisitor visitor = new RenamedNumbersVisitor();
		service.registerVisitor(visitor);
		service.startService();
		System.out.println("Result is : " + visitor.getResult());
	}
}
