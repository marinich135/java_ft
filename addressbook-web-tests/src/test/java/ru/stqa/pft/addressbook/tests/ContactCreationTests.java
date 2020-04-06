package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() throws Exception {
    app.Contact().gotoHomePage();
    Contacts before = app.Contact().all();
    app.Contact().gotoCreateContactPage();
    File photo = new File("src/test/resources/stru.png");
    ContactData contact = new ContactData().withFirstname("Anton").withMiddlename("Sergeevich").withLastname("Petrov")
            .withAddress("Moscow, Pobedy str").withMobilePhone("89214567821").withGroup("[none]").withPhoto(photo);
    app.Contact().create(contact, true);
    assertThat(app.Contact().Count(),equalTo(before.size() + 1));
    Contacts after = app.Contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
  @Test (enabled = false)
  public void testCurrentDir(){
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/stru.png");
    System.out.println(currentDir.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
