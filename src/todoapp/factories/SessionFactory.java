package todoapp.factories;

import todoapp.utils.Session;

/**
 *
 * @author jkawczynski
 */
public class SessionFactory {
    
    public Session getCurrentSession(){
        return Session.getInstance();
    }
    
}
