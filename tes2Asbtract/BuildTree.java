package tes2Asbtract;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
/**
 *Cette classe permet creer l'arborescence soit a partir d'un fichier
 *texte soit une arborescence avec juste la racine
 *
 */
public class BuildTree {

	ArbreFichiers arbre;
	public  BuildTree(String nomFichier) {
		 arbre = new Dossier("root");
		 FormatFichier format=new FormatFichier("*","fin"," ");
		BufferedReader lecteur = null;
		try {
			lecteur = new BufferedReader(new FileReader(nomFichier));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		String ligne="";
		try {
			ligne=lecteur.readLine();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		arbre.setNom(ligne);
		int cpt=0;
		Deque<ArbreFichiers> pile=new ArrayDeque<>();
		Deque<ArbreFichiers> pilefichier=new ArrayDeque<>();
		try {
			while((ligne=lecteur.readLine())!=null) {
				ligne=ligne.trim();
				ligne=ligne.replaceAll("\\s+",format.getSeparateur());;
				if (ligne!=null) {
					String [] str=ligne.split(format.getSeparateur());
					if(ligne.length()>2) {
						if(str[0].equals(format.getDebutInstruction()) && !str[1].equals(format.getFinInstruction())) {
							if (nbEtoile(str[0], format.getDebutInstruction().charAt(0))==1) {
								
								
								if(str[2].equals("d")) {
									ArbreFichiers fils=new Dossier(str[1]);
									
									((Dossier) arbre).ajouter(fils);
									pile.push(fils);

								}
								else if (str[2].equals("f")) {
									ArbreFichiers fils=new Fichier(str[1]);
									((Dossier) arbre).ajouter(fils);
									pilefichier.push(fils);
									cpt=0;
								}


							}






						}
						if (nbEtoile(str[0],format.getDebutInstruction().charAt(0))>1 && !str[1].equals(format.getFinInstruction())) {
							if(!pile.isEmpty()) {
								ArbreFichiers tmp=pile.peek();



								if(str[2].equals("d")) {
									ArbreFichiers fils1=new Dossier(str[1]);
									pile.push(fils1);
									((Dossier) tmp).ajouter(fils1);
								}
								else if (str[2].equals("f")) {
									ArbreFichiers fils1=new Fichier(str[1]);
									pilefichier.push(fils1);
									cpt=0;
									((Dossier) tmp).ajouter(fils1);
								}
								

							}

						}
						if (str[0].charAt(0)==format.getDebutInstruction().charAt(0) && str[1].equals(format.getFinInstruction())) {
							if(!pile.isEmpty())pile.pop();
						}
						if (!pilefichier.isEmpty() && cpt==1) {
							((Fichier) pilefichier.pop()).setContenu(ligne);
						}

					}

				}
				cpt++;
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}try {
			lecteur.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

public BuildTree() {
	arbre=new Dossier("");
}


	public  int nbEtoile(String ligne,char c) {
		int cpt=0;
		for (int i = 0; i < ligne.length(); i++) {
			if (ligne.charAt(i)==c) {
				cpt++;
			}
		}
		return cpt;
	}
	

}
