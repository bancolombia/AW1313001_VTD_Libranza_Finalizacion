package co.com.bancolombia.service.libranza.api.v1.service.documentos.impl;

import co.com.bancolombia.service.libranza.model.RecivedDocuments;

public class RecivedDocumentsService {

	/**
	 * 
	 * @param documents
	 * @return int
	 */
	public int getDocuments(RecivedDocuments documents) {

		RecivedDocuments recivedDocuments = new RecivedDocuments();

		if (documents.getRequestValue() >= 1000000 && !documents.getAccount().equals(null)
				&& documents.getTimeLimit() != 0) {

			recivedDocuments.setRequestValue(documents.getRequestValue());
			recivedDocuments.setAccount(documents.getAccount());
			recivedDocuments.setTimeLimit(documents.getTimeLimit());
			recivedDocuments.setRecived("Success");
			return 0;

		} else {
			recivedDocuments.setRecived("Not Recived");
			return 1;
		}
	}
}