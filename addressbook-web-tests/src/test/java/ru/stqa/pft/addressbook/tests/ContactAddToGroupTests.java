package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;


public class ContactAddToGroupTests extends TestBase {

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
  public void testContactAddToGroup(){
    Contacts before = app.db().contacts();
    ContactData contact = before.iterator().next();
    Groups addedGroups = contact.getGroups();
    Groups existGroups = app.db().groups();
    Groups notAdded = new Groups();

    if (existGroups == addedGroups ) {
      app.goTo().GroupPage();
      GroupData newGroup = new GroupData().withName("the_new_group");
      app.group().create(newGroup);
      existGroups = app.db().groups();
      GroupData group = newGroup.withId(existGroups.stream().mapToInt((g) -> (g.getId())).max().getAsInt());
    }
    for (GroupData group : existGroups)  {
      if (!addedGroups.contains(group)) {
        notAdded.add(group);
      }
    }
    GroupData group = notAdded.iterator().next();
    app.Contact().addContactToGroup(contact.getId(), contact, group);
    app.Contact().gotoHomePage();
    Groups updatedGroups = contact.getGroups();

    verifyContactListInUI();
  }

}




