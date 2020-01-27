package tes2Asbtract;

public class Fichier extends ArbreFichiers{
	private ArbreFichiers pere;
	@SuppressWarnings("unused")
	private final ArbreFichiers filsPlusAGauche=null;
	private final ArbreFichiers frereright=null;
	private final ArbreFichiers frereleft=null;
	private  String nom;
	private  boolean fichier;
	private String contenu;
	private int taille;
	public  Fichier(String nom,String contenu) {
		this.nom=nom;
		setContenu(contenu);
		setFichier(true);
		}
	public  Fichier(String nom) {
		this.nom=nom;
		setFichier(true);
		}
	
	
	
	public void miseAjourTaille(ArbreFichiers arbre) {
		ArbreFichiers tmp=arbre.getPere();
		while(tmp!=null) {
			tmp.setTaille(tmp.getTaille()+arbre.getTaille());
			arbre=tmp;
			tmp=tmp.getPere();
		}
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getContenu() {
		return contenu;
	}
	
	public void setContenu(String contenu) {
		if (contenu != null) taille = contenu.length();
		miseAjourTaille(this);
		this.contenu = contenu;
	}
	public int getTaille() {
		return taille;
	}
	public void setTaille(int taille) {
		this.taille = taille;
	}
	public boolean isFichier() {
		return fichier;
	}
	public void setFichier(boolean fichier) {
		this.fichier = fichier;
	}
}
