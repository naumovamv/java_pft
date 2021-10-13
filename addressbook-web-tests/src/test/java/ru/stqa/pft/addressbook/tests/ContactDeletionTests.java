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
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Test 0").withLastname("Test 0").withAddress("Saint-Petersburg").withHomePhone("+78125554433")
              .withMobilePhone("+7812343545").withWorkPhone("35354534").withEmail("test@test.ru").withEmail2("test@test.ru").withEmail3("test@test.ru"), true);
    }
  }

  @Test
  public void testContactDeletionTests() throws Exception {

    Contacts before = app.db().contacts();
    ContactData deletedcontact = before.iterator().next();
    app.contact().delete(deletedcontact);
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.withoutAdded(deletedcontact)));
    verifyContactListInUI();
    }
}





