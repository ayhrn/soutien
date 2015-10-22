// 3 bugs
// auteurs: Maud El-Hachem
// 2015
package drugware_v2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Pharmacie {
	private List<Client> lesClients;
	private List<Medicament> lesMedicaments;

	public Pharmacie() {
		this.lesMedicaments = new ArrayList<>();
		this.lesClients = new ArrayList<>();
	}

	/**
	 * @return the lesClients
	 */
	public List<Client> getLesClients() {
		return lesClients;
	}

	/**
	 * @param lesClients
	 *            the lesClients to set
	 */
	public void setLesClients(List<Client> lesClients) {
		this.lesClients = lesClients;
	}

	/**
	 * @return the lesMedicaments
	 */
	public List<Medicament> getLesMedicaments() {
		return lesMedicaments;
	}

	/**
	 * @param lesMedicaments
	 *            the lesMedicaments to set
	 */
	public void setLesMedicaments(List<Medicament> lesMedicaments) {
		this.lesMedicaments = lesMedicaments;
	}

	public void lireClients() {
		Fichiers fichier = new Fichiers();
		fichier.lireClients(lesClients);
	}

	public void lireMedicaments() {
		Fichiers fichier = new Fichiers();
		fichier.lireMedicaments(lesMedicaments);
	}

	public void lirePrescriptions() {
		Fichiers fichier = new Fichiers();
		fichier.lirePrescriptions(lesClients);
	}

	public boolean siClientExiste(String NAM) {
		for (Iterator<Client> it = lesClients.iterator(); it.hasNext();) {
			Client itClient = it.next();
			if (itClient.getNAM().equals(NAM)) {
				return true;
			}
		}
		return false;
	}

	public void ajouterClient(String NAM, String nom, String prenom) {
		this.lesClients.add(new Client(NAM, nom, prenom));
	}

	public List<Prescription> getPrescriptionsClient(String NAM) {
		for (Iterator<Client> it = lesClients.iterator(); it.hasNext();) {
			Client client = it.next();
			if (client.getNAM().equals(NAM)) {
				return client.getPrescriptions();
			}
		}
		return null;
	}
	
	public void ajouterPrescriptionClient(String NAM, Prescription presc) {
		for (Iterator<Client> it = lesClients.iterator(); it.hasNext();) {
			Client client = it.next();
			if (client.getNAM().equals(NAM)) {
				List<Prescription> liste = client.getPrescriptions();
				liste.add(presc);
				client.setPrescriptions(liste);
			}
		}
	}

	public boolean servirPrescription(String NAM, String medicament) {
		boolean delivree = false;
		for (Iterator<Client> it = lesClients.iterator(); it.hasNext();) {
			Client itClient = it.next();
			if (itClient.getNAM().equals(NAM)) {
				for (Iterator<Prescription> it2 = itClient.getPrescriptions()
						.iterator(); it2.hasNext();) {
					Prescription courante = it2.next();
					if (courante.getMedicamentAPrendre().equalsIgnoreCase(
							medicament))
						if (courante.getRenouvellements() >= 1) {
							courante.setRenouvellements(courante
									.getRenouvellements() - 1);
							delivree = true;
						}
				}
			}
		}
		return delivree;
	}

	public boolean trouverInteraction(String medicament1, String medicament2) {
		for (Iterator<Medicament> it = lesMedicaments.iterator(); it.hasNext();) {
			Medicament courant = it.next();
			// si 1er est molecule ou marque
			if (courant.getNomMolecule().equalsIgnoreCase(medicament1) || courant.getNomMarque().equalsIgnoreCase(medicament1)) {
				for (int i = 0; i < courant.getInteractions().length; i++){
					//si 2e est une molecule
					if (courant.getInteractions()[i]
							.equalsIgnoreCase(medicament2)) {
						return true;
					}
					// si 2e est marque
					for (Iterator<Medicament> it2 = lesMedicaments.iterator(); it2.hasNext();) {
						Medicament courant2 = it2.next();
						if (courant2.getNomMarque().equalsIgnoreCase(medicament2)) {			
							if (courant.getInteractions()[i].equalsIgnoreCase(courant2.getNomMolecule())) {
								return true;
							}
							for (int j = 0; j < courant2.getInteractions().length; j++){
								if (courant2.getInteractions()[j]
										.equalsIgnoreCase(medicament1)) {
									return true;
								}
							}
						}
					}
					
				}
					
			}
			

		}
		return false;
	}

	public void ecrireClients() {
		Fichiers fichier = new Fichiers();
		fichier.ecrireClients(lesClients);
	}

	public void ecrirePrescriptions() {
		Fichiers fichier = new Fichiers();
		fichier.lirePrescriptions(lesClients);
	}
}
