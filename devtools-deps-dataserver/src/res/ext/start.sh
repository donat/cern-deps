#!/bin/sh
if [ ! -d "jarrepo" ];
then
   mkdir "jarrepo"
fi
exec -a deps-server java -cp "`echo lib/*.jar | tr ' ' ':'`" cern.devtools.deps.ServerEmf &> log.txt &