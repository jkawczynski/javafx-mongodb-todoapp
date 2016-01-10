package todoapp.actions;

/**
 *
 * @author jkawczynski
 */
public interface EventAction <SuccessData> {

     SuccessData execute() throws Exception;

}
