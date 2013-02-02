/*
 * File VisitableNumber.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 26 Jan 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
package cern.devtools.depanalysis.example.service;

public interface VisitableNumber {
	public void accept(NumberVisitor visitor);
}
