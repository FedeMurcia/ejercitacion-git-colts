package utn.frba.ia.rna.tpG14;

import java.util.Arrays;

import org.neuroph.core.learning.TrainingElement;
import org.neuroph.core.learning.TrainingSet;
import org.neuroph.nnet.Hopfield;

public class PruebaRNA {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// HopfieldSample.main(args);
		hopfieldModificado(args);
	}

	public static void hopfieldModificado(String args[]) {

		// create training set (H and T letter in 3x3 grid)
		TrainingSet trainingSet = new TrainingSet();
		trainingSet.addElement(new TrainingElement(new double[] { 1, 0, 1, 1,
				1, 1, 1, 0, 1 })); // H letter

		trainingSet.addElement(new TrainingElement(new double[] { 1, 1, 1, 0,
				1, 0, 0, 1, 0 })); // T letter

		// Le enseñamos la L
		// trainingSet.addElement(new TrainingElement(new double[]{1, 0, 0,
		// 1, 0, 0,
		// 1, 1, 1})); // L letter
		// La Llllll

		// create hopfield network
		Hopfield myHopfield = new Hopfield(9);
		// learn the training set
		myHopfield.learnInSameThread(trainingSet);

		// test hopfield network
		System.out.println("Testing 1network");

		// add one more 'incomplete' H pattern for testing - it will be
		// recognized as H
		trainingSet.addElement(new TrainingElement(new double[] { 1, 0, 0, 1,
				0, 1, 1, 0, 1 }));

		// print network output for the each element from the specified training
		// set.
		for (TrainingElement trainingElement : trainingSet.trainingElements()) {
			myHopfield.setInput(trainingElement.getInput());
			myHopfield.calculate();
			myHopfield.calculate();
			double[] networkOutput = myHopfield.getOutput();

			System.out.print("Input: "
					+ Arrays.toString(trainingElement.getInput()));
			System.out.println(" Output: " + Arrays.toString(networkOutput));
		}

	}

}
