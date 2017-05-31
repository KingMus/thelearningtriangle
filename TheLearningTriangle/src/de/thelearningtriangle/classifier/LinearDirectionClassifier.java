package de.thelearningtriangle.classifier;

import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.lossfunctions.LossFunctions.LossFunction;

public class LinearDirectionClassifier
{
	int seed = 1337;
	double learningRate = 0.01;
	int batchSize;
	int nEpochs = 30;
	int numInputs = 36;
	int numOutputs = 4;
	int numHiddenNodes = 100;
	
	public LinearDirectionClassifier()
	{
		MultiLayerConfiguration configuration = new NeuralNetConfiguration.Builder().seed(seed)
				.iterations(1)
				.optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
				.learningRate(learningRate)
				.updater(Updater.NESTEROVS)
				.list()
				.layer(0,
						new DenseLayer.Builder().nIn(numInputs)
								.nOut(numHiddenNodes)
								.weightInit(WeightInit.XAVIER)
								.activation(Activation.RELU)
								.build())
				.layer(1,
						new OutputLayer.Builder(LossFunction.NEGATIVELOGLIKELIHOOD).nIn(numHiddenNodes)
								.nOut(numOutputs)
								.weightInit(WeightInit.XAVIER)
								.activation(Activation.SOFTMAX)
								.build())
				.pretrain(false)
				.backprop(true)
				.build();
		System.out.println(configuration.toJson());
	}
	
}