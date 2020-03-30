package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() throws Exception {
    app.Contact().gotoHomePage();
    Set<ContactData> before = app.Contact().all();
    app.Contact().gotoCreateContactPage();
    ContactData contact = new ContactData().withFirstname("Anton").withMiddlename("Sergeevich").withLastname("Petrov").withAddress("Moscow, Pobedy str").withMobile("89214567821").withGroup("[none]");
    app.Contact().create(contact, true);
    Set<ContactData> after = app.Contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}
