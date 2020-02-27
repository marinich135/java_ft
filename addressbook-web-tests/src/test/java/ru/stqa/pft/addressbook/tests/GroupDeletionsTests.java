package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionsTests extends TestBase{

  @Test
  public void testGroupDeletions() throws Exception {
    app.gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }


}
