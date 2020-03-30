package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() throws Exception {
    app.Contact().gotoHomePage();
    List<ContactData> before = app.Contact().list();
    app.Contact().gotoCreateContactPage();
    ContactData contact = new ContactData().withFirstname("Anton").withMiddlename("Sergeevich").withLastname("Petrov").withAddress("Moscow, Pobedy str").withMobile("89214567821").withGroup("[none]");
    app.Contact().create(contact, true);
    List<ContactData> after = app.Contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);


    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
