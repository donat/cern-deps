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
package cern.devtools.depanalysis.bean.impl;

//import static cern.devtools.depanalysis.bean.impl.CmmnbuildParamsStore.KEY_NEW_VERSION;
//import static cern.devtools.depanalysis.bean.impl.CmmnbuildParamsStore.KEY_OLD_VERSION;
//import static cern.devtools.depanalysis.bean.impl.CmmnbuildParamsStore.KEY_PRODUCT_NAME;
//import static cern.devtools.depanalysis.bean.impl.CmmnbuildParamsStore.KEY_RELEASER_NAME;
//import static cern.devtools.depanalysis.bean.impl.CmmnbuildParamsStore.KEY_RELEASE_TYPE;
//
//import java.rmi.RemoteException;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Hashtable;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import cern.accsoft.commons.util.userinfo.NiceUser;
//import cern.accsoft.commons.util.userinfo.UserInfoViewer;
//import cern.accsoft.io.mail.MailSender;
//import cern.devtools.depanalysis.bean.CmmnbuildNotificationCache;
//import cern.devtools.depanalysis.bean.CmmnbuildService;
//import cern.devtools.depanalysis.bean.DatabaseDao;
//import cern.devtools.depanalysis.bean.DatabaseException;
//import cern.devtools.depanalysis.bean.DependencyService;
//import cern.devtools.depanalysis.domain.Dependency;
//import cern.devtools.depanalysis.domain.Product;
//import cern.devtools.depanalysis.domain.creation.DomainFactory;
//import cern.devtools.depanalysis.domain.creation.DomainObjectCreator;

/**
 * Implementation of the {@link CmmnbuildService} interface. <p>
 * The following properties should be configured via Spring desriptor:
 * <table>
 * <tr>
 * <td>cacheNotification (boolean) The bean can be configured to cache all notification and retrain it till a specific
 * date of sending the emails. If set to true the notification is sent every day at the time specified by the properties
 * notifTimeHours and noitfTimeMinutes</td>
 * </tr>
 * <tr>
 * <td>notifTimeHours (int) The hour when to execute the mail sending</td>
 * </tr>
 * <tr>
 * <td>notifTimeMinutes (int) The minutes when to execute the mail sending</td>
 * </tr>
 * </table>
 * 
 * @author Donat Csikos
 */
public class CmmnbuildServiceImpl /*implements CmmnbuildService */{
//	/**
//	 * Logger.
//	 */
//	private static final Logger LOG = Logger.getLogger(CmmnbuildServiceImpl.class);
//
//	/**
//	 * The mail template's header to send.
//	 */
//	private static final String MAIL_SUBJECT = "[REPOSITORY_CHANGE] Summary of the repository changes";
//
//	/**
//	 * The email template body to send. It has two parameters: 1. First name of the committer 2. details of the
//	 * dependency information in a formatted form.
//	 */
//	private static final String MAIL_BODY_TEMPLATE = "<html>"
//			+ "<body><p>Dear %s,</p>"
//			+ "<p>You receive this e-mail because some changes occurred in some projects which are used by some of your products.Details below.</p>"
//			+ "<p>%s</p>"
//			+ "<p>Please check the provided changelogs to check if you are affected by these changes.</p>"
//			+ "<p>Note that everybody listed in the people file receives an e-mail. If you don't want to receive any"
//			+ " notifications for a given project, please remove your name from the people file. If you don't want to"
//			+ " receive release notifications at all, please register in the e-group accsoft-devtools-noreleasenotify.</p>"
//			+ "<p>Don't hesitate to send comments about this service to "
//			+ "<a href=\"mailto:accsoft-devtools-suppor@cern.ch\">accsoft-devtools-suppor@cern.ch</a>.</p>" +
//
//			"<p>Best regards,<br/>The accsoft-devtools team</p></body></html>";
//
//	/**
//	 * Value for scheduling a dially timertask.
//	 */
//	private static final long MILISECONDS_IN_A_DAY = 86400000;
//
//	/**
//	 * Whether the notification should be cached.
//	 */
//	private boolean cacheNotification = true;
//
//	/**
//	 * The scheduled time (hours part) to notify the jar dependencies
//	 */
//	private int notifTimeHours = 8;
//
//	/**
//	 * The scheduled time (minutes part) to notify the jar dependencies
//	 */
//	private int notifTimeMinutes = 0;
//
//	/**
//	 * The task instance which executes the notification for the incoming dependencies.
//	 */
//	private TimerTask notificationTask = new TimerTask() {
//		@Override
//		public void run() {
//			try {
//				sendMessages(cache.popAllEntries());
//			} catch (Throwable e) {
//				LOG.warn(e);
//			}
//		}
//	};
//
//	private boolean isScheduled = false;
//
//	@Autowired
//	private DependencyService service;
//
//	@Autowired
//	private volatile CmmnbuildNotificationCache cache;
//
//	@Autowired
//	private DatabaseDao db;
//
//	@Autowired
//	private DomainObjectCreator creator;
//
//	@Autowired
//	private MailSender mailSender;
//
//	@Override
//	@SuppressWarnings("unchecked")
//	public void notifyIncomingJarDependencies(String releaserName, String productName, String oldVersion,
//			String newVersion, String releaseType) throws RemoteException {
//		// Log the event.
//		LOG.info(String.format("New version released from a product. Details: relasername=%s, "
//				+ "productname=s, oldversion=%s, newversion=%s, type=%s.", releaserName, productName, oldVersion,
//				newVersion, releaseType));
//
//		// If we cache the entries then just store the new info.
//		if (cacheNotification) {
//			cache.addEntry(releaserName, productName, oldVersion, newVersion, releaseType);
//			startScheduleIfNotWorking();
//		}
//		// Otherwise send the message instantly.
//		else {
//			Hashtable<String, String> result = new Hashtable<String, String>();
//			result.put(KEY_RELEASER_NAME, releaserName);
//			result.put(KEY_PRODUCT_NAME, productName);
//			result.put(KEY_OLD_VERSION, oldVersion);
//			result.put(KEY_NEW_VERSION, newVersion);
//			result.put(KEY_RELEASE_TYPE, releaseType);
//
//			try {
//				sendMessages(Arrays.asList(result));
//			} catch (Exception e) {
//				LOG.warn(e);
//			}
//		}
//
//	}
//
//	private void startScheduleIfNotWorking() {
//		if (!isScheduled) {
//			isScheduled = true;
//			final Timer timer = new Timer();
//			timer.schedule(notificationTask, getFirstMessageSendDateTime(), MILISECONDS_IN_A_DAY);
//		}
//
//	}
//
//	private Date getFirstMessageSendDateTime() {
//		Calendar date = Calendar.getInstance();
//		date.setTime(new Date());
//		date.add(Calendar.DAY_OF_YEAR, 1);
//		date.set(Calendar.HOUR_OF_DAY, notifTimeHours);
//		date.set(Calendar.MINUTE, notifTimeMinutes);
//		date.set(Calendar.SECOND, 0);
//		date.set(Calendar.MILLISECOND, 0);
//		return date.getTime();
//	}
//
//	private synchronized void sendMessages(Collection<Hashtable<String, String>> releasedProducts) throws Exception {
//
//		// Maps released products to dependent products.
//		Map<Hashtable<String, String>, Set<Product>> releasedProductToDependentProduct = new HashMap<Hashtable<String, String>, Set<Product>>();
//		// Maps dependent projects to their committers.
//		Map<Product, Set<String>> dependentProductToCommitter = new HashMap<Product, Set<String>>();
//		// Collect all the committers.
//		Set<String> committerNames = new HashSet<String>();
//
//		for (Hashtable<String, String> releasedProduct : releasedProducts) {
//			// Get incoming products.
//			Collection<Dependency> incomingJarDependencies = getIncomingDeps(releasedProduct.get(KEY_PRODUCT_NAME),
//					releasedProduct.get(KEY_OLD_VERSION));
//			for (Dependency d : incomingJarDependencies) {
//				Product dependentProduct = (Product) d.getFrom();
//
//				// Map the relationship between the released and the dependent products.
//				Set<Product> depProductSet = releasedProductToDependentProduct.get(releasedProduct);
//				if (depProductSet == null) {
//					releasedProductToDependentProduct.put(releasedProduct, depProductSet = new HashSet<Product>());
//				}
//				depProductSet.add(dependentProduct);
//
//				// for each committer, add a new entry
//				Collection<String> dependantProductCommitters = service.getCommittersName(dependentProduct);
//				for (String incDepCommitter : dependantProductCommitters) {
//					Set<String> committersSet = dependentProductToCommitter.get(dependentProduct);
//					if (committersSet == null) {
//						dependentProductToCommitter.put(dependentProduct, committersSet = new HashSet<String>());
//					}
//					committersSet.add(incDepCommitter);
//
//					// and save the committer name.
//					committerNames.add(incDepCommitter);
//				}
//			}
//		}
//
//		// Compose and send emails.
//		for (String committer : committerNames) {
//			// Prepare user informations.
//			NiceUser committerInfo = retrieveUserInfo(committer);
//			String mailDetails = composeMailDetails(committer, releasedProductToDependentProduct,
//					dependentProductToCommitter);
//			String body = String.format(MAIL_BODY_TEMPLATE, committerInfo.getFirstName(), mailDetails);
//			performSending(committer, MAIL_SUBJECT, body);
//		}
//	}
//
//	private String composeMailDetails(String committer,
//			Map<Hashtable<String, String>, Set<Product>> releasedProductToDependentProduct,
//			Map<Product, Set<String>> dependentProductToCommitter) throws Exception {
//
//		// This object holds all strings to return.
//		StringBuilder details = new StringBuilder();
//		details.append("<table>");
//		details.append("<tr>");
//		details.append("<th>Released product</th>");
//		details.append("<th>Old version</th>");
//		details.append("<th>New version</th>");
//		details.append("<th>Releaser name</th>");
//		details.append("<th>Release type</th>");
//		details.append("<th>Changelog</th>");
//		details.append("<th>Your projects which depend on this release</th>");
//		details.append("</tr>");
//
//		// Iterate on all released projects.
//		for (Hashtable<String, String> releasedProduct : releasedProductToDependentProduct.keySet()) {
//
//			// Iterate on all dependent product and search for the ones which has the passed committer on the committers
//			// list.
//			List<String> dependentProductsList = new LinkedList<String>();
//			for (Product dependentProduct : releasedProductToDependentProduct.get(releasedProduct)) {
//
//				if (dependentProductToCommitter.get(dependentProduct).contains(committer)) {
//					dependentProductsList.add(dependentProduct.getName());
//				}
//			}
//
//			// If this release has at least one dependant project, where the passed commited contributes in, then add
//			// this to the entries.
//			if (!dependentProductsList.isEmpty()) {
//				details.append("<tr><td>");
//				details.append(releasedProduct.get(KEY_PRODUCT_NAME));
//				details.append("</td><td>");
//				details.append(releasedProduct.get(KEY_OLD_VERSION));
//				details.append("</td><td>");
//				details.append(releasedProduct.get(KEY_NEW_VERSION));
//				details.append("</td><td>");
//				details.append(releasedProduct.get(KEY_RELEASER_NAME));
//				details.append("</td><td>");
//				details.append(releasedProduct.get(KEY_RELEASE_TYPE));
//				details.append("</td><td>");
//				details.append(getChangeLogLink(releasedProduct.get(KEY_PRODUCT_NAME),
//						releasedProduct.get(KEY_NEW_VERSION)));
//				details.append("</td><td>");
//				details.append(createHtmlList(dependentProductsList));
//				details.append("</td><tr>");
//
//			}
//		}
//
//		details.append("</table>");
//		
//		return details.toString();
//	}
//
//	
//
//	private Collection<Dependency> getIncomingDeps(String productName, String version) throws Exception {
//		Product product = DomainFactory.creator().createProduct(productName, version, "", "");
//		return service.getIncomingDependencies(product);
//	}
//
//	private NiceUser retrieveUserInfo(String committer) throws Exception {
//		UserInfoViewer viewer = new UserInfoViewer();
//		NiceUser user = viewer.getUserInfoByLogin(committer);
//		return user;
//	}
//
//	private Object getChangeLogLink(String productName, String newVersion) {
//
//		try {
//			String result = "<a href=\"http://abwww.cern.ch/ap/dist/%s/%s/changelog.txt\">Changelog</a>";
//			String cf = db.findProduct(creator.createProduct(productName), false).getContainingFolders();
//			return String.format(result, cf, newVersion);
//		} catch (DatabaseException e) {
//			e.printStackTrace();
//			return "";
//		}
//	}
//	
//	private String createHtmlList(List<String> names) {
//		StringBuilder builder = new StringBuilder();
//		builder.append("<ul>");
//		for (String name : names) {
//			builder.append("<li>");
//			builder.append(name);
//			builder.append("</li>");
//		}
//		builder.append("</ul>");
//		
//		return builder.toString();
//	}
//
//	private void performSending(String toName, String subject, String body) {
//		mailSender.send(new String[] { toName + "@cern.ch" }, subject, body);
//	}
//
//	public boolean isCacheNotification() {
//		return cacheNotification;
//	}
//
//	public void setCacheNotification(boolean cacheNotification) {
//		this.cacheNotification = cacheNotification;
//	}
//
//	public int getNotifTimeHours() {
//		return notifTimeHours;
//	}
//
//	public void setNotifTimeHours(int notifTimeHours) {
//		this.notifTimeHours = notifTimeHours;
//	}
//
//	public int getNotifTimeMinutes() {
//		return notifTimeMinutes;
//	}
//
//	public void setNotifTimeMinutes(int notifTimeMinutes) {
//		this.notifTimeMinutes = notifTimeMinutes;
//	}
}
