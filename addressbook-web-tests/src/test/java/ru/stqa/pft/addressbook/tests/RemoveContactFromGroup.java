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
import static org.testng.Assert.*;

public class RemoveContactFromGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    GroupData group = new GroupData();
    ContactData contact = new ContactData();

    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      Groups before = app.db().groups();
      app.group().create(new GroupData().withName("test 0").withFooter("test 0").withHeader("test 0"));
      assertThat(app.group().count(), equalTo(before.size() + 1));
    }

    if (app.db().contacts().size() == 0) {
      app.goTo().HomePage();
      Contacts before = app.db().contacts();
      app.contact().create(new ContactData().withFirstname("Test 0").withLastname("Test 0").withAddress("Saint-Petersburg").withHomePhone("+78125554433")
              .withMobilePhone("+7812343545").withWorkPhone("35354534").withEmail("test@test.ru").withEmail2("test@test.ru").withEmail3("test@test.ru"), true);
      assertThat(app.contact().count(), equalTo(before.size() + 1));
    }
  }


  @Test
  public void testRemoveContactFromGroup() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    GroupData groupAdd = new GroupData();
    ContactData modifiedContact = new ContactData();
    boolean isContactAddedToGroup = false;
    for (ContactData contact : contacts) {
      Set<GroupData> contactGroupsBefor = contact.getGroups();
      for (GroupData group : groups) {
        if (contactGroupsBefor.contains(group)) {
          groupAdd = group;
          modifiedContact = contact;
          isContactAddedToGroup = true;
          break;
        }
      }
    }
    if (isContactAddedToGroup == false) {
      ContactData anymodifiedContact = contacts.iterator().next();
      modifiedContact = anymodifiedContact;
      groupAdd = groups.iterator().next();
      app.contact().goToHomePage();
      app.contact().addToGroup(modifiedContact, groupAdd);
    }

    app.contact().deleteFromGroup(modifiedContact, groupAdd);
    app.contact().goToHomePage();
    assertThat(app.contact().count(), equalTo(contacts.size()));
    assertFalse(app.db().contactById(modifiedContact.getId()).getGroups().contains(groupAdd));

  }















   /*
    Contacts before = app.db().contacts();
    Groups groups = app.db().groups();
    GroupData groupAdd = new GroupData();
    ContactData modifiedContact = before.iterator().next();
    Set<GroupData> contactGroupsBefor = modifiedContact.getGroups();
    for (GroupData group: groups) {
      if (contactGroupsBefor.contains(group)) {
        app.contact().deleteFromGroup(modifiedContact, group);
        groupAdd = group;
        break;
      }
    }
    app.contact().goToHomePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    assertFalse(app.db().contactById(modifiedContact.getId()).getGroups().contains(groupAdd));
  }

    */
}
