package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {
  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void loginUI(){
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), app.getProperty("web.adminLogin"));
    click(By.cssSelector("input[value='Login']"));
    type(By.name("password"), app.getProperty("web.adminPassword"));
    click(By.cssSelector("input[value='Login']"));
  }

  public void manageUsersPage(){
    click(By.xpath("//span[contains(text(),'Manage')]/.."));
    click(By.linkText("Manage Users"));
  }

  public  void resetPassword(int id){
    click(By.xpath("//input[@value='Reset Password']"));
    click(By.linkText("Proceed"));
  }
}
