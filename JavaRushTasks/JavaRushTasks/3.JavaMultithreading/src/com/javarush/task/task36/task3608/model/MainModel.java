package com.javarush.task.task36.task3608.model;


import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.model.service.UserService;
import com.javarush.task.task36.task3608.model.service.UserServiceImpl;
import java.util.List;


//Main method for Model in "MVC"
public class MainModel implements  Model {
  private   UserService userService = new UserServiceImpl();
    private ModelData modelData = new ModelData();
//method for getting list of all users
   private List<User> getAllUsers(){
       List<User> users = userService.getUsersBetweenLevels(1,100);
        return userService.filterOnlyActiveUsers(users);
    }

    @Override
    public ModelData getModelData() {
        return modelData;
    }
//method for load all users
    @Override
    public void loadUsers() {
        modelData.setUsers(getAllUsers());
        modelData.setDisplayDeletedUserList(false);
    }
//method for load all deleted users
    @Override
    public void loadDeletedUsers() {
        modelData.setUsers(userService.getAllDeletedUsers());
        modelData.setDisplayDeletedUserList(true);

}
//method for load user by Id
    @Override
    public void loadUserById(long userId) {
        User user = userService.getUsersById(userId);
        modelData.setActiveUser(user);
    }
//method for load deleted user by his Id
    @Override
    public void deleteUserById(long id) {
        userService.deleteUser(id);
        loadUsers();
    }
//method for load changed users
    @Override
    public void changeUserData(String name, long id, int level) {
        userService.createOrUpdateUser(name, id, level);
        loadUsers();
    }


}
