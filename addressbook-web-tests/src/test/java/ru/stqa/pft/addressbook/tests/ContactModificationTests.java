package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
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

    Contacts before = app.Contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Anton").withMiddlename("Sergeevich").withLastname("Petrov")
            .withAddress("Moscow, Pobedy str").withMobilePhone("89214567821").withGroup("[none]");
    app.Contact().modify(contact);
    assertThat(app.Contact().Count(),equalTo(before.size()));
    Contacts after = app.Contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}
