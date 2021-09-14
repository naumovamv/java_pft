package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    app.getContactHelper().initContactPage();
    app.getContactHelper().fillContactForm(new ContactData("Test", "Test","Saint-Petersburg", "+71111111111", "test@test.ru", "test1"), true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToContactPage();
  }

}
