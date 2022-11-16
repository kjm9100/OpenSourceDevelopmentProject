package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;
import java.awt.event.ActionEvent;

import javax.swing.*;


public class SeleniumTest extends JFrame {
	
	JTextField jf_id=new JTextField("");
	JTextField jf_password=new JTextField("");
	
	String id;
	String password;
	private WebDriver BlackboardDriver;
	private WebElement BlackboardWebElement;
	
	int month;
	int MaxDay=0;
	int PreMaxDay=0;
	String Sdays[][];
	int Idays[];
	int day;
	
	public SeleniumTest() {
		setTitle("GridLayout Sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridLayout grid=new GridLayout(4,2);
		grid.setVgap(5);
		
		Container c=getContentPane();
		c.setLayout(grid);
		c.add(new JLabel("ID"));
		c.add(jf_id);
		c.add(new JLabel("Password"));
		c.add(jf_password);
		JButton jb=new JButton("Login");
		jb.addActionListener(new MyActionListener());
		c.add(jb);
		setSize(300,200);
		setVisible(true);
	}
	
	private void Login() {
		id=jf_id.getText();
		password=jf_password.getText();
		dispose();
		System.setProperty("webdriver.chrome.driver","C:\\Users\\82103\\selenium\\chromedriver.exe");
		//ChromeOptions chromeOptions=new ChromeOptions();
		//chromeOptions.addArguments("--headless");
		//chromeOptions.addArguments("--no-sandbox");
		BlackboardDriver=new ChromeDriver();
		BlackboardDriver.get("https://ecampus.chungbuk.ac.kr/");
		BlackboardWebElement=BlackboardDriver.findElement(By.name("uid"));
		BlackboardWebElement.sendKeys(id);
		BlackboardWebElement=BlackboardDriver.findElement(By.name("pswd"));
		BlackboardWebElement.sendKeys(password);
		BlackboardWebElement=BlackboardDriver.findElement(By.id("entry-login"));
		BlackboardWebElement.click();
	}
	
	private void GotoCalendar() {
		BlackboardDriver.get("https://ecampus.chungbuk.ac.kr/ultra/calendar");
		try {
            Thread.sleep(3000);
            BlackboardWebElement=BlackboardDriver.findElement(By.cssSelector("#bb-calendar1-month"));
            BlackboardWebElement.click();
    		
            Thread.sleep(2000);
            BlackboardWebElement=BlackboardDriver.findElement(By.xpath("//*[@id=\"calendar-wrapper\"]/div[1]/div[2]/div/button[1]/bb-svg-icon"));

    		Thread.sleep(2000);
    		BlackboardWebElement=BlackboardDriver.findElement(By.xpath("//*[@id=\"calendar-wrapper\"]/div[1]/div[2]/div/h2[2]/div"));
    		String TempMonth=BlackboardWebElement.getText().replace("년 ", "").replace("월", "");
    		if(TempMonth.length()>5)
    			month=Integer.parseInt(TempMonth)%100;
    		else
    			month=Integer.parseInt(TempMonth)%10;
    		
    		switch(month) {
    		case 1:
    		case 3:
    		case 5:
    		case 7:
    		case 8:
    		case 10:
    		case 12:
    			MaxDay=31;
    			break;
    		case 2:
    		case 4:
    		case 6:
    		case 9:
    		case 11:
    			MaxDay=30;
    			break;
    		default:
    			break;
    		}
    		switch(month-1) {
    		case 1:
    		case 3:
    		case 5:
    		case 7:
    		case 8:
    		case 10:
    		case 0:
    			PreMaxDay=31;
    			break;
    		case 2:
    		case 4:
    		case 6:
    		case 9:
    		case 11:
    			PreMaxDay=30;
    			break;
    		default:
    			break;
    		}
    		
			Sdays=new String[MaxDay+1][3];
			Idays=new int[MaxDay+1];
			for(int i=1;i<=MaxDay;i++) {
				Idays[i]=0;
				for(int j=0;j<3;j++)Sdays[i][j]="";
			}
			
			/*for(int i=1;i<=5;i++) {//308 416 524 633 751 860 968
				for(int j=1;j<=7;j++) {
					webElement=driver.findElement(By.xpath("//*[@id=\"fullCalendar\"]/div[1]/div/table/tbody/tr/td/div/div/div["+i+"]/div[2]/table/thead/tr/td["+j+"]/span"));
					System.out.println("Location: "+webElement.getLocation());
				}
			}*/

        } catch(Exception e) {}
		
	}
	
	private void GetCalendar() {//231 339 447 555 664 773 880
		int Location;
		int Locations[]= {-1,231,339,447,555,664,773,880};
		WebElement WebDay;
		int day;
		String[] TempLocation;
		String TempCalendar;
		BlackboardWebElement=BlackboardDriver.findElement(By.xpath("//*[@id=\"fullCalendar\"]/div[1]/div/table/tbody/tr/td/div/div/div[1]/div[2]/table/thead/tr/td[1]/span"));
		int InitialDay=Integer.parseInt(BlackboardWebElement.getText());
		InitialDay=PreMaxDay-InitialDay+1;
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=7;j++)
			{
				for(int k=1;k<=3;k++) {
					try{
						BlackboardWebElement=BlackboardDriver.findElement(By.xpath("//*[@id=\"fullCalendar\"]/div[1]/div/table/tbody/tr/td/div/div/div["+i+"]/div[2]/table/tbody/tr["+k+"]/td["+j+"]/a"));
		     			TempLocation=BlackboardWebElement.getLocation().toString().split(",");
		     			Location=Integer.parseInt(TempLocation[0].replace("(", ""));
		     			WebDay=BlackboardDriver.findElement(By.xpath("//*[@id=\"fullCalendar\"]/div[1]/div/table/tbody/tr/td/div/div/div["+i+"]/div[2]/table/thead/tr/td["+j+"]/span"));
		     			day=Integer.parseInt(WebDay.getText());
		     			TempCalendar=BlackboardWebElement.getText().replace("\n", "");

		     			if(-20<Locations[1+(0+InitialDay)%7]-Location && Locations[1+(0+InitialDay)%7]-Location<20 && day<=MaxDay && TempCalendar!="") {
		     				Sdays[1+(i-1)*7][Idays[1+(i-1)*7]++]=TempCalendar;
		     			}
		     			else if(-20<Locations[1+(1+InitialDay)%7]-Location && Locations[1+(1+InitialDay)%7]-Location<20 && day<=MaxDay&& TempCalendar!="") {
		     				Sdays[2+(i-1)*7][Idays[2+(i-1)*7]++]=TempCalendar;
		     			}
		     			else if(-20<Locations[1+(2+InitialDay)%7]-Location && Locations[1+(2+InitialDay)%7]-Location<20 && day<=MaxDay&& TempCalendar!="") {
		     				Sdays[3+(i-1)*7][Idays[3+(i-1)*7]++]=TempCalendar;
		     			}
		     			else if(-20<Locations[1+(3+InitialDay)%7]-Location && Locations[1+(3+InitialDay)%7]-Location<20 && day<=MaxDay&& TempCalendar!="") {
		     				Sdays[4+(i-1)*7][Idays[4+(i-1)*7]++]=TempCalendar;
		     			}
		     			else if(-20<Locations[1+(4+InitialDay)%7]-Location && Locations[1+(4+InitialDay)%7]-Location<20 && day<=MaxDay&& TempCalendar!="") {
		     				Sdays[5+(i-1)*7][Idays[5+(i-1)*7]++]=TempCalendar;
		     			}
		     			else if(-20<Locations[1+(5+InitialDay)%7]-Location && Locations[1+(5+InitialDay)%7]-Location<20 && day<=MaxDay&& TempCalendar!="") {
		     				Sdays[6+(i-1)*7][Idays[6+(i-1)*7]++]=TempCalendar;
		     			}
		     			else if(-20<Locations[1+(6+InitialDay)%7]-Location && Locations[1+(6+InitialDay)%7]-Location<20 && day<=MaxDay&& TempCalendar!="") {
		     				Sdays[7+(i-1)*7][Idays[7+(i-1)*7]++]=TempCalendar;
		     			}
					} catch(Exception se) {}
				}
			}
		}
	}
	
	private class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Login();
			GotoCalendar();
			GetCalendar();
			for(int i=1;i<=MaxDay;i++) {
				System.out.println(i+": ");
				for(int j=0;j<3;j++)System.out.println("\t"+Idays[i]+"\t"+Sdays[i][j]);
			}
		}
	}
	
	public static void main(String args[]) {
		new SeleniumTest();
		
	}
}