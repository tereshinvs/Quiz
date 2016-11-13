import javax.swing.*;

public class InterfaceManager
	{
		private JFrame main_frame;
		private JPanel game_panel, record_panel, edit_panel;
		private JTabbedPane tabs;

		private GameInterfaceManager game_interface_manager;
		private RecordInterfaceManager record_interface_manager;

		public GameInterfaceManager getGameInterfaceManager() { return game_interface_manager; }
		public RecordInterfaceManager getRecordInterfaceManager() { return record_interface_manager; }
		public JTabbedPane getTabs() { return tabs; }

		InterfaceManager()
			{
				//Create a main frame:
				main_frame = new JFrame("Quiz");
				main_frame.setLayout(null);
				main_frame.setSize(Parameters.MAIN_FRAME_WIDTH, Parameters.MAIN_FRAME_HEIGHT);
				main_frame.setResizable(false);
				main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				main_frame.setLocationRelativeTo(new JFrame().getRootPane());

				//Create tabs:
				game_panel = new JPanel(); record_panel = new JPanel(); edit_panel = new JPanel();
				tabs = new JTabbedPane();
				tabs.add("Game", game_panel); tabs.add("Records", record_panel); tabs.add("Edit", edit_panel); //Adding tabs
				tabs.setBounds(Parameters.TABS_LEFT, Parameters.TABS_TOP, Parameters.TABS_WIDTH, Parameters.TABS_HEIGHT);
				main_frame.add(tabs);

				//Create an object with a game interface methods
				game_interface_manager = new GameInterfaceManager(game_panel);

				//Create an object with a record interface methods
				record_interface_manager = new RecordInterfaceManager(this, record_panel);

				main_frame.setVisible(true);
			}

		public void setFocusListenerToTabs(RecordFocusListener temp)
			{
				tabs.addChangeListener(temp);
			}
	}