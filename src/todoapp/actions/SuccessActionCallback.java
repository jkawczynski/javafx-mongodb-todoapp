package todoapp.actions;

/**
 *
 * @author jkawczynski
 */
public interface SuccessActionCallback<SuccessData> {
    
    public void execute(SuccessData data);
    
}
