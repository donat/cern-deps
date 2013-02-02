/*
 * File NumberCrunchingService.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 26 Jan 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
package cern.devtools.depanalysis.example.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class NumberCrunchingService {
	public final static NumberCrunchingService INSTANCE = new NumberCrunchingService();

	private List<NumberVisitor> visitors = new LinkedList<NumberVisitor>();

	private NumberCrunchingService() {
	}

	public void registerVisitor(NumberVisitor visitor) {
		visitors.add(visitor);
	}

	public void startService() {
		// init
		Random rand = new Random();
		VisitableNumber[] nums = new VisitableNumber[10];
		for (int i = 0; i < 10; ++i) {
			if (rand.nextBoolean()) {
				nums[i] = new RealNumber(rand.nextInt(10));
			} else {
				nums[i] = new ComplexNumber(rand.nextInt(10), rand.nextInt(10));
			}
		}
		
		if (rand.nextBoolean()) {
			for(VisitableNumber num : nums) {
				for (NumberVisitor v : visitors) {
					num.accept(v);
				}
			}
		}

	}
}
