package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase{


  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().gotoCreateContactPage();
    app.getContactHelper().fillContactForm(new ContactData("Ivan", "Sergeevich", "Petrov", "Frog", "TTI", "Moscow, Pobedy str", "89214567821", "ivanov@mail.ru", "test1"), true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size() + 1);
  }


}
