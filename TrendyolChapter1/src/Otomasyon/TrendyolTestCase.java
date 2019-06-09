package Otomasyon;


import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.ClickAction;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.support.ui.Select;

public class TrendyolTestCase {

	static WebDriver driver=new ChromeDriver();
	
	
	public static void main(String[] args) throws InterruptedException {
		
		//full page
		driver.manage().window().maximize();
	    
	    System.getProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		driver.get("https://www.trendyol.com/");//yönlendirilen sayfa
		
		//step1-cinsiyet seçimi
		String cinsiyetPopup= driver.findElement(By.xpath("//*[@id=\'popupContainer']/div/div[2]/a/span[2]")).getText();
		
		//seçilmesi gereken
		if(cinsiyetPopup.contains("ERKEK"))
		{
			//Erkek secenegine tiklar
			driver.findElement(By.xpath("//*[@id=\'popupContainer\']/div/div[2]/a/span[2]")).click();
			
			//login button 
			driver.findElement(By.xpath("//*[@id=\'accountNavigationRoot\']/div/ul/li[1]/div[1]/i")).click();
			Thread.sleep(2000);
			String girisPopup=driver.findElement(By.xpath("//*[@id=\'popupLoginMain\']/h2")).getText();
			
			if(girisPopup.contains("Trendyol'a Giriþ Yapýn"))
			{
				//email
				driver.findElement(By.xpath("//*[@id='email']")).sendKeys("burhanguven34@gmail.com");
				//password
				driver.findElement(By.xpath("//*[@id=\'password\']")).sendKeys("5254586044aB");
				//button click
				try {
					driver.findElement(By.xpath("//*[@id=\'loginSubmit\']")).click();
					System.out.println("Giriþ Baþarýlý");
				} catch (Exception e) {
					System.out.println("Error: Giriþ Yapýlýrken Sorun Oluþtu!!");
				}
			}
			else {
				System.out.println("Error: Giris sayfasý açilirken sorun olustu!!");
			}
			
		}
		else {
			System.out.println("Error:Cinsiye Seçilirken Hata Oluþtu!!");
		}
		
		
		//step 2
		ArrayList<String> ustMenu=new ArrayList<String>();
		//ana sayfa menu item larýný alma
		//xpath lerini kontrol ettiðimde sadece item dan sonra gelen rakamlar deðiþtiði için array oluþturup kullanmayý tercih ettim
		String addItem;
		
		for (int i = 0; i <10; i++) {	
			addItem="//*[@id='item"+i+"']/a";
			ustMenu.add(addItem);
		}
		Thread.sleep(2000);
		
		for (int j = 1; j < ustMenu.size(); j++) {
			try {
				driver.findElement(By.xpath(ustMenu.get(j))).click();
				System.out.println(driver.findElement(By.xpath(ustMenu.get(j))).getText()+" Tab ine giris yapildi");
			} catch (Exception e) {
				System.out.println("Error: "+ustMenu.get(j)+" sayfasý açýlýrken sorun oluþtu ->>>"+ e);
			}
			Thread.sleep(2000);
		}
		
		//step3
		Random rand = new Random();
	    String randomUstMenu = ustMenu.get(rand.nextInt(ustMenu.size()));
		try {
			driver.findElement(By.xpath(randomUstMenu)).click();
				
		} catch (Exception e) {
			System.out.println(randomUstMenu+" -> Menusune giris YAPILAMADI!!!");
		}
		
		ArrayList<String> boutiques=new ArrayList<String>();
		String addBoutiques;
		//1 den 20 a kadar
		for (int x = 1; x <=20; x++) {
			addBoutiques="//*[@id=\'dynamic-boutiques\']/div/div/div["+x+"]/div[1]/a/div";
			boutiques.add(addBoutiques);
		}	
		String randomBoutiques=boutiques.get(rand.nextInt(boutiques.size()));
		
		try {
			driver.findElement(By.xpath(randomBoutiques)).click();
			
			System.out.println("Butik acildi.");
		} catch (Exception e) {
			System.out.println("Error: Butik sayfasi acilirken sorun olustu!!");
		}
		//ilk 10 ürün arasýndan random detay sayfasýna geçilecek.
		//tüm ürün kontrolü????

		ArrayList<String> urun=new ArrayList<String>();
		String addUrun;
		
		for (int a = 1; a <=10; a++) {
			addUrun="//*[@id='root']/div/ul/li["+a+"]/div/a/div[3]/div/div/img";
			urun.add(addUrun);
		}
		String randomUrunDetay=urun.get(rand.nextInt(urun.size()));
		
		try {
			driver.findElement(By.xpath(randomUrunDetay)).click();
			System.out.println("Urun detay sayfasina gidildi.");
		} catch (Exception e) {
			System.out.println("Urun detay sayfasina gidilemedi!!");
		}		
		
		//step4- ürünü sepete ekle
		
		Thread.sleep(2000);
		String control=driver.findElement(By.xpath("//*[@id='product-detail-app']/div/div[2]/div[2]/div[2]")).getText();
			
		//random butik e gidildigi icin beden secenegi olan-olamayan urunlerin kontrolu 
		if(control.contains("Beden")) {
			WebElement element=driver.findElement(By.xpath("//*[@id='product-detail-app']/div/div[2]/div[2]/div[2]/div[2]/div[2]/div/div[2]"));
			Select select=new Select(element);
			for (int s = 0; s < args.length; s++) {
				try {
					select.selectByIndex(s);
					System.out.println("Beden secildi");
					break;
				} catch (Exception e) {
					System.out.println("Beden bulunamadi. Farkli bir beden e bakilacaktir...");
				}
			}
		}
		else
		{
			try {
				driver.findElement(By.xpath("//*[@id=\'product-detail-app\']/div/div[2]/div[2]/div[2]/div[2]/button[1]")).click();
				System.out.println("Urun basarýlý bir sekilde sepete eklendi");
			} catch (Exception e) {
				System.out.println("Urun sepete eklenirken hata olustu: "+e);
			}		
		}
	
	}

}
