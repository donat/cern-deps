/*
 * File Real.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 26 Jan 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
package cern.devtools.depanalysis.example.service;

public class RealNumber implements VisitableNumber {
	float value;
	
	public RealNumber(float value) {
		this.value = value;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	@Override
	public void accept(NumberVisitor visitor) {
		visitor.visit(this);
		
	}
}
