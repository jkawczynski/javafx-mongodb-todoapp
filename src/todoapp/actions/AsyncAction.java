package todoapp.actions;

import static java.util.concurrent.Executors.newSingleThreadExecutor;
import static javafx.application.Platform.runLater;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author jkawczynski
 */
public class AsyncAction implements EventHandler<ActionEvent> {

    private final EventAction action;
    private final SuccessActionCallback successCallback;
    private final ErrorActionCallback errCallback;

    private AsyncAction(AsyncAction.Builder builder) {
        this.action = builder.ac;
        this.successCallback = builder.successCallback;
        this.errCallback = builder.errCallback;
    }

    @Override
    public void handle(ActionEvent event) {
        execute();
    }

    public void execute() {
        newSingleThreadExecutor().execute(() -> {
            try {
                Object successData = action.execute();
                if (successCallback != null) {
                    runLater(() -> successCallback.execute(successData));
                }
            } catch (Exception ex) {
                if (errCallback != null) {
                    runLater(() -> errCallback.execute(ex));
                }

            }
        });
    }

    public static class Builder<SuccessData> {

        private final EventAction<SuccessData> ac;
        private SuccessActionCallback<SuccessData> successCallback;
        private ErrorActionCallback errCallback;

        public Builder(EventAction ac) {
            this.ac = ac;
        }

        public Builder success(SuccessActionCallback<SuccessData> ac) {
            this.successCallback = ac;
            return this;
        }

        public Builder error(ErrorActionCallback ac) {
            this.errCallback = ac;
            return this;
        }

        public AsyncAction build() {
            return new AsyncAction(this);
        }

    }

}
