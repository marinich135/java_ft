package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionsTests extends TestBase {

  @Test (enabled = false)
  public void testContactDeletions () {

    app.Contact().gotoHomePage();
    if (! app.Contact().isThereAContact()) {
      app.Contact().gotoCreateContactPage();
      app.Contact().create(new ContactData("Ivan", "Sergeevich", "Petrov", "Frog", "TTI", "Moscow, Pobedy str", "89214567821", "ivanov@mail.ru", "test1"), true);
      app.Contact().gotoHomePage();
    }
    List<ContactData> before = app.Contact().list();
    app.Contact().selectContact(before.size()-1);
    app.Contact().deleteSelectedContacts();
    app.Contact().acceptAlert();
    app.Contact().gotoHomePage();
    List<ContactData> after = app.Contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}
