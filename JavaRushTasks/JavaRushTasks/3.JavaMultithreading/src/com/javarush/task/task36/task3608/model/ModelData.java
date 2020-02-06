package com.javarush.task.task36.task3608.model;
import com.javarush.task.task36.task3608.bean.User;
import java.util.List;
// Class who has all important Data for our Model
public class ModelData {
   private List<User> users;
    private User activeUser;
private  boolean displayDeletedUserList;

    public void setDisplayDeletedUserList(boolean displayDeletedUserList) {
        this.displayDeletedUserList = displayDeletedUserList;
    }

    public boolean isDisplayDeletedUserList() {
        return displayDeletedUserList;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
