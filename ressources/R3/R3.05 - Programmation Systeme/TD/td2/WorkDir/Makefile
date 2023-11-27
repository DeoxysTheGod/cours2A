#/**
#*
#* @File : makefile
#*
#*
#* @Synopsis : Makefile pour les TD/TP Systemes
#*
#**/
include ../Include/INCLUDE_H
#
COMPILER = g++ -c -I$(INCLUDE) -Wall -O3 -Werror $*.cxx 

LIBBUILDER = mkdir -p ../lib; ar -cqs ../lib/libSys.a $*.o

#
# Edition de liens
#
$(nom).exec : codeobjsyst $(nom).o 
	g++ -s -o $(nom).exec $(nom).o -L../lib -lSys 
#
$(nom).o : $(nom).cxx $(CEXC_H) $(NSSYSTEME_H)
	$(COMPILER)
#
# Code Objet pour la bibliotheque Systemes
# 

codeobjsyst: nsSysteme.o
	touch codeobjsyst
#
nsSysteme.o : nsSysteme.cxx $(CEXC_H) $(NSSYSTEME_H)
	$(COMPILER); $(LIBBUILDER) 
#
# Nettoyage du repertoire courant : executables et fichiers .o
#
clean : 
	clear; rm -rf *.o *.run codeobjsyst ../lib -v *~ ../Include/*~
#




