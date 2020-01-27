package tes2Asbtract;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controleur {

	public Controleur(String nomFichier) {
		BuildTree p=new BuildTree(nomFichier);
		ctlArborescence(p);
	}	
	public Controleur() {
		BuildTree p=new BuildTree();
		ctlArborescence(p);

	}


	public void ctlArborescence(BuildTree p) {
		Console vue=new Console();
		Scanner in=new Scanner(System.in);
		do {
			System.out.print(">");
			String str=in.nextLine();
			if (str.equals("exit")|| str.equals("quit")) {
				break;
			}
			String []tabstr=str.split(" ");
			switch (tabstr[0]) {
			case "mkdir":
				if (tabstr.length>1)
				{
					for (int i = 1; i < tabstr.length; i++) {
						ArbreFichiers fils=new Dossier(tabstr[i]);
						((Dossier) p.arbre).ajouter(fils);
					}

				}

				else if (tabstr.length==1) {


					vue.demandeHelp("mkdir");
				}


				else vue.tropArgument("mkdir");
				break;
			case "ls":
				if (tabstr.length==1) 

					vue.afficher(((Dossier) p.arbre).lister());
				else 
					vue.afficher("Syntaxe Incorrect");
				break;
			case "less":
				if (tabstr.length==2) {
					if(p.arbre.getFils(tabstr[1])!=null &&
							p.arbre.getFils(tabstr[1]).isFichier())
						System.out.println(((Dossier) p.arbre).less(tabstr[1]));
					else vue.afficher("Aucun fichier de ce nom");;	
				}
				if (tabstr.length!=2) {
					vue.afficher("less : operande manquant");
					vue.demandeHelp("less");		
				}
				break;
			case "cd":
				if (tabstr.length==2)
					p.arbre=Dossier.changeMode(p.arbre, tabstr[1]); 
				else {
					vue.afficher("cd : operande manquant");
				}
				break;
			case "cd ..":
				if (tabstr.length==2)
					p.arbre=Dossier.changeMode(p.arbre,"../");
				else  vue.afficher("syntaxe incorrecte");
				break;
			case "rm":
				if (tabstr.length>=2 && tabstr.length<=3)
					for (int i = 1; i < tabstr.length; i++) {
						((Dossier) p.arbre).remove(tabstr[i]);
					}

				else  
					vue.afficher("rm : syntaxe incorrecte");
				break;
			case "pwd":
				if (tabstr.length==1) 
					System.out.println(((Dossier) p.arbre).dossierCourant());
				else
					vue.afficher("Syntaxe Incorrect");
				break;
			case "mkfile":
				if (tabstr.length>1) {
					for (int i = 1; i < tabstr.length; i++) {
						((Dossier) p.arbre).mkfile(tabstr[i]);
					}

				}
				else
				{
					vue.operandeMan("mkfile");
					vue.demandeHelp("mkfile");
				}
				break;
			case "grep":
				if (tabstr.length==3) {
				String strg=((Dossier) p.arbre).grep(tabstr[1],tabstr[2] );
				vue.afficher(strg);
				}
				else vue.demandeHelp("grep");;
				break;
			case "find":
				if (tabstr.length==3) {
					String strf=((Dossier) p.arbre).find(p.arbre,tabstr[2]);;
					vue.afficher(strf);
				}
				else {
					vue.demandeHelp("find");
				}
				break;
			case "mkdir-help":
				vue.helpMkdir();
				break;
			case "less-help":
				vue.helpLess();
				break;
			case "mkfile-help":
				vue.helpMkfile();
				break;
			case "find-help":
				vue.helpFind();;
				break;
			case "grep-help":
				vue.helpGrep();;
				break;
			default:
				if (qs(tabstr[0])) {
					String cmd=returnNomCmd(tabstr[0]);
					vue.qshelp(cmd, tabstr[0]);
				}
				else vue.cmdIntrouvable();
				break;
			}

		} while (true);

	}



	public static boolean qs(String str1){
		List<String> lesCommandes=new ArrayList<>();
		lesCommandes.add("ls");
		lesCommandes.add("pwd");
		lesCommandes.add("mkdir");
		lesCommandes.add("less");
		lesCommandes.add("mkfile");
		lesCommandes.add("cd");
		lesCommandes.add("grep");
		lesCommandes.add("find");
		int cpt=0;
		//	if (str2.length()==str1.length() || str2.length()-1==str1.length()) {
		//
		//		for (int i=0;i<str1.length();i++ ) {
		//			if(str1.charAt(i)!=str.charAt(i))cpt++;
		//		}
		//	}
		for (String str :lesCommandes) {
			cpt=0;
			if (str.length()==str1.length() || str1.length()-1==str.length()) {
				for (int i=0;i<str.length();i++ ) {
					if(str.charAt(i)!=str1.charAt(i))cpt++;
				}
				if (cpt==1)
					return true;		
				}
		}

		return false;
	}

	public String returnNomCmd(String str1) {
		String res="";
		List<String> lesCommandes=new ArrayList<>();
		lesCommandes.add("ls");
		lesCommandes.add("pwd");
		lesCommandes.add("mkdir");
		lesCommandes.add("less");
		lesCommandes.add("mkfile");
		lesCommandes.add("cd");
		lesCommandes.add("grep");
		lesCommandes.add("find");
		int cpt=0;

		for (String str :lesCommandes) {
			cpt=0;
			if (str.length()==str1.length() || str.length()==str1.length()-1) {
				for (int i=0;i<str.length();i++ ) {
					if(str.charAt(i)!=str1.charAt(i))cpt++;
				}
				
			}
			if(cpt==1)return str;
		}
		return "";
	}




}
