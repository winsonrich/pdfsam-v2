/*
 * Created on 27-Jun-2008
 * Copyright (C) 2008 by Andrea Vacondio.
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the 
 * GNU General Public License as published by the Free Software Foundation; 
 * either version 2 of the License.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program; 
 * if not, write to the Free Software Foundation, Inc., 
 *  59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package org.pdfsam.guiclient.commons.dnd.transferables;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.pdfsam.guiclient.configuration.Configuration;
import org.pdfsam.guiclient.dto.VisualPageListItem;
import org.pdfsam.i18n.GettextResource;
/**
 * Transferable for the D&D support 
 * @author Andrea Vacondio
 *
 */
public class VisualPageListTransferable implements Transferable {

	private static final Logger log = Logger.getLogger(VisualPageListTransferable.class.getPackage().getName());
	
	private TransferableData data;
	private static DataFlavor visualListFlavor;
	
	/**
	 * @param data
	 */
	public VisualPageListTransferable(TransferableData data) {
		super();
		this.data = data;
		init();
	}
	
	public VisualPageListTransferable(VisualPageListItem[] dataList, int[] indexesList) {
		super();
		this.data = new TransferableData(dataList, indexesList);
		init();
	}

	/**
	 * initialization
	 */
	private static void init(){
		try {
			if(visualListFlavor == null){
				visualListFlavor = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType);
			}
		} catch (ClassNotFoundException e) {
			visualListFlavor = null;
			log.error(GettextResource.gettext(Configuration.getInstance().getI18nResourceBundle(),"Unable to initializate drag and drop support."), e); 
		}
	}
	
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		if (!isDataFlavorSupported(flavor)){
			throw new UnsupportedFlavorException(flavor);
	    }
	    return data;
	}

	public DataFlavor[] getTransferDataFlavors() {
		  return new DataFlavor[] { visualListFlavor };
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return ((visualListFlavor!=null)&&(flavor!=null)&&(flavor.equals(visualListFlavor)));
	}

	/**
	 * @return the visualListFlavor
	 */
	public static DataFlavor getVisualListFlavor() {
		init();
		return visualListFlavor;
	}

	/**
	 * It models the transferred data
	 * @author Andrea Vacondio
	 *
	 */
	public class TransferableData{
		
		private VisualPageListItem[] dataList;
		private int[] indexesList;
		
		public TransferableData() {
		}

		/**
		 * @param dataList
		 * @param indexesList
		 */
		public TransferableData(VisualPageListItem[] dataList, int[] indexesList) {
			super();
			this.dataList = dataList;
			this.indexesList = indexesList;
		}
		/**
		 * @return the dataList
		 */
		public VisualPageListItem[] getDataList() {
			return dataList;
		}
		/**
		 * @param dataList the dataList to set
		 */
		public void setDataList(VisualPageListItem[] dataList) {
			this.dataList = dataList;
		}
		/**
		 * @return the indexesList
		 */
		public int[] getIndexesList() {
			return indexesList;
		}
		/**
		 * @param indexesList the indexesList to set
		 */
		public void setIndexesList(int[] indexesList) {
			this.indexesList = indexesList;
		}
		
		
	}
}
