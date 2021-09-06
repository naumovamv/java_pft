package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletionTests() throws Exception {

    app.gotoHomePage();
    app.selectContact();
    app.deleteSelectContact();
    app.closeAlertBox();
  }

}
