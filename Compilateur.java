import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.ScrollPaneConstants;



public class Compilateur extends JFrame {

	
	private static final long serialVersionUID = -2496651740627319814L;
	private JPanel panel = new JPanel();
			JButton btnlex = new JButton("Analyse Lexical");
			JButton btnsyntax = new JButton("Analyse Syntaxique");
			JButton btnsemant = new JButton("Analyse Semantique");
			JLabel lblNewLabel = new JLabel("Commandes :");
			JLabel lblNewLabel_1 = new JLabel("Sortie  :");
			JPanel panel_1 = new JPanel();
			JScrollPane scrollPane = new JScrollPane();
			JTextArea textArea = new JTextArea();
			JButton btnNewButton = new JButton("Charger un fichier");
			JFileChooser file = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers Compila", "Compila");
			static ArrayList<String> mots = new ArrayList<String>();
			static ArrayList<String> lignes = new ArrayList<String>();
			static ArrayList<String> result= new ArrayList<String>();
			static String[] mot;

	
	public Compilateur() {
		this.setVisible(true);
		this.setSize(1000,700);
		this.setTitle("MY COMPILER");
		this.getContentPane().setBackground(Color.WHITE);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel.setBackground(Color.WHITE);
		panel.setBounds(69, 38, 249, 561);
		this.getContentPane().add(panel);
		panel.setLayout(null);
		btnlex.setBounds(22, 159, 195, 59);
		btnlex.setForeground(Color.BLACK);
		btnlex.setBackground(Color.GRAY);
		btnlex.setFont(new Font("Dialog", Font.BOLD, 15));
		btnlex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				
				
				int i = 0;
				while (i < mots.size()) {
					
						if (TOKEN(mots.get(i)) != null) {
							result.add(TOKEN(mots.get(i)));
						} else if (id(mots.get(i)) != null) {
							result.add(id(mots.get(i)));
						} else if (nombreE_R(mots.get(i)) != null) {
							result.add(nombreE_R(mots.get(i)));
						}
						
						else result.add("Erreur");

						
					
					textArea.setText(textArea.getText()+ result.get(i)+"\n");
					i++;}
			}
		});
		panel.add(btnlex);
		btnsyntax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
				int i = 0;
				while (i < lignes.size()) {
					textArea.setText(textArea.getText()+lignes.get(i) + " : " +syntax(lignes.get(i))+"\n");
					i++;}
			}

		});

		btnsyntax.setBounds(22, 290, 195, 59);
		
		btnsyntax.setForeground(Color.BLACK);
		btnsyntax.setBackground(Color.GRAY);
		btnsyntax.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.add(btnsyntax);
		btnsemant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
				int i = 0;
				
				
				while (i < lignes.size()) {
					textArea.setText(textArea.getText()+lignes.get(i) + " : " +semantique(lignes.get(i))+"\n");
					
					i++;}
			}
		});

		btnsemant.setBounds(22, 422, 195, 59);
		btnsemant.setForeground(Color.BLACK);
		btnsemant.setBackground(Color.GRAY);
		btnsemant.setFont(new Font("Dialog", Font.BOLD, 13));
		panel.add(btnsemant);

		
		lblNewLabel.setBounds(22, 34, 195, 59);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 25));
		panel.add(lblNewLabel);

		
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel_1.setBounds(504, 10, 151, 48);
		this.getContentPane().add(lblNewLabel_1);

		
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(340, 70, 636, 588);
		this.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		scrollPane.setBounds(10, 11, 614, 526);
		panel_1.add(scrollPane);

		
		textArea.setBounds(34, 24, 578, 495);
		//panel_1.add(textArea);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		textArea.setForeground(Color.BLACK);
		textArea.setBackground(UIManager.getColor("Button.background"));
		textArea.setFont(new Font("Dialog", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				try {
					  textArea.setText("");
					  JFileChooser jfc=new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
					  jfc.addChoosableFileFilter(filter);
					  int returnfile =jfc.showOpenDialog(null);
					  if(returnfile==JFileChooser.APPROVE_OPTION){
					  File selectedfile = jfc.getSelectedFile();
					  Scanner s_l = new Scanner(selectedfile);
					  Scanner s_m = new Scanner(selectedfile);
					  while(s_l.hasNextLine()){
					  lignes.add(s_l.nextLine());
					  }
					  while(s_m.hasNext()){
					  mots.add(s_m.next());
					  }
					  s_m.close();
					  s_l.close();
					  }

					int i = 0;
					while (i < lignes.size()) {
						textArea.setText(textArea.getText()+lignes.get(i)+"\n");
						i++;}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		
		
		btnNewButton.setBounds(429, 549, 195, 27);
		panel_1.add(btnNewButton);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		
	}
	
	// declaration dun nombre 
	public boolean Num(String chaine, int i) {
		char[] num = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
		int j = 0;
		int l = num.length;
		while (j < l) {
			if (chaine.charAt(i) == num[j]) {
				return true;
			}
			j++;
		}

		return false;
	}
	//NOMBRE ENTIER OU REEL
	public String nombreE_R(String chaine) {
		int i = 0;
		int pos = 0;
		int l =chaine.length();
		while (i < l) {
			if (Num(chaine, i)) pos++;
			else if(chaine.charAt(pos)=='.') {
				pos++;
			
			}
			i++;
		}

		if (pos == l && !chaine.contains(".")) return "Nombre entier";
		else if (pos == l) return "Nombre reel";
		return null;

	}
	//declaration dune chaine de character 
	public boolean Char(String chaine, int i) {
		int k = 0;
		char[] ch_c = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',  'N', 'O', 'P', 'Q', 'R', 'S', 'T',
				'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
				'q', 'r', 's','t', 'u', 'v', 'w', 'x', 'y', 'z'};
		int l = ch_c.length;
		while (k < l) {
			if (chaine.charAt(i) == ch_c[k]) {
				return true;
			}
			k++;
		}
		return false;
	}
	
	//declaration dun ID
	public String id(String chaine) {
		boolean p = false;
		int pos = 0;
		int i = 0;
		int l = chaine.length();
		if (Char(chaine, 0)) {
			pos++;
			p = true;
		}
		if (p == true && l == 1)
			return chaine +" : identificateur";

		else if (l > 1) {
			i = 1;
			while (i < l) {

				if (Char(chaine, i))
					{pos++;
					
					}
				else if (Num(chaine, i))
					{pos++;
					
					}
				else if (chaine.charAt(i) == '_' ) {
					
					pos++;
				}
				i++;
			}
			if (pos == l)
				return chaine +" : Identificateur";
		}
		return null;
	}
	// syntaxique
	public String syntax(String chaine){
		
		if(chaine.equals("Start_Program")) return "Début du programme";
		else if(chaine.equals("Else")) return "Else";
		else if(chaine.equals("Start")) return "Début d'un bloc";
		else if(chaine.equals("Finish")) return "Fin d'un bloc";
		else if(chaine.startsWith("//.")) return "un commentaire";
		else if(chaine.equals("End_Program")) return "Fin du programme";
		else if(chaine.startsWith("ShowMes") && chaine.endsWith(";;")) return "Affichage d'un message";
		else if(chaine.contains(" ")) {
			mot = chaine.split(" ");
			int i=0;

			if(mot[i].equals("Int_Number")){
				i++;
			if(mot[i].equals(" "))
				i++;
			if(mot[i].equals(":"))
				i++;
			if(id(mot[i]) != null){
				i++;
			if(mot[i].equals(" "))
				i++;
				while(mot[i].equals(",")){
					i++;
					
					if(mot[i].equals(" "))
							i++;
					if(id(mot[i]) != null) 
							i++;	
					if(mot[i].equals(" "))
							i++;
				}
				if(mot[i].equals(";;")) return "Déclaration d entier";
						}
					

				}
				else if(mot[i].equals("Give")){
					i++;
					if(mot[i].equals(" "))
						i++;
					if(id(mot[i]) != null){
						i++;
					if(mot[i].equals(" "))
						i++;	
					if(mot[i].equals(":"))
						i++;
					if(mot[i].equals(" "))
						i++;
					if(nombreE_R(mot[i]) == "Nombre entier") {
						i++;
						if(mot[i].equals(" "))
							i++;
					if(mot[i].equals(";;")) return "affectation a un nombre entier";
						}
						else if(nombreE_R(mot[i]) == "Nombre reel"){
							i++;
							if(mot[i].equals(" "))
								i++;
							if(mot[i].equals(";;")) return "affectation a un nombre reel";
						}
					}

				}
				
				else if(mot[i].equals("Real_Number")){
					i++;
					if(mot[i].equals(" "))
						i++;
						
					if(mot[i].equals(":"))
							i++;
					if(mot[i].equals(" "))
							i++;
					if(id(mot[i]) != null)i++;
						
					if(mot[i].equals(";;")) return "Déclaration d un reel";
						}


				
				
				else if(mot[i].equals("If")){
					i++;
					if(mot[i].equals("--")){
						i++;
					if(id(mot[i]) != null){
						i++;
						if(mot[i].equals("<") || mot[i].equals(">") || mot[i].equals("==") || mot[i].equals("<=") || mot[i].equals(">=") || mot[i].equals("!=")){
						i++;
						if(id(mot[i]) != null){
							i++;
						if(mot[i].equals("--")) return "condition if"; 
							}}}}
				}
				
				
				
				else if(mot[i].equals("Affect")){
					i++;
					if(id(mot[i]) != null){
						i++;
					if(mot[i].equals("to")){
						i++;
					if(id(mot[i]) != null) {
						i++;
					if(mot[i].equals(";;")) return "affectation";
						}
					}
				}
				}
				
				
				else if(mot[i].equals("ShowVal")){
					i++;
					if(mot[i].equals(" "))
						i++;
						
						if(mot[i].equals(":"))
							i++;
						if(mot[i].equals(" "))
							i++;
						if(id(mot[i]) != null)i++;
							if(mot[i].equals(";;")) return "affichage d une valeur";
						}

				

				
								}
		return "erreur syntaxique";
	}
	//sementique
	public String semantique(String chaine){
		if(chaine.equals("Start_Program")) return "Debut de program";
		else if(chaine.equals("Else")) return "else";
		else if(chaine.equals("Start")) return "debut de bloc";
		else if(chaine.equals("Finish")) return "fin de bloc";
		else if(chaine.startsWith("//.")) return " un commentaire";
		else if(chaine.startsWith("ShowMes :") && chaine.endsWith(";;")) return "Affichage dun message";
		else if(chaine.equals("End_Program")) return "fin de program";
		else if(chaine.contains(" ")) {
		mot = chaine.split(" ");
		int i=0, k=1;

		if(mot[i].equals("Int_Number")){
			i++;
		if(mot[i].equals(" "))
			i++;
		if(mot[i].equals(":"))
			i++;
		if(id(mot[i]) != null){
			i++;
		if(mot[i].equals(" "))
			i++;
			while(mot[i].equals(",")){
				i++;
				k++;
				if(mot[i].equals(" "))
						i++;
				if(id(mot[i]) != null) 
						i++;	
				if(mot[i].equals(" "))
						i++;
			}
			if(mot[i].equals(";;")) return "Déclaration de "+k+" variables entiers";
					}
		}

		else if(mot[i].equals("Give")){
			i++;
			if(mot[i].equals(" "))
				i++;
			if(id(mot[i]) != null){
				i++;
			if(mot[i].equals(" "))
				i++;	
			if(mot[i].equals(":"))
				i++;
			if(mot[i].equals(" "))
				i++;
			if(nombreE_R(mot[i]) == "Nombre entier") {
				i++;
			if(mot[i].equals(";;")) return "affectation dune valeur entiere à "+mot[i-3];
				}
				else if(nombreE_R(mot[i]) == "Nombre reel"){
					i++;
					if(mot[i].equals(";;")) return "affectation dune valeur reel à "+mot[i-3];
				}
			}

		}
		
		else if(mot[i].equals("Real_Number")){
			i++;
			if(mot[i].equals(" "))
				i++;
				
			if(mot[i].equals(":"))
					i++;
			if(mot[i].equals(" "))
					i++;
			if(id(mot[i]) != null)i++;
				
			if(mot[i].equals(";;")) return "Déclaration de  variable reel";
				}


		
		
		else if(mot[i].equals("If")){
			i++;
			if(mot[i].equals("--")){
				i++;
			if(id(mot[i]) != null){
				i++;
				if(mot[i].equals("<") || mot[i].equals(">") || mot[i].equals("==") || mot[i].equals("<=") || mot[i].equals(">=") || mot[i].equals("!=")){
				i++;
				if(id(mot[i]) != null){
					i++;
				if(mot[i].equals("--")) return "condition alors instruction"; 
					}}}}
		}
		
		
		
		else if(mot[i].equals("Affect")){
			i++;
			if(id(mot[i]) != null){
				i++;
			if(mot[i].equals("to")){
				i++;
			if(id(mot[i]) != null) {
				i++;
			if(mot[i].equals(";;")) return "affectation de "+mot[i-3]+" a "+mot[i-1];
				}
			}
		}
		}
		
		
		else if(mot[i].equals("ShowVal")){
			i++;
			if(mot[i].equals(" "))
				i++;
				
				if(mot[i].equals(":"))
					i++;
				if(mot[i].equals(" "))
					i++;
				if(id(mot[i]) != null)i++;
					if(mot[i].equals(";;")) return "affichage de la valeur de "+mot[i-1];
				}

		

		}
						
		return "erreur symantique";

		}
	//declaration des TOKEN
	public String TOKEN (String chaine) {
		  if(chaine.equals("Start_Program"))
		  return "Start_Program : Mot reserve debut d un programme";
		  if(chaine.equals("Int_Numbe"))
		  return "Int_Number :Mot reserve Déclaration d un entier";
		  if(chaine.equals("Real_Number"))
		  return "Real_Number :Mot reserve Déclaration d un reel";
		  if(chaine.equals("Giver"))
		  return "Give : Mot reserve affectation val a var";
		  if(chaine.equals("If"))
		  return "If :Mot reserve pour Condition";
		  if(chaine.equals("Else"))
		  return "Else :Mot reserve Sinon";
		  if(chaine.equals("Start"))
		  return "Start :Mot reserve Debut d un bloc";
		  if(chaine.equals("Finish"))
		  return "Finish :fin d un bloc";
		  if(chaine.equals("Affect"))
		  return "Affect :Mot reserve Affectation var a var";
		  if(chaine.equals("ShowMes"))
		  return "ShowMes :Mot reserve pour message";
		  if(chaine.equals("ShowVa"))
		  return "ShowVal :Mot reserve show valeur d une variable";
		  if(chaine.equals("End_Program"))
		  return "End_Program :Mot reserve fin d un programme";
		  if(chaine.equals("<"))
		  return "< : Symbole reserve";
		  if(chaine.equals(">"))
		  return "> : Symbole reserve";
		  if(chaine.equals("--"))
		  return "-- : Symbole reserve";
		  if(chaine.equals(">="))
		  return ">= : Symbole reserve";
		  if(chaine.equals("<="))
		  return "<= : Symbole reserve";
		  if(chaine.equals("=="))
		  return "== : Symbole reserve";
		  if(chaine.equals("!="))
		  return "!= : Symbole reserve";
		  if(chaine.equals(","))
		  return ", : Caractere virgule";
		  if(chaine.equals(";;"))
		  return ";; : caractere reserve fin d instruction";
		  if(chaine.equals(":"))
		  return ": : caractere reserve";
		  if(chaine.equals("//."))
		  return "//. :Caractere reserve pour un commentaire";
		  if(chaine.equals("\""))
		  return "\": Symbole reserve";
		       
		  else
		return null;
		}

	
	
	
	public static void main(String[] args) {
		
					Compilateur C = new Compilateur();
					C.setVisible(true);
		
		
	}

}
