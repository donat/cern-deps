/*
 * File ComplexNumber.java.
 * Created by Donat Csikos<dcsikos@cern.ch> at 26 Jan 2012.
 *
 * Copyright CERN 2012, All Rights Reserved.
 */
package cern.devtools.depanalysis.example.service;

public class ComplexNumber implements VisitableNumber {
	float im;
	float re;
	
	public ComplexNumber(float im, float re) {
		this.im = im;
		this.re = re;
	}

	public float getIm() {
		return im;
	}

	public void setIm(float im) {
		this.im = im;
	}

	public float getRe() {
		return re;
	}

	public void setRe(float re) {
		this.re = re;
	}

	@Override
	public void accept(NumberVisitor visitor) {
		visitor.visit(this);
		
	}
}
