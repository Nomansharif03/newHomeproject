package TakeHomeProject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class TakeHomeProjectObjects {

	WebDriver driver;
	Logger logger = Logger.getLogger("WeekendTaskObject");
	
	@FindBy(xpath="//a[@class='departments-menu-v2-title']")
	WebElement clickOnProducts;
	@FindBy(xpath="//h2[@class='woocommerce-loop-product__title']")
	WebElement LaptopNames123;
	@FindBy(xpath="//ul[@id='menu-all-departments-1']//li[@id='menu-item-4761']")
	WebElement clickonlaptopButton;
	@FindBy(xpath="//div[@class=\"product-short-description\"]")
	WebElement LaptopDescription;
	@FindBy(xpath="//select[@name='ppp']")
	WebElement ClickOnNumberofProducts;	
	@FindBy(xpath="//span[@class='woocommerce-Price-currencySymbol']")
	WebElement LaptopPrice;
	@FindBy(xpath="//img[@class='attachment-woocommerce_thumbnail size-woocommerce_thumbnail jetpack-lazy-image jetpack-lazy-image--handled']\r\n")
	WebElement LaptopImages;
	
	ArrayList<String> LaptopNames1 = new ArrayList<String>();
	ArrayList<String> LaptopPrice1 = new ArrayList<String>();
	ArrayList<String> LaptopDescription1 = new ArrayList<String>();
	
	
	public TakeHomeProjectObjects(WebDriver driver1){
		driver=driver1;
		PageFactory.initElements(driver1,this);}
	
	  public void clicklaptopButton() throws Exception {
//		  driver.manage().window().maximize();
//			Actions hover=new Actions(driver);
//			hover.moveToElement(clickOnProducts).perform();
//			Thread.sleep(1000);
			clickonlaptopButton.click();
			Select dd=new Select(ClickOnNumberofProducts);
			dd.selectByVisibleText("Show All");
			Thread.sleep(2000);
		  
	  }
	  public void LaptopNames() throws InterruptedException {
		  // ArrayList<String> LaptopNames1 = new ArrayList<String>(); 
		  Thread.sleep(2000);
		   for(int names=2;names<319;names=names+2) {
		   WebElement LaptopNames2 =  driver.findElement(By.xpath("(//h2[@class=\"woocommerce-loop-product__title\"])["+ names +"]"));
			   LaptopNames1.add(LaptopNames2.getText());
		   }
		   Thread.sleep(2000);
		  // ArrayList<String> LaptopPrice1 = new ArrayList<String>(); 
		   for(int price=2; price<161; price++) {
		   WebElement LaptopPrice2= driver.findElement(By.xpath("(//bdi)["+ price +"]"));
		   LaptopPrice1.add((LaptopPrice2).getText());
			   
		   }
		   Thread.sleep(2000);
		  // ArrayList<String> LaptopDescription1 = new ArrayList<String>(); 
		   for(int description=1;description<160;description++) {
		   WebElement LaptopDescription2= driver.findElement(By.xpath("(//div[@class=\"product-short-description\"])["+ description +"]"));
		   LaptopDescription1.add((LaptopDescription2).getText());
			  
			  
		   }
		   
	  }  	   
	   public void ExcelData() throws Exception {
		   
		   XSSFWorkbook WorkBook=new XSSFWorkbook();
		   XSSFSheet WBsheet = WorkBook.createSheet("ExcelData");
		   WBsheet.createRow(0);
		   WBsheet.getRow(0).createCell(0).setCellValue("Laptop Names");
		   WBsheet.getRow(0).createCell(1).setCellValue("Laptop Price");
		   WBsheet.getRow(0).createCell(2).setCellValue("Laptop Description");

			for(int i=0;i<LaptopNames1.size();i++) {
				WBsheet.createRow(i+1);
	            WBsheet.createRow(i+1).createCell(0).setCellValue(LaptopNames1.get(i));
	            }
			for(int i=0;i<LaptopPrice1.size();i++) {
//				WBsheet.createRow(i+1);
	            WBsheet.getRow(i+1).createCell(1).setCellValue(LaptopPrice1.get(i));
			}
			for(int i=0;i<LaptopDescription1.size();i=i++) {
//				WBsheet.createRow(i+1);
	            WBsheet.getRow(i+1).createCell(2).setCellValue(LaptopDescription1.get(i));
			}
//			 List<WebElement> image=driver.findElements(By.xpath("//div[@class='product-thumbnail product-item__thumbnail']")); 
//			int count=1;
//			for(WebElement e:image) {
//				String src1=(e.getAttribute("srcset"));
//				
//				URL imageURL=new URL(src1);
//				BufferedImage saveimage=ImageIO.read(imageURL);
//				ImageIO.write(saveimage, "jpg", new File("count"+".jpg"));
//				count++;
//			}
			
			File file=new File("C:\\Users\\Ali Yar Khan\\Desktop\\Excel files\\ExcelData.xlsx");
			FileOutputStream fileoutputstream=new FileOutputStream(file);
			WorkBook.write(fileoutputstream);
	
  }	
	  
}

