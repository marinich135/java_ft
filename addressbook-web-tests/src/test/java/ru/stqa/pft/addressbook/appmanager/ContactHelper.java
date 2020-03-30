package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import static org.testng.Assert.*;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
   }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"),contactData.getMiddlename());
    type(By.name("lastname"),contactData.getLastname());
    type(By.name("nickname"),contactData.getNickname());
    type(By.name("company"),contactData.getCompany());
    type(By.name("address"),contactData.getAddress());
    click(By.xpath("//div[@id='content']/form/label[9]"));
    type(By.name("mobile"),contactData.getMobile());
    type(By.name("email"),contactData.getEmail());

    if(creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse((isElementPresent(By.name("new_group"))));
    }
  }

  public void gotoCreateContactPage() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void gotoHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    wd.findElement(By.linkText("home")).click();
  }

  public void submitContactCreation() {

    wd.findElement(By.name("submit")).click();
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }
  public void acceptAlert() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void submitContactModifications() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//img[@alt='Edit']"));
  }

  public void create(ContactData contactData, boolean creation) {
    fillContactForm (contactData, true);
    submitContactCreation();
    gotoHomePage();
  }

  public void modify(int index, ContactData contact) {
    selectContact(index);
    initContactModification(index);
    fillContactForm(contact, false);
    submitContactModifications();
    gotoHomePage();
  }
  public void delete(int index) {
    selectContact(index);
    deleteSelectedContacts();
    acceptAlert();
    gotoHomePage();
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname("Petrov"));
    }
    return contacts;
  }
  public Set<ContactData> all() {
    Set<ContactData> contacts = new HashSet<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname("Petrov"));
    }
    return contacts;
  }
}
