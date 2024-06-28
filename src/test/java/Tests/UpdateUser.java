package Tests;

import org.apiTest.Dto.User;

public class UpdateUser {
    public String createUserBody(String name,String job){
        User user = new User();
        user.setName(name);
        user.setJob(job);
        return user.toString();
    }
    public String updateUserBody(String name,String job){
        User user = new User();
        user.setName(name);
        user.setJob(job);
        return user.toString();
    }

}