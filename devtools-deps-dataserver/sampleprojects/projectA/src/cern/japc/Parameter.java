package cern.japc;

public interface Parameter {
	public ParameterValue getValue();
	public void setValue(ParameterValue value);
	public void prettyPrint();
}
