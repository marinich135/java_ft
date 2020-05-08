package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactModificationTests extends TestBase {

  private Properties properties;

  @BeforeMethod
  public void ensurePreconditions(){
    Groups groups = app.db().groups();
    if ( app.db().contacts().size() == 0) {
      app.Contact().gotoCreateContactPage();
      app.Contact().create(new ContactData().withFirstname(properties.getProperty("web.firstName"))
              .inGroup(groups.iterator().next()), true);
    }
  }

  @Test
  public void testContactModification () throws IOException {
    Groups groups = app.db().groups();
    properties = new Properties();
    properties.load(new FileReader(new File(String.format("src/test/resources/local.properties"))));
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstname(properties.getProperty("web.firstName"))
            .withMiddlename(properties.getProperty("web.middlename"))
            .withLastname(properties.getProperty("web.lastName"))
            .withAddress(properties.getProperty("web.address"))
            .withMobilePhone(properties.getProperty("web.mobilephone"))
            .inGroup(groups.iterator().next());
    app.Contact().gotoHomePage();
    app.Contact().modify(contact);
    assertThat(app.Contact().Count(),equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }

}
