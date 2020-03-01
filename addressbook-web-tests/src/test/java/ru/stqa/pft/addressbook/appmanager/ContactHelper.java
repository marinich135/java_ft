package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
   }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"),contactData.getMiddlename());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("nickname"),contactData.getNickname());
    type(By.name("company"),contactData.getCompany());
    type(By.name("address"),contactData.getAddress());
    click(By.xpath("//div[@id='content']/form/label[9]"));
    type(By.name("mobile"),contactData.getMobile());
    type(By.name("email"),contactData.getEmail());

  }

  public void gotoCreateContactPage() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void gotoHomePage() {

    wd.findElement(By.linkText("home")).click();
  }

  public void submitContactCreation() {

    wd.findElement(By.name("submit")).click();
  }
}
