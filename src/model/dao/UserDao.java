package model.dao;

public class UserDao {

    private UserDao(){}
    private static final UserDao instance = new UserDao();
    public static UserDao getInstance(){return instance;}
}
