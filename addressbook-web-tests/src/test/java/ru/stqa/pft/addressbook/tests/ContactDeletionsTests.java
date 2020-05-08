package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class ContactDeletionsTests extends TestBase {

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
  public void testContactDeletions () {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.Contact().delete(deletedContact);
    assertThat(app.Contact().Count(),equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
    verifyContactListInUI();
  }

}
