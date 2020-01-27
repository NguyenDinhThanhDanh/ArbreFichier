package tes2Asbtract;

public class Console {

	
	public void tropArgument(String cmd) {
		System.out.println(cmd +" : trop d'arguments");
	}
	
	public void imposiSuprimer(String nom) {
		System.out.println("rm : impossible de supprimer "+nom 
				+ " Aucun fichier ou dossier de ce type");
	}
	public void cmdIntrouvable() {
		System.out.println("Commande introuvable");
	}
	//NOTICE
	public void operandeManq(String cmd) {
		System.out.println(cmd+" opérande manquant");
	
	}
	
	public void demandeHelp(String cmd) {
		System.out.println("Saisissez "+cmd+"-help pour plus d'information.");
	}
	public void helpMkdir() {
		String str="Utilisation : mkdir "+ "\n"+"Creer le ou les repertoire "
				+ "s'ils n'exixtent pas "
				+ "\n"+" Exemple: mkdir a : creer le dossier a.";
	
			System.out.println(str);
}
	public void helpMkfile() {
		String str="Utilisation : mkfile "+ "\n"+"Creer le ou les fichier "
				+ "s'ils n'exixtent pas "
				+ "\n"+" Exemple: mkfile a : creer le fichier a."
						+ "\n"+"Exemple 2:mkfile a b: creer les fichiers a et b ";
	
			System.out.println(str);
}
	
	public void helpLess() {
		String str="Utilisation : less \n "
				+ "affiche le contenu d'un fichier "
				+ "\n Exemple : less f affiche le contenu du fichier f";
		
		System.out.println(str);
	}
	
	public void helpPwd() {
		System.out.println("pwd: Affiche le nom du repertoire"
				+ "courant.");
	}
	
	public void qshelp(String str1,String str2) {
		String res="La commande " +str2 +" est introuvable "+"\n"+
	" Voulez vous dire "+str1;
		
		System.out.println(res);
	}
	
	public void operandeMan(String cmd) {
		System.out.println(cmd+" : operande manquant");
	}
	public void afficher(String msg) {
		System.out.println(msg);
	}
	public void helpFind() {
		String str="Utilisation : find \n"
				+ "find -name f: qui donne tous les chemins"+"\n"+
				"menant a un fichier appele f"
				+ "\n Exemple : find -name f affiche tous les chemins"
				+ "\n"+" menant a un fichier appeler  f";
		
		System.out.println(str);
	}
	public void helpGrep() {
		String str="Utilisation : grep \n"
				+ "grep m f: qui retourne toutes les lignes "+"\n"+
				"du fichier f qui contiennent le mot m"
				;
		
		System.out.println(str);
	}
}
