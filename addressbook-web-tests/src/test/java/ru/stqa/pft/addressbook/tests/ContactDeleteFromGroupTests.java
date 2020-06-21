package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactDeleteFromGroupTests extends TestBase {

  ContactData helpContact = new ContactData()
          .withLastname("test5").withFirstname("test5").withEmail("test5@mail.com");
  GroupData helpGroup = new GroupData().withName("test5");

  @BeforeClass
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(helpGroup);
    }
    Groups groups = app.db().groups();

    if (app.db().contacts().size() == 0) {
      app.Contact().gotoCreateContactPage();
      app.Contact().create(helpContact.inGroup(groups.iterator().next()), true);

    }
  }

  @Test
  public void testContactDeleteFromGroup() {

    ContactData userAfter = null;
    ContactData userSelect;
    GroupData groupSelect = null;

    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    app.Contact().gotoHomePage();
    userSelect = contacts.iterator().next();

    for (ContactData currentUser : contacts) {
      Groups currentGroup = currentUser.getGroups();
      if (currentGroup.size() > 0) {
        userSelect = currentUser;
        groupSelect = currentUser.getGroups().iterator().next();
        break;
      }
    }

    if (userSelect.getGroups().size() == 0) {
      groupSelect = groups.iterator().next();
      app.Contact().selectedGroup(userSelect, groupSelect);
    }
    app.Contact().gotoHomePage();
    app.Contact().selectGroupFromFilterForDeletion(groupSelect);
    app.Contact().selectContactById(userSelect.getId());
    app.Contact().clickRemoveContactFromGroup();
    app.Contact().gotoHomePage();

    Contacts usersAllAfter = app.db().contacts();
    for (ContactData userChoiceAfter : usersAllAfter) {
      if (userChoiceAfter.getId() == userSelect.getId()) {
        userAfter = userChoiceAfter;
      }
      assertThat(userSelect.getGroups(), equalTo(userAfter.getGroups().without(groupSelect)));
    }
  }
  }
