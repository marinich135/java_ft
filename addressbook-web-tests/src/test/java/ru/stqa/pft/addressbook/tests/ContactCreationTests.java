package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{new ContactData().withFirstname("Petr").withLastname("Ivanov").withAddress("footer")});
    list.add(new Object[]{new ContactData().withFirstname("Max").withLastname("Ivanov").withAddress("footer2")});
    list.add(new Object[]{new ContactData().withFirstname("Kolia").withLastname("Petrov").withAddress("footer3")});
    return list.iterator();
  }
    @Test(dataProvider = "validContacts")
    public void testContactCreation (ContactData contact){
      app.Contact().gotoHomePage();
      Contacts before = app.Contact().all();
      app.Contact().gotoCreateContactPage();
      app.Contact().create(contact, true);
      assertThat(app.Contact().Count(), equalTo(before.size() + 1));
      Contacts after = app.Contact().all();
      assertThat(after, equalTo(
              before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
  }