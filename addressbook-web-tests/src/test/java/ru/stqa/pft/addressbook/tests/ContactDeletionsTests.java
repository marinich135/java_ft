package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
    Contacts before = app.Contact().all();
    ContactData deletedContact = before.iterator().next();
    app.Contact().delete(deletedContact);
    assertThat(app.Contact().Count(),equalTo(before.size() - 1));
    Contacts after = app.Contact().all();
    assertThat(after, equalTo(before.without(deletedContact)));

  }

}
