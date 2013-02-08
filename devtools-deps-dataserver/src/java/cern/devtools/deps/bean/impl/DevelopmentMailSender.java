/***********************************************************************************************************************
 * © Copyright 2013 CERN. This software is distributed under the terms of the GNU General Public Licence version 3 (GPL
 * Version 3), copied verbatim in the file "COPYING". In applying this licence, CERN does not waive the privileges and
 * immunities granted to it by virtue of its status as an Intergovernmental Organization or submit itself to any
 * jurisdiction.
 * 
 * If you modify this Program, or any covered work, by linking or combining it with the Eclipse Integrated Development
 * Environment Plugin libraries (or a modified version of these libraries), containing parts covered by the terms of EPL
 * (Eclipse Public licence), the licensors of this Program grant you additional permission to convey the resulting work.
 * Corresponding Source for a non-source form of such a combination shall include the source code for the parts of
 * Eclipse Integrated Development Environment Plugin libraries used as well as that of the covered work.
 **********************************************************************************************************************/
package cern.devtools.deps.bean.impl;

//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//
//import cern.accsoft.commons.util.StringUtils;
//import cern.accsoft.io.mail.MailSender;
//import cern.accsoft.io.mail.MailSenderFactory;

/**
 * Development mail sender which sends all the e-mails to my own e-mail address.
 * 
 * @author Donat Csikos
 * 
 */
public class DevelopmentMailSender /*implements MailSender*/ {

//	private final MailSender mailSender;
//
//	private final String[] myOwnAddress;
//	
//	private static final Logger LOG = Logger.getLogger(DevelopmentMailSender.class);
//
//	public DevelopmentMailSender() {
//		MailSenderFactory msf = MailSenderFactory.newInstance();
//		msf.setFrom("donotreply@cern.ch");
//		this.mailSender = msf.newMailSender();
//		this.myOwnAddress = new String[]{ "dcsikos@cern.ch" };
//	}
//	
//	public DevelopmentMailSender(List<String> targetAddresses) {
//		MailSenderFactory msf = MailSenderFactory.newInstance();
//		msf.setFrom("donotreply@cern.ch");
//		this.mailSender = msf.newMailSender();
//		this.myOwnAddress = new ArrayList<String>(targetAddresses).toArray(new String[0]);
//	}
//
//	@Override
//	public void send(String[] to, String subject, String body) {
//		log(to, subject, body);
//		mailSender.send(myOwnAddress, subject, body);
//	}
//
//	@Override
//	public void send(String[] to, String[] cc, String[] bcc, String subject, String body) {
//		log(to, subject, body);
//		mailSender.send(myOwnAddress, cc, bcc, subject, body);
//	}
//
//	@Override
//	public void send(String[] to, String[] cc, String[] bcc, String subject, String body, File[] attachments) {
//		log(to, subject, body);
//		mailSender.send(myOwnAddress, cc, bcc, subject, body, attachments);
//	}
//
//	private void log(String[] to, String subject, String body) {
//		String toLog = "=== EMAIL ===\n";
//		toLog += "Email sent to: " + StringUtils.arrayToCommaDelimitedString(to) + "\n";
//		toLog += "With subject: " + subject + "\n";
//		toLog += "With content:\n" + body;
//		LOG.info(toLog);
//	}
}
