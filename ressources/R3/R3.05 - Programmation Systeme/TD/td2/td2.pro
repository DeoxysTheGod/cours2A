TEMPLATE = app
CONFIG += console c++17
CONFIG -= app_bundle
CONFIG -= qt

DISTFILES += \
    Include/INCLUDE_H

HEADERS += \
    Include/CExc.h \
    Include/nsSysteme.h

SOURCES += \
    WorkDir/exo_01.cxx \
    WorkDir/nsSysteme.cxx
