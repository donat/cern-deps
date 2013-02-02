/*
 * File SumVisitor.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 26 Jan 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
package cern.devtools.depanalysis.example.dependant;

import cern.devtools.depanalysis.example.service.AbstractVisitor;
import cern.devtools.depanalysis.example.service.ComplexNumber;
import cern.devtools.depanalysis.example.service.NumberVisitor;
import cern.devtools.depanalysis.example.service.RealNumber;

public class SumRealNumbersVisitor extends AbstractVisitor implements NumberVisitor {

	float sum = 0;
	
	@Override
	public void visit(RealNumber r) {
		sum += r.getValue();
		
	}

	@Override
	public void visit(ComplexNumber c) {
		super.visit(c);
		System.out.println("excluded");
	}

	public float getResult() {
		float result = sum;
		sum = 0;
		return result;
	}
}
