package software.ulpgc.kata6.apps.windows;

import software.ulpgc.kata6.apps.windows.adapters.CalculateWorkingDateAdapter;
import software.ulpgc.kata6.apps.windows.adapters.CalculateWorkingDaysAdapter;
import software.ulpgc.kata6.architecture.control.CommandFactory;
import software.ulpgc.kata6.architecture.control.WorkingDateCommand;
import software.ulpgc.kata6.architecture.control.WorkingDaysCommand;
import software.ulpgc.kata6.architecture.model.Calendar;
import spark.Request;
import spark.Response;

public class Main {
    public static void main(String[] args) {
        new WebService(commandFactory()).init();
    }

    private static CommandFactory commandFactory() {
        return new CommandFactory().add("working-date", Main::createWorkingDateCommand)
                .add("working-days", Main::createWorkingDaysCommand);
            }

    private static WorkingDaysCommand createWorkingDaysCommand(Request request, Response response) {
        return new WorkingDaysCommand(
                CalculateWorkingDaysAdapter.adapt(request), new Calendar(),
                CalculateWorkingDaysAdapter.adapt(response)
        );
    }

    private static WorkingDateCommand createWorkingDateCommand(Request request, Response response) {
        return new WorkingDateCommand(
                CalculateWorkingDateAdapter.adapt(request), new Calendar(),
                CalculateWorkingDateAdapter.adapt(response)
        );
    }


}
