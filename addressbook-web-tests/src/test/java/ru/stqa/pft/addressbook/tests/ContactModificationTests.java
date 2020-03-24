package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {
  @Test (enabled = false)
  public void testContactModification () {

    app.Contact().gotoHomePage();
    if (! app.Contact().isThereAContact()) {
      app.Contact().gotoCreateContactPage();
      app.Contact().create(new ContactData("Ivan", "Sergeevich", "Petrov", "Frog", "TTI", "Moscow, Pobedy str", "89214567821", "ivanov@mail.ru", "test1"), false);
      app.Contact().gotoHomePage();
    }
    List<ContactData> before = app.Contact().list();
    app.Contact().selectContact(before.size()-1);
    app.Contact().initContactModification();
    ContactData contact = new ContactData(before.get(before.size()-1).getId(), "Ivan", "Sergeevich", "Petrov", "Frog", "TTI", "Moscow, Pobedy str", "89214567821", "ivanov@mail.ru", "test1");
    app.Contact().submitContactModifications();
    app.Contact().gotoHomePage();
    List<ContactData> after = app.Contact().list();
    assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
