package Tests;

import org.testng.annotations.Test;

import Pages.LoginPage;
import Pages.NavigationMenu;
import Utils.BaseTest;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;





public class LoginTest extends BaseTest {
    
	@Parameters({"user","pass"})
	@Test
	public void loginTest(String user, String pass) throws InterruptedException {
		NavigationMenu menu = new NavigationMenu(driver);
		menu.navigateTo(menu.shopLink);
		
		menu.navigateTo(menu.contactLink);
		
		menu.navigateTo(menu.loginLink);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginInApp(user, pass);
		Thread.sleep(5000);
		loginPage.logoutFromApp();
	}
	
	@Test(priority = 1)
	public void invalidLoginTest() {
		NavigationMenu menu = new NavigationMenu(driver);
		menu.navigateTo(menu.shopLink);
		
		menu.navigateTo(menu.contactLink);
		
		menu.navigateTo(menu.loginLink);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginInApp("UserGresit", "ParolaGresita");
	
	}
	
}
