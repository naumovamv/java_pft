package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {

    initContactPage();
    fillContactForm(new ContactData("Test", "Test", "Saint-Petersburg", "+71111111111", "test@test.ru"));
    submitContactCreation();
    returnToContactPage();
  }

}
