package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionsTests extends TestBase {

  @Test
  public void testContactDeletions () {

    app.getContactHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().gotoCreateContactPage();
      app.getContactHelper().createContact(new ContactData("Ivan", "Sergeevich", "Petrov", "Frog", "TTI", "Moscow, Pobedy str", "89214567821", "ivanov@mail.ru", "test1"), true);
      app.getContactHelper().gotoHomePage();
    }
    app.getContactHelper().selectContact(before-1);
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().acceptAlert();
    app.getContactHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after,before - 1);
  }

}
