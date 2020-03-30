package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionsTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    app.Contact().gotoHomePage();
    if (app.Contact().list().size() == 0) {
      app.Contact().gotoCreateContactPage();
      app.Contact().create(new ContactData().withFirstname("Anton").withLastname("Petrov").withGroup("[none]"), true);
    }
  }
  @Test
  public void testContactDeletions () {
    List<ContactData> before = app.Contact().list();
    int index = before.size()-1;
    app.Contact().delete(index);
    List<ContactData> after = app.Contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }

}
