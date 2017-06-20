package de.thelearningtriangle.classifier;

import java.io.File;
import java.io.IOException;

import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.cpu.nativecpu.NDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.lossfunctions.LossFunctions.LossFunction;

import de.thelearningtriangle.core.overworld.Direction;

public class LinearDirectionClassifier {
	int seed = 123;
	double learningRate = 0.1;
	int batchSize = 12000;
	int nEpochs = 20;
	int numInputs = 36;
	int numOutputs = 4;
	private MultiLayerNetwork model;

	public LinearDirectionClassifier() throws IOException, InterruptedException {
		RecordReader trainRR = new CSVRecordReader();
		trainRR.initialize(new FileSplit(new File("train.csv")));
		DataSetIterator trainIter = new RecordReaderDataSetIterator(trainRR, batchSize, 0, 4);

		RecordReader testRR = new CSVRecordReader();
		testRR.initialize(new FileSplit(new File("test.csv")));
		DataSetIterator testIter = new RecordReaderDataSetIterator(testRR, batchSize, 0, 4);

		MultiLayerConfiguration configuration = new NeuralNetConfiguration.Builder()
				.seed(seed)
				.iterations(10)
				.optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
				.learningRate(learningRate)
				.updater(Updater.NESTEROVS)
				.momentum(0.9)
				.list()
				.layer(
						0,
						new DenseLayer.Builder()
								.nIn(numInputs)
								.nOut(12)
								.weightInit(WeightInit.XAVIER)
								.activation(Activation.RELU)
								.build())
				.layer(
						1,
						new DenseLayer.Builder()
								.nIn(12)
								.nOut(6)
								.weightInit(WeightInit.XAVIER)
								.activation(Activation.RRELU)
								.build())

				.layer(
						2,
						new OutputLayer.Builder(LossFunction.NEGATIVELOGLIKELIHOOD)
								.nIn(6)
								.nOut(numOutputs)
								.weightInit(WeightInit.XAVIER)
								.activation(Activation.SOFTMAX)
								.build())
				.pretrain(true)
				.backprop(true)
				.build();

		model = new MultiLayerNetwork(configuration);
		model.init();
		model.setListeners(new ScoreIterationListener(10));

		for (int n = 0; n < nEpochs; n++) {
			model.fit(trainIter);
		}

		Evaluation evaluation = new Evaluation(numOutputs);

		while (testIter.hasNext()) {
			DataSet t = testIter.next();
			INDArray features = t.getFeatureMatrix();
			INDArray labels = t.getLabels();
			INDArray predicted = model.output(features);
			evaluation.eval(labels, predicted);
		}
		System.out.println(evaluation.stats());
	}

	public Direction predict(Integer[] integers) {
		double[][] visionVector = new double[1][integers.length];
		for (int i = 0; i < integers.length; i++) {
			visionVector[0][i] = integers[i];
		}
		INDArray features = new NDArray(visionVector);
		INDArray predict = model.output(features);

		int bestIndex = 0;
		for (int i = 0; i < numOutputs; i++) {
			if (predict.getDouble(i) > predict.getDouble(bestIndex)) {
				bestIndex = i;
			}
		}

		return Direction.getDirectionFor(bestIndex);
	}
}