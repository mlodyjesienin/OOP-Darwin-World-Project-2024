package agh.engine;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulationEngine {
    //private final List<Simulation> simulations;
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public void runAsyncInThreadPool(Simulation simulation){
        //simulations.add(simulation);
        executorService.submit(simulation);
    }
}
