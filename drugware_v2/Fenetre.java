// auteurs: Maud El-Hachem
// 2015
package drugware_v2;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Fenetre extends JFrame {
	private JMenuBar menuBar;
	private JMenu menuFic;
	private JMenu menuClients;
	private JMenu menuPresc;
	private JMenu menuMedic;

	private JMenuItem itemFic1;
	private JMenuItem itemFic2;
	private JMenuItem itemFic3;

	private JMenuItem itemClients1;
	private JMenuItem itemClients2;

	private JMenuItem itemPresc1;
	private JMenuItem itemPresc2;
	private JMenuItem itemPresc3;


	private JMenuItem itemMedic1;
	private JMenuItem itemMedic2;

	private Pharmacie pharma;

	public Fenetre() {
		this.setSize(300, 200);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			  @Override
			  public void windowClosing(WindowEvent we)
			  { 
			    String btns[] = {"Oui","Annuler"};
			    int confirm = JOptionPane.showOptionDialog(null, 
			        "Êtes-vous certain de vouloir quitter?\nMettez à jour vos fichiers pour ne pas perdre vos données", "Fermeture pharmacie", 
			        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
			        btns,btns[1]);
			    if(confirm==0)
			    {
			      System.exit(0);          
			    }
			  }
		});
			  
		this.setLocationRelativeTo(null);

		menuBar = new JMenuBar();
		menuFic = new JMenu("Fichier");
		menuClients = new JMenu("Clients");
		menuPresc = new JMenu("Prescriptions");
		menuMedic = new JMenu("Médicaments");

		itemFic1 = new JMenuItem("Charger les fichiers");
		itemFic2 = new JMenuItem("Mettre à jour les fichiers");
		itemFic3 = new JMenuItem("Quitter");
		
		itemClients1 = new JMenuItem("Inscrire un nouveau client");
		itemClients2 = new JMenuItem("Afficher tous les clients");
		itemClients2.setEnabled(false);
		
		itemPresc1 = new JMenuItem("Afficher les prescriptions d'un client");
		itemPresc1.setEnabled(false);
		itemPresc2 = new JMenuItem("Servir une prescription");
		itemPresc2.setEnabled(false);
		itemPresc3 = new JMenuItem("Ajouter une prescription");
		
		itemMedic1 = new JMenuItem("Afficher tous les médicaments");
		itemMedic1.setEnabled(false);
		itemMedic2 = new JMenuItem("Afficher si interaction");
		itemMedic2.setEnabled(false);

		pharma = new Pharmacie();

	}

	public void initMenus() {

		// Menu fichier
		itemFic1.addActionListener(new BoutonFic1Listener());
		this.menuFic.add(itemFic1);
		itemFic2.addActionListener(new BoutonFic2Listener());
		this.menuFic.add(itemFic2);

		// Ajout d'un séparateur
		this.menuFic.addSeparator();
		// si quitter
		itemFic3.addActionListener(new BoutonFic3Listener());
		this.menuFic.add(itemFic3);

		// Menu Clients
		itemClients1.addActionListener(new BoutonClient1Listener());
		this.menuClients.add(itemClients1);
		itemClients2.addActionListener(new BoutonClient2Listener());
		this.menuClients.add(itemClients2);

		// Menu Prescriptions
		itemPresc1.addActionListener(new BoutonPresc1Listener());
		this.menuPresc.add(itemPresc1);
		itemPresc2.addActionListener(new BoutonPresc2Listener());
		this.menuPresc.add(itemPresc2);
		itemPresc3.addActionListener(new BoutonPresc3Listener());
		this.menuPresc.add(itemPresc3);

		// Menu Médicaments
		itemMedic1.addActionListener(new BoutonMedic1Listener());
		this.menuMedic.add(itemMedic1);
		itemMedic2.addActionListener(new BoutonMedic2Listener());
		this.menuMedic.add(itemMedic2);

		this.menuBar.add(menuFic);
		this.menuBar.add(menuClients);
		this.menuBar.add(menuPresc);
		this.menuBar.add(menuMedic);
		this.setJMenuBar(menuBar);
		this.setVisible(true);

	}

	public class BoutonFic1Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			pharma = new Pharmacie();
			pharma.lireClients();
			pharma.lirePrescriptions();
			pharma.lireMedicaments();
			
			itemClients2.setEnabled(true);
			itemPresc1.setEnabled(true);
			itemPresc2.setEnabled(true);
			itemMedic1.setEnabled(true);
			itemMedic2.setEnabled(true);
		}
	}

	public class BoutonFic2Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			pharma.ecrireClients();
			pharma.ecrirePrescriptions();
		}
	}

	public class BoutonFic3Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
		 String btns[] = {"Oui","Annuler"};
		    int confirm = JOptionPane.showOptionDialog(null, 
		        "Êtes-vous certain de vouloir quitter?\nMettez à jour vos fichiers pour ne pas perdre vos données", "Fermeture pharmacie", 
		        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
		        btns,btns[1]);
		    if(confirm==0)
		    {
		      System.exit(0);          
		    }
		}
	}

	public class BoutonClient1Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String nom, prenom;
			String NAM = JOptionPane.showInputDialog(null,
					"Entre le numéro d'assurance maladie",
					"Numéro d'assurance maladie", JOptionPane.QUESTION_MESSAGE);
			if (NAM != null && NAM.length() > 0)
				if (pharma.siClientExiste(NAM))
					JOptionPane.showMessageDialog(null,
							"Ce numéro d'assurance maladie existe déjà",
							"Problème", JOptionPane.INFORMATION_MESSAGE);
				else {
					nom = JOptionPane.showInputDialog(null, "Entre le nom",
							"Nom", JOptionPane.QUESTION_MESSAGE);
					prenom = JOptionPane.showInputDialog(null,
							"Entre le prenom", "Prénom",
							JOptionPane.QUESTION_MESSAGE);
					if (nom != null && nom.length() > 0 && prenom != null
							&& prenom.length() > 0)
						pharma.ajouterClient(NAM, nom, prenom);
				}
		}
	}

	public class BoutonClient2Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JFrame nouvelle = new JFrame("Clients");
			int nbClients = pharma.getLesClients().size();
			JPanel panneau = new JPanel();
			panneau.setLayout(new GridLayout(nbClients, 1));
			panneau.setBackground(Color.white);
			for (Iterator<Client> it = pharma.getLesClients().iterator(); it.hasNext();) {
				Client courant = it.next();
				JLabel label = new JLabel();
				label.setText("<html><p style='font-size:14px'>"
						+ courant.afficherClient() + "</p></html>");
				panneau.add(label);
			}
			nouvelle.add(panneau);
			nouvelle.setSize(400, nbClients*100);
			nouvelle.setVisible(true);
		}
	}

	public class BoutonPresc1Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String NAM = JOptionPane.showInputDialog(null,
					"Entre le numéro d'assurance maladie",
					"Numéro d'assurance maladie", JOptionPane.QUESTION_MESSAGE);
			if (NAM != null && NAM.length() > 0) {
				List<Prescription> liste = pharma.getPrescriptionsClient(NAM);
				if (liste != null) {
					JFrame nouvelle = new JFrame("Prescription du client");
					int nbPresc = liste.size();
					JPanel panneau = new JPanel();
					panneau.setLayout(new GridLayout(nbPresc, 1));
					panneau.setBackground(Color.white);
					for (Iterator<Prescription> it = liste.iterator(); it
							.hasNext();) {
						Prescription courant = it.next();
						JLabel label = new JLabel();
						label.setText("<html><p style='font-size:14px'>"
								+ courant.afficherPrescription() + "</p></html>");
						panneau.add(label);
					}
					nouvelle.add(panneau);
					nouvelle.setSize(400, nbPresc*100);
					nouvelle.setVisible(true);
				}

			}
		}
	}

	public class BoutonPresc2Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String NAM = JOptionPane.showInputDialog(null,
					"Entre le numéro d'assurance maladie",
					"Prescription", JOptionPane.QUESTION_MESSAGE);
			String medicament = JOptionPane.showInputDialog(null,
					"Entre le nom du médicament",
					"Prescription", JOptionPane.QUESTION_MESSAGE);
			if (pharma.servirPrescription(NAM, medicament))
				JOptionPane.showMessageDialog(null,
						"Prescription servie!",
						"Prescription", JOptionPane.INFORMATION_MESSAGE);
			else 
				JOptionPane.showMessageDialog(null,
						"Il n'est pas possible de servir la prescription",
						"Prescription", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public class BoutonPresc3Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String nom;
			double dose;
			int renouvellements;
			Prescription presc;
			String NAM = JOptionPane.showInputDialog(null,
					"Entre le numéro d'assurance maladie",
					"Prescription", JOptionPane.QUESTION_MESSAGE);
			if (NAM != null && NAM.length() > 0){
				if (pharma.siClientExiste(NAM)) {
					nom = JOptionPane.showInputDialog(null,
							"Entre le nom du médicament",
							"Nom prescription", JOptionPane.QUESTION_MESSAGE);
					
					dose =  Double.parseDouble(JOptionPane.showInputDialog(null,
							"Entre le dosage de " + nom,
							"Dose prescription", JOptionPane.QUESTION_MESSAGE));
					renouvellements =  Integer.parseInt(JOptionPane.showInputDialog(null,
							"Entre le nombre de renouvellement",
							"Renouvellement prescription", JOptionPane.QUESTION_MESSAGE));
					presc = new Prescription(nom, dose, renouvellements);
					pharma.ajouterPrescriptionClient(NAM, presc);
				} else {
					JOptionPane.showMessageDialog(null,
							"Le client avec ce numéro d'assurance maladie n'existe pas encore",
							"Problème", JOptionPane.INFORMATION_MESSAGE);
				}
			
			}
		}
	}

	public class BoutonMedic1Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JFrame nouvelle = new JFrame("Médicaments");
			int nbMedic = pharma.getLesMedicaments().size();
			JPanel panneau = new JPanel();
			panneau.setLayout(new GridLayout(nbMedic, 1));
			panneau.setBackground(Color.white);
			for (Iterator<Medicament> it = pharma.getLesMedicaments()
					.iterator(); it.hasNext();) {
				Medicament courant = it.next();
				JLabel label = new JLabel();
				label.setText("<html><p style='font-size:14px'>"
						+ courant.getNomMolecule() + " "
						+ courant.getNomMarque() + "</p></html>");
				panneau.add(label);
			}
			nouvelle.add(panneau);
			nouvelle.setSize(400, nbMedic*100);
			nouvelle.setVisible(true);

		}
	}

	public class BoutonMedic2Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String medicament1 = JOptionPane.showInputDialog(null,
					"Entre le nom de la molécule ou du médicament no 1",
					"Interactions", JOptionPane.QUESTION_MESSAGE);
			String medicament2 = JOptionPane.showInputDialog(null,
					"Entre le nom de la molécule ou du médicament no 2",
					"Interactions", JOptionPane.QUESTION_MESSAGE);
			if (pharma.trouverInteraction(medicament1, medicament2))
				JOptionPane.showMessageDialog(null,
						"Interaction trouvée! Faites attention!",
						"Interactions", JOptionPane.INFORMATION_MESSAGE);
			else 
				JOptionPane.showMessageDialog(null,
						"Aucune interaction trouvée!",
						"Interactions", JOptionPane.INFORMATION_MESSAGE);
		}
	}



}