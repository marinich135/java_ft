package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;


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

  public void selectContactById(int id) {

    wd.findElement((By.cssSelector("a[href*='edit.php?id=" + id + "']"))).click();
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
    contactCach = null;
    gotoHomePage();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    fillContactForm(contact, false);
    submitContactModifications();
    contactCach = null;
    gotoHomePage();
  }


  public void delete(ContactData Contact) {
    selectContactById(Contact.getId());
    deleteSelectedContacts();
    contactCach = null;
    gotoHomePage();
  }

  public int Count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCach = null;

  public Contacts all() {
    if (contactCach != null) {
      return new Contacts(contactCach);
    }
    contactCach = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String[] phones = cells.get(5).getText().split("\n");
      contactCach.add(new ContactData().withId(id).withFirstname(firstname).withLastname("Petrov")
              .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
    }
    return new Contacts(contactCach);
  }

    public ContactData infoFromEditForm(ContactData contact) {
     selectContactById(contact.getId());
     String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
     String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
     String home = wd.findElement(By.name("home")).getAttribute("value");
     String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
     String work = wd.findElement(By.name("work")).getAttribute("value");
     wd.navigate().back();
     return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
             .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);

    }

  }
