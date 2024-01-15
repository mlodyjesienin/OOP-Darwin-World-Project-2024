package agh.engine;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulationEngine {
    private final List<Simulation> simulations;
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public SimulationEngine(List<Simulation> simulations){
        this.simulations = simulations;
    }

    public void runAsyncInThreadPool(){
        for (Simulation simulation: simulations) {
            executorService.submit(simulation);
        }
    }
}
