JFLAGS = -g 
JC = javac 

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES =tes2Asbtract/Controleur.java\
	tes2Asbtract/BuildTree.java\
	tes2Asbtract/Dossier.java\
	tes2Asbtract/Fichier.java\
	tes2Asbtract/FormatFichier.java\
	tes2Asbtract/ArbreFichiers.java\
	tes2Asbtract/Console.java\
	Main.java

rdefault: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class tes2Abstract/*.class

