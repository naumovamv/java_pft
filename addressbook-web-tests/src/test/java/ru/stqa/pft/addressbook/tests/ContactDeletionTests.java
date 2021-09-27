package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Test").withLastname("Test"), true);
    }
  }

  @Test
  public void testContactDeletionTests() throws Exception {


    Set<ContactData> before = app.contact().all();
    ContactData deletedcontact = before.iterator().next();
    app.contact().delete(deletedcontact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedcontact);
    Assert.assertEquals(before, after);

  }
}





