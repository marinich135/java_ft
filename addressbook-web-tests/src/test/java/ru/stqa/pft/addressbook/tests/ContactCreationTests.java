package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() throws Exception {
    app.Contact().gotoHomePage();
    Contacts before = app.Contact().all();
    app.Contact().gotoCreateContactPage();
    ContactData contact = new ContactData().withFirstname("Anton").withMiddlename("Sergeevich").withLastname("Petrov").withAddress("Moscow, Pobedy str").withMobilePhone("89214567821").withGroup("[none]");
    app.Contact().create(contact, true);
    assertThat(app.Contact().Count(),equalTo(before.size() + 1));
    Contacts after = app.Contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
