/*
 * File AbstractVisitor.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 9 Feb 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
package cern.devtools.depanalysis.example.service;

public class AbstractVisitor implements NumberVisitor {

	@Override
	public void visit(RealNumber r) {
		System.out.println(r);

	}

	@Override
	public void visit(ComplexNumber c) {
		System.out.println(c);

	}

}
