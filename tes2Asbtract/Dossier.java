package tes2Asbtract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Dossier extends ArbreFichiers {
	private ArbreFichiers pere;
	private ArbreFichiers filsPlusAGauche;
	private ArbreFichiers frereright;
	private ArbreFichiers frereleft;
	private String nom;
	private final boolean fichier = false;
	private String contenu;
	private int taille;

	public Dossier(ArbreFichiers pere, ArbreFichiers filsPlusAGauche, ArbreFichiers frereright, ArbreFichiers frereleft,
			String nom, boolean fichier, String contenu, int taille) {
		super(pere, filsPlusAGauche, frereright, frereleft, nom, fichier, contenu, taille);

	}

	public Dossier(String nom) {
		this.nom = nom;
		setFichier(true);
	}

	private void setFichier(boolean b) {
		// TODO Auto-generated method stub

	}
/**
 * cette methode ajouter permet d'ajouter un arbre fichier 
 * (dossier ou fichier) comme fils du dossier qui l'appelle
 * sachant q'un fichier ne peut pas avoir de fils donc 
 * cette methode est toujours utiliser par un arbre fichier
 * de type fichier
 * 
 * @param pereN2
 */
	public void ajouter(ArbreFichiers pereN2) {
		boolean b = false;
		boolean c = false;
		pereN2.setPere(this);// le pere de N2 est this
		if (getFilsPlusAGauche() == null) {
			setFilsPlusAGauche(pereN2);
			;
			miseAjourTaille(getFilsPlusAGauche());
		} else {
			ArbreFichiers filscourant = getFilsPlusAGauche();
			while (filscourant != null) {
				if (filscourant.getNom().compareToIgnoreCase(pereN2.getNom()) >= 0
						&& filscourant.getFrereleft() == null) {
					ArbreFichiers tmp = filscourant;
					pereN2.setFrereright(filscourant);
					filscourant.setFrereleft(pereN2);
					filscourant = pereN2;
					setFilsPlusAGauche(filscourant);
					miseAjourTaille(filscourant);
					c = true;
					b = true;
					break;

				}

				filscourant = filscourant.getFrereright();

			}

			if (!c) {
				ArbreFichiers filsc = getFilsPlusAGauche();
				;
				while (filsc.getFrereright() != null) {
					if (filsc.getFrereright().getNom().compareToIgnoreCase(pereN2.getNom()) >= 0) {
						ArbreFichiers tmp = filsc;
						pereN2.setFrereleft(tmp);
						pereN2.setFrereright(filsc.getFrereright());
						filsc.setFrereright(pereN2);

						b = true;
						c = true;
						break;
					}
					filsc = filsc.getFrereright();

				}
			}

			if (!b) {

				ArbreFichiers filscouran = getFilsPlusAGauche();
				;

				while (filscouran != null) {

					if (filscouran.getFrereright() == null && filscouran != null) {

						pereN2.setFrereleft(filscouran);
						filscouran.setFrereright(pereN2);
						break;

					}
					filscouran = filscouran.getFrereright();
				}

			}

		}
	}
/**
 * 
 * @return le chemin de la racine j'usqu'au dossier courant
 */
	public String dossierCourant() {
		String res = "";
		ArbreFichiers tmp = this;
		List<String> liste = new ArrayList<String>();
		while (tmp != null) {
			liste.add(tmp.getNom());
			tmp = tmp.getPere();
		}
		Collections.reverse(liste);
		liste.remove(0);
		res = "/";
		for (String string : liste) {
			res += string + "/";
		}
		return (res.length() == 1 ? res : res.substring(0, res.length() - 1));
	}
/**
 * 
 * @param nom
 * @return cette methode  creer un dossier
 * avec comme nom le nom passer en parametre
 */
	public boolean mkdir(String nom) {
		ArbreFichiers dossier = new Dossier(nom);
		this.ajouter(dossier);
		return true;
	}
/**
 * 
 * @param nom
 * @return cette methode  creer un fichier
 * avec comme nom le nom passer en parametre
 */
	public boolean mkfile(String nom) {
		System.out.print("Contenu du fichier ? ");
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();

		ArbreFichiers fichier = new Fichier(nom, str);

		this.ajouter(fichier);
		return true;
	}
/**
 * 
 * cette methode permet de lister tous les fils du
 * dossier a laquelle elle est appeler
 */
	public String lister() {
		String res = "";
		if (getFilsPlusAGauche() == null) {
			return res + " ";
		} else {
			ArbreFichiers filscourant = getFilsPlusAGauche();

			while (filscourant != null) {
				if (!filscourant.isFichier()) {
					res += "d";
					res += " " + filscourant.getNom() + " " + filscourant.getTaille() + "\n";
				}

				else {
					res += " " + filscourant.getNom() + " " + filscourant.getTaille() + "\n";
				}
				filscourant = filscourant.getFrereright();
			}
			return res;
		}
	}

	/**
	 * retourne le fils d'un dossier dont le nom est 
	 * passee en parametre
	 */
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

	/**
	 * 
	 * @param arbre
	 * @param chemin
	 * @return ce methode est equivalent de cd s
	 */
	public static ArbreFichiers changeMode(ArbreFichiers arbre, String chemin) {
		String[] str = chemin.split("/");
		ArbreFichiers sauv = arbre;
		ArbreFichiers tmp = arbre;

		for (String string : str) {
			if (((Dossier) tmp).getFils(string) != null) {
				ArbreFichiers fils = ((Dossier) tmp).getFils(chemin);
				if (fils != null && !fils.isFichier()) {
					tmp = fils;
				} else {
					tmp = sauv;
					System.out.println("cd: " + chemin + " aucun dossier de ce type ");
					break;
				}
			} else if (string.equals("..")) {
				if (tmp.getPere() != null) {
					tmp = tmp.getPere();
				} else {
					tmp = sauv;
					System.out.println("cd: " + chemin + " aucun dossier de ce type ");
					break;
				}
			}
		}
		return tmp;
	}

	/**
	 * 
	 * @param nom
	 * Elle supprime l'arbre fichier (Dossier ou fichier)
	 * dont le nom est passer en parametre
	 
	 */
	public void remove(String nom) {
		ArbreFichiers fils = getFils(nom);
		ArbreFichiers tmp = getFilsPlusAGauche();
		while (tmp != null) {
			if (tmp.getNom().equals(nom) && nbFils() == 1) {

				int fat = this.getTaille();
				fat = this.getTaille();
				this.setTaille(fat - getFilsPlusAGauche().getTaille());
				setFilsPlusAGauche(null);
			} else if (tmp.getNom().equals(nom) && nbFils() > 1 && getFilsPlusAGauche().getNom().equals(nom)) {
				int fat = this.getTaille();
				this.setTaille(fat - tmp.getTaille());
				// ArbreFichiers tmp=frereleft;
				setFilsPlusAGauche(getFilsPlusAGauche().getFrereright());
				// frereleft=frereright;

			} else if (tmp.getNom().equals(nom) && tmp.getFrereleft() != null && tmp.getFrereright() != null) {
				int fat = this.getTaille();
				this.setTaille(fat - tmp.getTaille());
				tmp.getFrereleft().setFrereright(tmp.getFrereright());
				tmp.getFrereright().setFrereleft(tmp.getFrereleft());

			} else if (tmp.getNom().equals(nom) && tmp.getFrereright() == null) {
				int fat = this.getTaille();
				this.setTaille(fat - tmp.getTaille());
				// frereright=null;
				miseAjourTaille(this);
				tmp.getFrereleft().setFrereright(null);
				break;

			}
			tmp = tmp.getFrereright();
			{

			}

		}
	}
/**
 * return le nombre de fils d'un dossier
 */
	public int nbFils() {
		int cpt = 0;
		ArbreFichiers tmp = getFilsPlusAGauche();
		while (tmp != null) {
			cpt++;
			tmp = tmp.getFrereright();
		}

		return cpt;
	}

	public void miseAjourTaille(ArbreFichiers arbre) {
		ArbreFichiers tmp = arbre.getPere();
		while (tmp != null) {
			tmp.setTaille(tmp.getTaille() + arbre.getTaille());
			arbre = tmp;
			tmp = tmp.getPere();
		}
	}
/**
 * retoune le pere d'un dossier ou fichier
 */
	public ArbreFichiers getPere() {
		return pere;
	}

	public void setPere(ArbreFichiers pere) {
		this.pere = pere;
	}

	/**
	 * retoune le premier fils ou (fils le plus a gauche) d'un dossier 
	 */
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

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}
	/**
	 * 
	 * @param motif
	 * @param nomF
	 * @return les lignes correspondant a un motif
	 * d'un fichier
	 */
	public String grep(String motif,String nomF) {
		ArbreFichiers tmp=getFils(nomF);
		String contenu=tmp.getContenu();
		String []tas=contenu.split("___");
		
		String res="";
		for (int i = 0; i < tas.length; i++) {
			String []tas2=tas[i].split(" ");
			for (int j = 0; j < tas2.length; j++) {
				if (tas2[j].equalsIgnoreCase(motif)) {
					res+=tas[i]+"\n";
				}
			}
		}

		return res;

	}
	
	public boolean isContent(String nomf) {
		String res="";
		if( getFilsPlusAGauche()==null )return false;
		ArbreFichiers filsCourant=getFilsPlusAGauche();
		while(filsCourant!=null){
			if (filsCourant.getNom().equals(nomf) && filsCourant.isFichier()) {
				return true;
			}
			
			filsCourant=filsCourant.getFrereright();
		}
		
		return false;
		
	}
	/**
	 * 
	 * @param a
	 * @param nomf
	 * @return cette methode est l'equivalent de la commande
	 * find
	 */
	public String find(ArbreFichiers a,String nomf) {
		String res="";
		ArbreFichiers filsCourant=a.getFilsPlusAGauche();
		while(filsCourant!=null){
			if(!filsCourant.isFichier()) {
				res+= find(filsCourant,nomf);
				
			}
			if(filsCourant.isFichier() && filsCourant.getNom().equals(nomf)) {
				res+=((Dossier) filsCourant.getPere()).dossierCourant()+"/"+
			nomf+"\n";
			
			}
			
			filsCourant=filsCourant.getFrereright();
		}
		
		
		return res;
	}
}
