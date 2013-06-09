#!/bin/bash
mvn install:install-file -Dfile=ojdbc6.jar  -DgroupId=oracle -DartifactId=ojdbc6 -Dpackaging=jar -Dversion=11.2.0.3
mvn install:install-file -Dfile=org.eclipse.emf.common_2.7.0.jar -DgroupId=org.eclipse.emf -DartifactId=emf-common -Dpackaging=jar -Dversion=2.7.0
mvn install:install-file -Dfile=org.eclipse.emf.ecore_2.7.0.jar -DgroupId=org.eclipse.emf -DartifactId=emf-ecore -Dpackaging=jar -Dversion=2.7.0
mvn install:install-file -Dfile=org.eclipse.emf.ecore.change_2.7.1.jar -DgroupId=org.eclipse.emf -DartifactId=emf-ecore-change -Dpackaging=jar -Dversion=2.7.1
mvn install:install-file -Dfile=org.eclipse.emf.ecore.xmi_2.7.0.jar -DgroupId=org.eclipse.emf -DartifactId=emf-ecore-xmi -Dpackaging=jar -Dversion=2.7.0
