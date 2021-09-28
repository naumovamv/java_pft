package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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


    Contacts before = app.contact().all();
    ContactData deletedcontact = before.iterator().next();
    app.contact().delete(deletedcontact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.withoutAdded(deletedcontact)));

  }
}





