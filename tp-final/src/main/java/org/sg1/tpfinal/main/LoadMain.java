package org.sg1.tpfinal.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.sg1.tpfinal.model.Results;
import org.sg1.tpfinal.model.Tracker;

import com.thoughtworks.xstream.XStream;

public class LoadMain {

	public static void main(final String... args) throws FileNotFoundException {
		final Tracker tracker = loadTracker();

		new Results(tracker).printResults();
	}

	private static Tracker loadTracker() throws FileNotFoundException {
		final Tracker tracker = (Tracker) new XStream()
				.fromXML(new FileInputStream(QueryMain.FILE_PATH));
		return tracker;
	}
}
