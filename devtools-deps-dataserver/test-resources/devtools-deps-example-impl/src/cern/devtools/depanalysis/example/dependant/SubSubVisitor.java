/*
 * File SubSubVisitor.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 27 Feb 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
package cern.devtools.depanalysis.example.dependant;

import cern.devtools.depanalysis.example.service.ComplexNumber;
import cern.devtools.depanalysis.example.service.RealNumber;

public class SubSubVisitor extends SumRealNumbersVisitor {
	@Override
	public void visit(ComplexNumber c) {
		System.out.println("subsubclass visit before sumvisit");
		super.visit(c);
	}
	
	@Override
	public void visit(RealNumber r) {
		System.out.println("subsubclass visit before sumvisit");
		super.visit(r);
	}
}
