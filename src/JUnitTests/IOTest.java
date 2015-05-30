package JUnitTests;

import static Implementations.Others.IO.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class IOTest {

	@Test
	public void drukujTest() {
		print("Witaj");
		print("Witaj");
	}
	
	@Test
	public void drukujZNowąLinią() {
		printL("Hej");
		printL("Hej");
	}
	
	@Test
	public void drukujDoPliku() throws IOException {
		File plik = new File("test/test.txt");
		print(plik, "Witaj");
	}
	
	@Test
	public void drukujListęDoPliku() throws IOException {
		List <String> lista = Arrays.asList("Hej", "Co tam?");
		print(new File("test/test.txt"), lista);
	}
	
	@Test
	public void drukujListęDoPlikuZNowaLinią() throws IOException {
		List <String> lista = Arrays.asList("Hej", "Co tam?");
		printL(new File("test/test.txt"), lista);
	}
	
	@Test
	public void skanujPlikDoListyTest() throws IOException {
		List <String> lista = scan(new File("test/test.txt"));
		print(lista);
	}
	
	@Test
	public void skanujDoStringaTest() throws IOException {
		String linia = scanS(new File("test/test.txt"));
		print(linia);
	}

}
