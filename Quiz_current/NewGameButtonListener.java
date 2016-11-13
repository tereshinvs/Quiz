import java.awt.event.*;

public class NewGameButtonListener implements ActionListener
	{
		//ActionListener for new game button
		private GameInterfaceManager game_interface_manager;
		private GameManager game_manager;

		NewGameButtonListener(GameInterfaceManager temp_game_interface_manager, GameManager temp_game_manager)
			{
				game_interface_manager = temp_game_interface_manager;
				game_manager = temp_game_manager;
			}

		//Event on click
		public void actionPerformed(ActionEvent aevent)
			{
				game_manager.newGame();
				game_interface_manager.showStartScreen();
			}
	}

