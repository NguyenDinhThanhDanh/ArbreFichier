package tes2Asbtract;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;



public abstract class ArbreFichiers {
	private ArbreFichiers pere;
	private ArbreFichiers filsPlusAGauche;
	private ArbreFichiers frereright;
	private ArbreFichiers frereleft;
	private String nom;
	private boolean fichier;
	private String contenu;
	private int taille;

public ArbreFichiers(ArbreFichiers pere, ArbreFichiers filsPlusAGauche, ArbreFichiers frereright,
			ArbreFichiers frereleft, String nom, boolean fichier, String contenu, int taille) {
		
		this.pere = pere;
		this.filsPlusAGauche = filsPlusAGauche;
		this.frereright = frereright;
		this.frereleft = frereleft;
		this.nom = nom;
		this.fichier = fichier;
		this.contenu = contenu;
		this.taille = taille;
	}

public ArbreFichiers() {
		taille=0;
	}
public String less(String nom) {
	ArbreFichiers fils=getFils(nom);
	String res="";
	if(fils!=null && fils.isFichier()) {
		res=fils.getContenu().replaceAll("___", "\n");
	}
	return res;
}
public ArbreFichiers getFils(String nom) {
	if (nbFils() == 0)
		return null;
	ArbreFichiers tmp = getFilsPlusAGauche();
	while (tmp != null) {
		if (tmp.getNom().equals(nom))
			return tmp;

		tmp = tmp.getFrereright();
	}

	return null;

}

public int nbFils() {
	int cpt = 0;
	ArbreFichiers tmp = getFilsPlusAGauche();
	while (tmp != null) {
		cpt++;
		tmp = tmp.getFrereright();
	}

	return cpt;
}

	public ArbreFichiers getPere() {
		return pere;
	}

	public void setPere(ArbreFichiers pere) {
		this.pere = pere;
	}	

	public ArbreFichiers getFilsPlusAGauche() {
		return filsPlusAGauche;
	}

	public void setFilsPlusAGauche(ArbreFichiers filsPlusAGauche) {
		this.filsPlusAGauche = filsPlusAGauche;
	}

	public ArbreFichiers getFrereright() {
		return frereright;
	}

	public void setFrereright(ArbreFichiers frereright) {
		this.frereright = frereright;
	}

	public ArbreFichiers getFrereleft() {
		return frereleft;
	}

	public void setFrereleft(ArbreFichiers frereleft) {
		this.frereleft = frereleft;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public boolean isFichier() {
		return fichier;
	}

	

	public String getContenu() {
		return contenu;
	}

	

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille=taille;
	}

	

	public String returnConten(String cl, String str) {
		String res="";
		String []tas=str.split(" ");
		for (String string : tas) {
			if (cl.equals(string))res=str;
		}
		return res;
	}



}


