/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.dao;

/**
 *
 * @author btssio
 */
public class DaoH2 extends Dao{

    public DaoH2(String nomBd, String loginBd, String mdpBd) {
        super("org.h2.Driver", "jdbc:h2:~/" + nomBd, loginBd, mdpBd);
    }
    
}
