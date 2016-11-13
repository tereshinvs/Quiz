import javax.swing.*;
import javax.swing.event.*;

//Listener for choosing record tab
public class RecordFocusListener implements ChangeListener
	{
		private RecordManager record_manager;
		private JTabbedPane tabs;

		RecordFocusListener(JTabbedPane temp_tabs, RecordManager temp_record_manager)
			{
				tabs = temp_tabs;
				record_manager = temp_record_manager;
			}

		public void stateChanged(ChangeEvent fevent)
			{
				if (tabs.getSelectedIndex() == 1) record_manager.loadRecords(Parameters.WITH_UPDATING_INTERFACE);
			}
	}