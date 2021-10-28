package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddContactToGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("test 0").withFooter("test 0").withHeader("test 0"));
    }
    if (app.db().contacts().size() == 0) {
      app.goTo().HomePage();
      app.contact().create(new ContactData().withFirstname("Test 0").withLastname("Test 0").withAddress("Saint-Petersburg").withHomePhone("+78125554433")
              .withMobilePhone("+7812343545").withWorkPhone("35354534").withEmail("test@test.ru").withEmail2("test@test.ru").withEmail3("test@test.ru"), true);
    }
  }

  @Test
  public void testAddContactToGroup() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    GroupData groupAdd = new GroupData();
    ContactData modifiedContact = new ContactData();
    for (ContactData contact : contacts) {
      Set<GroupData> contactGroupsBefor = contact.getGroups();
      for (GroupData group : groups) {
        if (!contactGroupsBefor.contains(group)) {
          groupAdd = group;
          modifiedContact = contact;
          break;
        }
      }
    }
    if (groupAdd.getName() == null){
      app.goTo().GroupPage();
      groupAdd = new GroupData().withName("testgroup").withFooter("testgroup").withHeader("testgroup");
      app.group().create(groupAdd);
      ContactData anymodifiedContact = contacts.iterator().next();
      modifiedContact = anymodifiedContact;
    }

    app.contact().goToHomePage();
    app.contact().addToGroup(modifiedContact, groupAdd);
    assertEquals(app.contact().count(), contacts.size());
    Set<GroupData> contactGroupsAfter = app.db().contactById(modifiedContact.getId()).getGroups();
    assertTrue(contactGroupsAfter.contains(groupAdd));

    }
  }
  

