package com.luizjacomn.threads.task;

import java.util.Scanner;
import java.util.logging.Logger;

public class BuscarEmArquivo implements Runnable {

	private static final Logger LOGGER = Logger.getLogger(BuscarEmArquivo.class.getSimpleName());

	private String nomeArquivo;
	private String termo;

	public BuscarEmArquivo(String nomeArquivo, String termo) {
		this.nomeArquivo = nomeArquivo;
		this.termo = termo;
	}

	@Override
	public void run() {
		try (Scanner scanner = new Scanner(getClass().getClassLoader().getResourceAsStream(nomeArquivo))) {
			StringBuilder builder = new StringBuilder(
					String.format("### Buscar \"%s\" no arquivo \"%s\" ###", termo, nomeArquivo));

			int lineNumber = 1;
			boolean found = false;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();

				if (line.toLowerCase().contains(termo.toLowerCase())) {
					found = true;
					builder.append("\n").append(lineNumber).append(" - ").append(line);
				}

				lineNumber++;
			}

			LOGGER.info(builder.append("\n").toString());

			if (!found)
				LOGGER.warning(String.format("Nenhum resultado encontrado no arquivo \"%s\" para o termo \"%s\".\n",
						nomeArquivo, termo));
		}
	}

}
