package cern.accsoft.gui.alarms;

import cern.japc.Parameter;
import cern.japc.ParameterFactory;

public class CommandPanel {

	 public void setDeviceName(final String deviceName) {
		 Parameter parameter = ParameterFactory.newInstance().newParameter("paramname");
		 parameter.setValue(null);
	 }
	 
	 
	 void handleReset(String deviceName) {
		 Parameter parameter = ParameterFactory.newInstance().newParameter("paramname");
		 parameter.getValue();
	 }
}
