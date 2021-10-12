package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

@BeforeMethod
public void ensurePreconditions() {

  if (app.db().contacts().size() == 0) {
    Groups groups = app.db().groups();
    if (groups.size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("test 0").withFooter("test 0").withHeader("test 0"));
    }
    app.goTo().HomePage();
    app.contact().create(new ContactData().withFirstname("Test 0").withLastname("Test 0").withAddress("Saint-Petersburg").withHomePhone("+78125554433")
            .withMobilePhone("+7812343545").withWorkPhone("35354534").withEmail("test@test.ru").withEmail2("test@test.ru").withEmail3("test@test.ru")
            .withGroup(groups.iterator().next()), true);
  }
}

  @Test
  public void testContactModification() {

    Contacts before = app.db().contacts();
    Groups groups = app.db().groups();
    app.goTo().HomePage();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Test 0").withLastname("Test 0").withAddress("Saint-Petersburg").withHomePhone("+78125554433")
            .withMobilePhone("+7812343545").withWorkPhone("35354534").withEmail("test@test.ru").withEmail2("test@test.ru").withEmail3("test@test.ru")
            .withGroup(groups.iterator().next());
    app.contact().modify(contact);
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.withoutAdded(modifiedContact).withAdded(contact)));
  }
}
