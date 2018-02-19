Network topology: A complete undirected graph on n = 5 nodes. This means, every node is connected with every other one (parallel edges and self-loops are excluded in this graph). As a result, this graph has m = 10 edges, representing the links of the network.

Components that may fail: The links of the network may fail, the nodes
are always up. The reliability of each link is p, the same for every link. The
parameter p will take different values in the experiments.

Reliability configuration: The system is considered operational, if the
network is connected.

Experiment I: Run the program for different values of p. Let the parameter p run over the [0; 1] interval, in steps of 0.04. Show graphically in a diagram how
the obtained network reliability values depend on p.

Experiment II: Now fix the p parameter at p = 0:85, and do the following experiment.
Among the 210 = 1024 possible combinations of component states pick k of the combinations randomly, and flip the corresponding system condition. That is, if the system was up, change it to down, if it was down, change it to up. Show in a diagram, how the reliability of the system changes due to this alteration. Specifically, show how the change depends on k, in the range k = 0, 1, 2, 3, …, 25: During this experiment keep the value of the parameter p fixed at p = 0.85. To reduce the effect of randomness, run several experiments and average them out, for each value of k.
