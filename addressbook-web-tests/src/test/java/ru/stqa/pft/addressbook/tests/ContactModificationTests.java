package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification () {

    app.getContactHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().gotoCreateContactPage();
      app.getContactHelper().createContact(new ContactData("Ivan", "Sergeevich", "Petrov", "Frog", "TTI", "Moscow, Pobedy str", "89214567821", "ivanov@mail.ru", "test1"), true);
      app.getContactHelper().gotoHomePage();
    }
    
    app.getContactHelper().selectContact(before-1);
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Igor", "Vladimirovich","Kuzmenko","Izya","TOR","Moscow", "8952365843","Igoryan@mail.ru", null), false);
    app.getContactHelper().submitContactModifications();
    app.getContactHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after,before);
  }
}
