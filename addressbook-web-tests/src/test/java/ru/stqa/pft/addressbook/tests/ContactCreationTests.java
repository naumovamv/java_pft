package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.initContactPage();
    app.fillContactForm(new ContactData("Test", "Test", "Saint-Petersburg", "+71111111111", "test@test.ru"));
    app.submitContactCreation();
    app.returnToContactPage();
  }

}
