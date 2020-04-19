package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  private Properties properties;

  @BeforeMethod
  public void ensurePreconditions(){
    app.Contact().gotoHomePage();
    if ( app.Contact().all().size() == 0) {
      app.Contact().gotoCreateContactPage();
      app.Contact().create(new ContactData().withFirstname(properties.getProperty("web.firstName")).withGroup(properties.getProperty("web.group")), true);
    }
  }

  @Test
  public void testContactModification () throws IOException {
    properties = new Properties();
    properties.load(new FileReader(new File(String.format("src/test/resources/local.properties"))));
    Contacts before = app.Contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname(properties.getProperty("web.firstName")).withMiddlename(properties.getProperty("web.middlename")).withLastname(properties.getProperty("web.lastName"))
            .withAddress(properties.getProperty("web.address")).withMobilePhone(properties.getProperty("web.mobilephone")).withGroup(properties.getProperty("web.group"));
    app.Contact().modify(contact);
    assertThat(app.Contact().Count(),equalTo(before.size()));
    Contacts after = app.Contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}
