package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionsTests extends TestBase{

  @Test
  public void testGroupDeletions() throws Exception {
    gotoGroupPage();
    selectGroup();
    deleteSelectedGroups();
    returnToGroupPage();
  }


}
