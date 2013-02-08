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

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;

public class EmfTransactionManager extends AbstractPlatformTransactionManager {

    private static final long serialVersionUID = 3709971395690825281L;

    @Override
	protected Object doGetTransaction() throws TransactionException {
		return null;
	}

	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) throws TransactionException {
		System.out.println("EMF transaction begin");
	}

	@Override
	protected void doCommit(DefaultTransactionStatus status) throws TransactionException {
		System.out.println("EMF transaction commit");
		
	}

	@Override
	protected void doRollback(DefaultTransactionStatus status) throws TransactionException {
		System.out.println("EMF transaction rollback");
	}

}
