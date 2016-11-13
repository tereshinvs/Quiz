public class Quiz
	{
		public static void main(String[] args)
			{
				GameManager game_manager = new GameManager();
				RecordManager record_manager = new RecordManager();
				InterfaceManager interface_manager = new InterfaceManager();

				//Record programm
				interface_manager.getRecordInterfaceManager().setRecordManager(record_manager);
				record_manager.setRecordInterfaceManager(interface_manager.getRecordInterfaceManager());

				//Game programm
				game_manager.setGameInterfaceManager(interface_manager.getGameInterfaceManager());
				interface_manager.getGameInterfaceManager().setGameManager(game_manager);
				game_manager.setRecordManager(record_manager);
			}
	}