package drugware_v2;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class PharmacieTest {

	private Pharmacie pharm;
	@Rule
	public TestName name= new TestName();

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		pharm = new Pharmacie();
		System.out.println("Début du test - " + getClass().getName() + " : " + name.getMethodName());
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Fin du test - " + getClass().getName() + " : " + name.getMethodName());
	}

	@Test
	public void testPharmacie() {
		try {
			assertNotNull(pharm);
		} catch(AssertionError e) {
			fail();
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testGetLesClients() {
		List<Client> lesClients = new ArrayList<>();
		try {
		assertEquals(lesClients, pharm.getLesClients());
		} catch(AssertionError e) {
			fail();
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testSetLesClients() {
		List<Client> lesClients = new ArrayList<>();
		Client client1 = new Client("KIMV123", "Kim", "Vladislav");
		lesClients.add(client1);
		pharm.setLesClients(lesClients);
		try {
		assertEquals(lesClients, pharm.getLesClients());
		} catch(AssertionError e) {
			fail();
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testGetLesMedicaments() {
		List<Medicament> lesMedicaments = new ArrayList<>();
		try {
			assertEquals(lesMedicaments, pharm.getLesMedicaments());
		} catch(AssertionError e) {
			fail();
			System.out.println(e.getMessage());
		}
				
	}

	@Test
	public void testSetLesMedicaments() {
		List<Medicament> lesMedicaments = new ArrayList<>();
		Medicament medic1 = new Medicament();
		lesMedicaments.add(medic1);
		pharm.setLesMedicaments(lesMedicaments);
		try {
			assertEquals(lesMedicaments, pharm.getLesMedicaments());
		} catch(AssertionError e) {
			fail();
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testLireClients() {
		try {
			pharm.lireClients();
		} catch (Exception e) {
			fail("défaillance LireClients");
		}
	}

	@Test
	public void testLireMedicaments() {
		try {
			pharm.lireMedicaments();
		} catch (Exception e) {
			fail();
			System.out.println("défaillance LireMédicaments");
		}
	}

	@Test
	public void testLirePrescriptions() {
		try {
			pharm.lirePrescriptions();
		} catch (Exception e) {
			fail();
			System.out.println("défaillance LirePrescriptions");
		}
	}

	@Test
	public void testSiClientExiste() {
		pharm.lireClients();
		String NAM = "ELHM12345678";
		//
		try {
			assertTrue("défaillance ELHM12345678 non existant", pharm.siClientExiste(NAM));
			assertFalse("défaillance client 'non1' pas supposé exister", pharm.siClientExiste("non1"));
		} catch(AssertionError e) {
			fail();
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testAjouterClient() {
		Client client = new Client("KIMV1995","Kim","Vladislav");
		pharm.ajouterClient("KIMV1995","Kim","Vladislav");
		//
		try {
			assertEquals(client.afficherClient(),pharm.getLesClients().get(0).afficherClient());
			assertEquals(1, pharm.getLesClients().size());
		} catch(AssertionError e) {
			fail();
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testGetPrescriptionsClient() {
		pharm.lireClients();
		pharm.lirePrescriptions();
		List<Client> lesClients = pharm.getLesClients();
		try {
			assertEquals(lesClients.get(0).getPrescriptions(), pharm.getPrescriptionsClient("ELHM12345678"));
			assertNull(pharm.getPrescriptionsClient("nul1"));
		} catch(AssertionError e) {
			fail();
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testServirPrescription() {
		pharm.lireClients();
		pharm.lirePrescriptions();
		try {
			assertTrue(pharm.servirPrescription("DUFO12345678", "Plavix"));
			assertFalse(pharm.servirPrescription("ELHM12345678", "Botox"));
			assertFalse(pharm.servirPrescription("123", "123"));
		} catch(AssertionError e) {
			fail();
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testAjouterPrescriptionClient() {
		pharm.lireClients();
		pharm.lirePrescriptions();
		String NAM = "VKIM1995";
		Prescription presc = new Prescription("ok", 100, 1);
		List<Prescription> prescriptions = new ArrayList<>();
		prescriptions.add(presc);
		
		try {
			pharm.ajouterPrescriptionClient(NAM, presc);
			assertEquals(prescriptions, pharm.getPrescriptionsClient(NAM));
		} catch(AssertionError e) {
			fail();
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testTrouverInteraction() {
		pharm.lireMedicaments();
		try {
			assertTrue(pharm.trouverInteraction("Nexium", "clopidogrel"));
			assertTrue(pharm.trouverInteraction("Nexium", "Plavix"));
			assertTrue(pharm.trouverInteraction("Plavix", "fluvoxamine"));
			assertTrue(pharm.trouverInteraction("clopidogrel","Nexium"));
			assertFalse(pharm.trouverInteraction("Nexium", "whatever"));
		} catch(AssertionError e) {
			fail();
			System.out.println(e.getMessage());
		}
	}

}
