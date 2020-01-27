package tes2Asbtract;

public class FormatFichier {
private String debutInstruction;
private String finInstruction;
private String separateur;

public FormatFichier(String debutInstruction, String finInstruction, String separateur) {
	this.debutInstruction = debutInstruction;
	this.finInstruction = finInstruction;
	this.separateur = separateur;
}

public String getDebutInstruction() {
	return debutInstruction;
}

public String getFinInstruction() {
	return finInstruction;
}

public String getSeparateur() {
	return separateur;
}


}
