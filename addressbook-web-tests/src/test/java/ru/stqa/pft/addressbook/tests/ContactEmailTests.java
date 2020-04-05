package ru.stqa.pft.addressbook.tests;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

  @Test
  public void TestContactEmail(){
    app.Contact().gotoHomePage();
    ContactData contact = app.Contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.Contact().infoFromEditForm(contact);

    assertThat(contact.getEmail(), equalTo(contactInfoFromEditForm.getEmail()));
    assertThat(contact.getEmail2(), equalTo(contactInfoFromEditForm.getEmail2()));
    assertThat(contact.getEmail3(), equalTo(contactInfoFromEditForm.getEmail3()));
  }
}
