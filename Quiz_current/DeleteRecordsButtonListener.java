import java.awt.event.*;
import java.io.*;

public class DeleteRecordsButtonListener implements ActionListener
	{
		private RecordManager record_manager;

		DeleteRecordsButtonListener(RecordManager temp_record_manager)
			{
				record_manager = temp_record_manager;
			}

		public void actionPerformed(ActionEvent aevent)
			{
				record_manager.deleteRecords();
				record_manager.loadRecords(Parameters.WITH_UPDATING_INTERFACE);
			}
	}