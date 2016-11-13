import javax.swing.*;

public class RecordInterfaceManager
	{
		private JPanel record_panel;
		private JButton delete_record_button;

		private InterfaceManager interface_manager;
		private RecordManager record_manager;

		private JLabel pos_label[], name_label[], percent_label[];

		public RecordManager getRecordManager() { return record_manager; }

		RecordInterfaceManager(InterfaceManager temp_interface_manager, JPanel temp_record_panel)
			{
				interface_manager = temp_interface_manager;
				record_panel = temp_record_panel;
				record_panel.setLayout(null);

				pos_label = new JLabel[Parameters.RECORDS_LINE_AMOUNT+1];
				name_label = new JLabel[Parameters.RECORDS_LINE_AMOUNT+1];
				percent_label = new JLabel[Parameters.RECORDS_LINE_AMOUNT+1];
				for (int i = 0; i <= Parameters.RECORDS_LINE_AMOUNT; i++)
					{
						pos_label[i] = new JLabel(Integer.toString(i));
						pos_label[i].setBounds(Parameters.RECORDS_POSITION_LEFT, Parameters.RECORDS_LINE_TOP + i*Parameters.RECORDS_LINE_HEIGHT,
													Parameters.RECORDS_POSITION_WIDTH, Parameters.RECORDS_LINE_HEIGHT);
						pos_label[i].setHorizontalAlignment(JLabel.CENTER);

						name_label[i] = new JLabel();
						name_label[i].setBounds(Parameters.RECORDS_NAME_LEFT, Parameters.RECORDS_LINE_TOP + i*Parameters.RECORDS_LINE_HEIGHT,
													Parameters.RECORDS_NAME_WIDTH, Parameters.RECORDS_LINE_HEIGHT);
						name_label[i].setHorizontalAlignment(JLabel.CENTER);

						percent_label[i] = new JLabel();
						percent_label[i].setBounds(Parameters.RECORDS_PERCENT_LEFT, Parameters.RECORDS_LINE_TOP + i*Parameters.RECORDS_LINE_HEIGHT,
													Parameters.RECORDS_PERCENT_WIDTH, Parameters.RECORDS_LINE_HEIGHT);
						percent_label[i].setHorizontalAlignment(JLabel.CENTER);

						record_panel.add(pos_label[i]);
						record_panel.add(name_label[i]);
						record_panel.add(percent_label[i]);
					}
				pos_label[0].setText("#"); pos_label[0].setFont(Parameters.RECORD_LABEL_FONT);
				name_label[0].setText("Name"); name_label[0].setFont(Parameters.RECORD_LABEL_FONT);
				percent_label[0].setText("Percent of right answers"); percent_label[0].setFont(Parameters.RECORD_LABEL_FONT);

				delete_record_button = new JButton("Delete records");
				delete_record_button.setBounds(Parameters.DELETE_RECORDS_BUTTON_LEFT, Parameters.DELETE_RECORDS_BUTTON_TOP,
														Parameters.DELETE_RECORDS_BUTTON_WIDTH, Parameters.DELETE_RECORDS_BUTTON_HEIGHT);
				record_panel.add(delete_record_button);
			}

		public void setRecordManager(RecordManager temp_record_manager)
			{
				record_manager = temp_record_manager;
				interface_manager.setFocusListenerToTabs(new RecordFocusListener(interface_manager.getTabs(), record_manager));
				delete_record_button.addActionListener(new DeleteRecordsButtonListener(record_manager));
			}

		public void updateRecordInterface(Record records[], int n)
			{
				for (int i = 0; i < n; i++)
					{
						name_label[i+1].setText(records[i].name);
						percent_label[i+1].setText(records[i].percent + "%");
					}

				for (int i = n; i < Parameters.RECORDS_LINE_AMOUNT; i++)
					{
						name_label[i+1].setText("---");
						percent_label[i+1].setText("---");
					}
			}
	}