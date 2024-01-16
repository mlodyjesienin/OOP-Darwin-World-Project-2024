package agh.engine;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulationEngine {
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public void runAsyncInThreadPool(Simulation simulation){
        executorService.submit(simulation);
    }
}
