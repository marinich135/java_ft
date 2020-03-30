package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.Contact().gotoHomePage();
    if ( app.Contact().all().size() == 0) {
      app.Contact().gotoCreateContactPage();
      app.Contact().create(new ContactData().withFirstname("Anton").withGroup("[none]"), true);
    }
  }

  @Test
  public void testContactModification () {

    Set<ContactData> before = app.Contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Anton").withMiddlename("Sergeevich").withLastname("Petrov")
            .withAddress("Moscow, Pobedy str").withMobile("89214567821").withGroup("[none]");
    app.Contact().modify(contact);
    Set<ContactData> after = app.Contact().all();
    assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}
