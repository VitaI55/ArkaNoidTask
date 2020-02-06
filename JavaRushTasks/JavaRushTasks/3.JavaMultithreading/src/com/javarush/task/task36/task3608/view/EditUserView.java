package com.javarush.task.task36.task3608.view;


import com.javarush.task.task36.task3608.controller.Controller;
import com.javarush.task.task36.task3608.model.ModelData;
//class who has information about some User changes
public class EditUserView implements  View{
    private Controller controller;
    @Override
    public void refresh(ModelData modelData) {
        System.out.println("User to be edited:");
        System.out.println("\t" + modelData.getActiveUser());
        System.out.println("===================================================");
    }

    @Override
    public void setController(Controller controller) {
       this.controller = controller;
    }

// shows us the Users who have been deleted by Id
    public void fireEventUserDeleted(long id) {
        controller.onUserDelete(id);
    }
    // shows us the Users who have been deleted by chosen parameters
    public void fireEventUserChanged(String name, long id, int level){
        controller.onUserChange(name, id, level);
    }
}
