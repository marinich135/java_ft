package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.Contact().gotoHomePage();
    if ( app.Contact().list().size() == 0) {
      app.Contact().gotoCreateContactPage();
      app.Contact().create(new ContactData().withFirstname("Anton").withGroup("[none]"), true);
    }
  }

  @Test
  public void testContactModification () {

    List<ContactData> before = app.Contact().list();
    int index = before.size()-1;
    ContactData contact = new ContactData()
            .withId(before.get(index).getId()).withFirstname("Anton").withMiddlename("Sergeevich").withLastname("Petrov")
            .withAddress("Moscow, Pobedy str").withMobile("89214567821").withGroup("[none]");
    app.Contact().modify(index, contact);
    List<ContactData> after = app.Contact().list();
    assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
