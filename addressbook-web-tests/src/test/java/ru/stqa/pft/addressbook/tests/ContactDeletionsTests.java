package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionsTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.Contact().gotoHomePage();
    if (app.Contact().all().size() == 0) {
      app.Contact().gotoCreateContactPage();
      app.Contact().create(new ContactData().withFirstname("Anton").withLastname("Petrov").withGroup("[none]"), true);
    }
  }
  @Test
  public void testContactDeletions () {
    Set<ContactData> before = app.Contact().all();
    ContactData deletedContact = before.iterator().next();
    app.Contact().delete(deletedContact);
    Set<ContactData> after = app.Contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }

}
