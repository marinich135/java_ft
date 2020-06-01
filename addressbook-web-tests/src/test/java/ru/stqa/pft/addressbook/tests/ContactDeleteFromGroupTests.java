package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContactDeleteFromGroupTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("test1"));
    }

    if (app.db().contacts().size() == 0) {
      app.Contact().gotoCreateContactPage();
      app.Contact().create(new ContactData().withFirstname("test").withLastname("test1").withEmail("test@test.ru"), true);
    }
  }

  public List<Integer> validGroupAndContactID() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();

    List<Integer> validGroupAndContactID = new ArrayList<>();
    for (ContactData contact : contacts) {
      for (GroupData group : groups) {
        if (app.Contact().isContactInGroup(contact, group)) {
          validGroupAndContactID.add(group.getId());
          validGroupAndContactID.add(contact.getId());
          return validGroupAndContactID;
        }
      }
    }
    return validGroupAndContactID;
  }

  @Test
  public void testContactDeleteFromGroup() {
    List<Integer> validID= validGroupAndContactID();
    Contacts before = app.db().contacts();
    Groups groups = app.db().groups();

    ContactData modifiedContact = before.stream().filter(data -> Objects.equals(data.getId(), validID.get(1))).findFirst().get();
    GroupData groupUnassigned = groups.stream().filter(data -> Objects.equals(data.getId(), validID.get(0))).findFirst().get();

    ContactData contact = modifiedContact;


    app.Contact().gotoHomePage();
    app.Contact().deleteContactFromGroup(contact, groupUnassigned);
    Contacts after = app.db().contacts();
    ContactData contactModifiedDb = after.stream().filter(data -> Objects.equals(data.getId(), modifiedContact.getId())).findFirst().get();
    Assert.assertFalse(app.Contact().isContactInGroup(contactModifiedDb, groupUnassigned ));
  }

}
