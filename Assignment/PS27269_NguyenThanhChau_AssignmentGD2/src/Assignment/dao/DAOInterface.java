/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment.dao;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface DAOInterface<T> {
    
    public int insert(T t);
    
    public int update(T t);
    
    public int delete(T t);
    
    public ArrayList<T> selectAll();
    
    public T selectByID(T t);
    
    public ArrayList<T> selectByCondition(String condition);
}
