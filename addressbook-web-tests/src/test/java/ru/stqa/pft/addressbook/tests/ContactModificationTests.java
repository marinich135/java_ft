package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification () {

    app.getContactHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Igor", "Vladimirovich","Kuzmenko","Izya","TOR","Moscow", "8952365843","Igoryan@mail.ru"));
    app.getContactHelper().submitContactModifications();
    app.getContactHelper().gotoHomePage();
  }
}