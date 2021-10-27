package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {


  @BeforeMethod
  public void start() {
       app.mail().start();
   }

  @Test
  public void changePasswordTest() throws IOException, MessagingException {
    app.registration().userAuthentication(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    UserData user = selectedUser(app.dbHelper().allUsers());
    String password = "password";
    app.registration().resetUserPassword(user.getUsername());
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 60000);
    String confirmationLink = app.registration().findConfirmationLink(mailMessages, user.getEmail());
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user.getUsername(), password));

  }

  private UserData selectedUser(List<UserData> allUsers) {
    List<UserData> users = new ArrayList<>(allUsers);
    for (UserData user : users){
      if(user.getUsername().equals("administrator")) {
        allUsers.remove(user);
      }
    }
    return allUsers.get((int)Math.random() * allUsers.size());
  }

  @AfterMethod(alwaysRun = true)
  public void stop() {
    app.mail().stop();
  }
}
